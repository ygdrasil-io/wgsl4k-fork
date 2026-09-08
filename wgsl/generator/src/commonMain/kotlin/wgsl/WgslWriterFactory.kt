package org.graphiks.wgsl.wgsl

import org.graphiks.wgsl.back.BackendOptions
import org.graphiks.wgsl.back.BackendRegistry
import org.graphiks.wgsl.back.BackendWriter
import org.graphiks.wgsl.back.WgslOptions
import org.graphiks.wgsl.ir.Module
import org.graphiks.wgsl.proc.Layouter
import org.graphiks.wgsl.proc.Namer
import org.graphiks.wgsl.valid.ModuleInfo

class WgslWriterFactory : BackendRegistry.BackendFactory {
    override fun create(): BackendWriter<*> {
        return createWithOptions(WgslOptions())
    }

    override fun createWithOptions(options: BackendOptions): BackendWriter<*> {
        val wgslOptions = options as? WgslOptions ?: WgslOptions()
        return WgslWriter(
            StringBuilder(),
            Module(),
            ModuleInfo(),
            wgslOptions,
            Namer(),
            Layouter()
        )
    }
}

fun registerWgslBackend() {
    BackendRegistry.DEFAULT.register("wgsl", WgslWriterFactory())
}
