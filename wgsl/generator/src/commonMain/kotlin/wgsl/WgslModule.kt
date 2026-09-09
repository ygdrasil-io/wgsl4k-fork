package org.graphiks.wgsl.wgsl

import org.graphiks.wgsl.back.WgslOptions
import org.graphiks.wgsl.ir.Module
import org.graphiks.wgsl.proc.Layouter
import org.graphiks.wgsl.proc.Namer
import org.graphiks.wgsl.valid.ModuleInfo

/**
 * API publique pour le backend WGSL.
 */
object WgslModule {

    /**
     * Génère le code source WGSL pour un module.
     */
    fun writeString(
        module: Module,
        moduleInfo: ModuleInfo = ModuleInfo.empty(),
        options: WgslOptions = WgslOptions()
    ): String {
        val namer = Namer().apply { reset(emptySet()) }
        val layouter = Layouter().apply { update(module) }
        val output = StringBuilder()
        val writer = WgslWriter(output, module, moduleInfo, options, namer, layouter)
        return writer.write()
    }
}
