package org.graphiks.wgsl.generator.glsl

import org.graphiks.wgsl.back.BackendOptions
import org.graphiks.wgsl.back.BackendRegistry
import org.graphiks.wgsl.back.BackendWriter
import org.graphiks.wgsl.back.GlslOptions
import org.graphiks.wgsl.ir.Module
import org.graphiks.wgsl.proc.Layouter
import org.graphiks.wgsl.proc.Namer
import org.graphiks.wgsl.valid.ModuleInfo

class GlslWriterFactory : BackendRegistry.BackendFactory {
    override fun create(): BackendWriter<*> {
        return createWithOptions(GlslOptions())
    }

    override fun createWithOptions(options: BackendOptions): BackendWriter<*> {
        val glslOptions = options as? GlslOptions ?: GlslOptions()
        return GlslWriter(
            StringBuilder(),
            Module(),
            ModuleInfo(),
            glslOptions,
            Namer(),
            Layouter()
        )
    }
}

fun registerGlslBackend() {
    BackendRegistry.DEFAULT.register("glsl", GlslWriterFactory())
}
