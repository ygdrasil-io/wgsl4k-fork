package org.graphiks.wgsl.tests

import org.graphiks.wgsl.back.registerIrBackend
import org.graphiks.wgsl.generator.glsl.registerGlslBackend
import org.graphiks.wgsl.generator.hlsl.registerHlslBackend
import org.graphiks.wgsl.generator.msl.registerMslBackend
import org.graphiks.wgsl.wgsl.registerWgslBackend

fun registerAllBackends() {
    registerIrBackend()
    registerMslBackend()
    registerHlslBackend()
    registerGlslBackend()
    registerWgslBackend()
}
