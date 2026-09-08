package org.graphiks.wgsl.generator.msl

import org.graphiks.wgsl.back.BackendOptions
import org.graphiks.wgsl.back.BackendRegistry
import org.graphiks.wgsl.back.BackendWriter
import org.graphiks.wgsl.back.MslOptions
import org.graphiks.wgsl.ir.Module
import org.graphiks.wgsl.proc.Layouter
import org.graphiks.wgsl.proc.Namer
import org.graphiks.wgsl.valid.ModuleInfo

class MslWriterFactory : BackendRegistry.BackendFactory {
    override fun create(): BackendWriter<*> {
        return createWithOptions(MslOptions())
    }

    override fun createWithOptions(options: BackendOptions): BackendWriter<*> {
        val mslOptions = options as? MslOptions ?: MslOptions()
        // These will be initialized later in write() or we provide defaults
        return MslWriter(
            StringBuilder(),
            Module(),
            ModuleInfo(),
            mslOptions,
            Namer(),
            Layouter()
        )
    }
}

fun registerMslBackend() {
    BackendRegistry.DEFAULT.register("msl", MslWriterFactory())
}
