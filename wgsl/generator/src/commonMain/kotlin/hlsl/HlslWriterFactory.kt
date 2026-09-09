package org.graphiks.wgsl.generator.hlsl

import org.graphiks.wgsl.back.BackendOptions
import org.graphiks.wgsl.back.BackendRegistry
import org.graphiks.wgsl.back.BackendWriter
import org.graphiks.wgsl.back.HlslOptions
import org.graphiks.wgsl.ir.Module
import org.graphiks.wgsl.proc.Layouter
import org.graphiks.wgsl.proc.Namer
import org.graphiks.wgsl.valid.ModuleInfo

class HlslWriterFactory : BackendRegistry.BackendFactory {
    override fun create(): BackendWriter<*> {
        return createWithOptions(HlslOptions())
    }

    override fun createWithOptions(options: BackendOptions): BackendWriter<*> {
        val hlslOptions = options as? HlslOptions ?: HlslOptions()
        return HlslWriter(
            StringBuilder(),
            Module(),
            ModuleInfo(),
            hlslOptions,
            Namer(),
            Layouter()
        )
    }
}

fun registerHlslBackend() {
    BackendRegistry.DEFAULT.register("hlsl", HlslWriterFactory())
}
