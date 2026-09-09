package org.graphiks.wgsl.parser

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import org.graphiks.wgsl.proc.reflectWgslModule

class WgslReflectionContractTest : FunSpec({
    test("validates and reflects uniform-buffer fragment module") {
        val report = validateAndReflectWgsl(
            sourceId = "solid_color_uniform.wgsl",
            source = """
                struct SolidColorParams {
                    color: vec4<f32>,
                }

                @group(1) @binding(0)
                var<uniform> solidColor: SolidColorParams;

                @fragment
                fn fragmentMain() -> @location(0) vec4<f32> {
                    return vec4<f32>(1.0, 0.0, 0.0, 1.0);
                }
            """.trimIndent(),
        )

        report.validation.success shouldBe true
        report.entryPoints.single().stage shouldBe "fragment"
        report.bindings.single().resourceKind shouldBe "uniformBuffer"
        report.layouts.single().structName shouldBe "SolidColorParams"
        report.unsupportedFeatures shouldBe emptyList()
    }

    test("reflects sampled texture, sampler, and uniform layout") {
        val module = lowerWgsl(
            """
            struct FragmentParams {
                textureScale: vec2<f32>,
                exposure: f32,
            }

            @group(2) @binding(0)
            var sourceTexture: texture_2d<f32>;

            @group(2) @binding(1)
            var sourceSampler: sampler;

            @group(2) @binding(2)
            var<uniform> fragmentParams: FragmentParams;

            @fragment
            fn fragmentMain() -> @location(0) vec4<f32> {
                return vec4<f32>(1.0, 1.0, 1.0, 1.0);
            }
            """.trimIndent()
        )

        val report = module.reflectWgslModule(sourceId = "sampled_texture_uniform.wgsl")

        report.sourceId shouldBe "sampled_texture_uniform.wgsl"
        report.validation.success shouldBe true
        report.entryPoints.single().name shouldBe "fragmentMain"
        report.entryPoints.single().stage shouldBe "fragment"

        report.bindings.map { "${it.group}:${it.binding}:${it.name}:${it.resourceKind}" }
            .shouldContainExactly(
                "2:0:sourceTexture:sampledTexture",
                "2:1:sourceSampler:sampler",
                "2:2:fragmentParams:uniformBuffer",
            )

        val fragmentParams = report.layouts.single { it.structName == "FragmentParams" }
        fragmentParams.addressSpace shouldBe "uniform"
        fragmentParams.size shouldBe 16
        fragmentParams.alignment shouldBe 8
        fragmentParams.members shouldHaveSize 2
        fragmentParams.members[0].name shouldBe "textureScale"
        fragmentParams.members[0].type shouldBe "vec2<f32>"
        fragmentParams.members[0].offset shouldBe 0
        fragmentParams.members[0].size shouldBe 8
        fragmentParams.members[0].alignment shouldBe 8
        fragmentParams.members[1].name shouldBe "exposure"
        fragmentParams.members[1].type shouldBe "f32"
        fragmentParams.members[1].offset shouldBe 8
        fragmentParams.members[1].size shouldBe 4
        fragmentParams.members[1].alignment shouldBe 4
    }

    test("reflects compute workgroup size and storage buffer access") {
        val module = lowerWgsl(
            """
            struct CopyPayload {
                value: u32,
            }

            @group(3) @binding(0)
            var<storage, read> src: array<CopyPayload>;

            @group(3) @binding(1)
            var<storage, read_write> dst: array<CopyPayload>;

            @compute @workgroup_size(8, 4, 1)
            fn copyMain() {}
            """.trimIndent()
        )

        val report = module.reflectWgslModule(sourceId = "compute_buffer_copy.wgsl")

        report.entryPoints.single().name shouldBe "copyMain"
        report.entryPoints.single().stage shouldBe "compute"
        report.entryPoints.single().workgroupSize shouldBe listOf(8, 4, 1)
        report.bindings.map { "${it.group}:${it.binding}:${it.name}:${it.resourceKind}:${it.access}" }
            .shouldContainExactly(
                "3:0:src:storageBuffer:read",
                "3:1:dst:storageBuffer:read_write",
            )
        report.unsupportedFeatures shouldBe emptyList()
    }

    test("reports invalid WGSL syntax with stable diagnostic reason") {
        val report = validateAndReflectWgsl(
            sourceId = "invalid_syntax.wgsl",
            source = "@fragment fn broken( {",
        )

        report.validation.success shouldBe false
        report.validation.diagnostics.first().reason shouldBe "wgsl4k.validation.syntax_error"
        report.entryPoints shouldBe emptyList()
        report.bindings shouldBe emptyList()
        report.layouts shouldBe emptyList()
    }

    test("reports valid but unrepresented external texture as unsupported") {
        val report = validateAndReflectWgsl(
            sourceId = "layout_unrepresented.wgsl",
            source = """
                @group(0) @binding(0)
                var sourceVideo: texture_external;

                @fragment
                fn fragmentMain() -> @location(0) vec4<f32> {
                    return vec4<f32>(0.0, 0.0, 0.0, 1.0);
                }
            """.trimIndent(),
        )

        report.validation.success shouldBe true
        report.bindings.single().resourceKind shouldBe "unsupported"
        report.unsupportedFeatures.shouldContainExactly(
            "wgsl4k.reflection.feature_unrepresented:sourceVideo:texture_external"
        )
    }
})
