package org.graphiks.wgsl.generator.msl

import org.graphiks.wgsl.back.MslOptions
import org.graphiks.wgsl.ir.Module
import org.graphiks.wgsl.proc.Layouter
import org.graphiks.wgsl.proc.Namer
import org.graphiks.wgsl.valid.ModuleInfo

/**
 * API publique pour le backend MSL.
 */
object MslModule {

    /**
     * Génère le code source MSL pour un module.
     */
    fun writeString(
        module: Module,
        moduleInfo: ModuleInfo = ModuleInfo.empty(),
        options: MslOptions = MslOptions()
    ): String {
        val namer = Namer().apply { reset(Keywords.MSL_RESERVED) }
        val layouter = Layouter().apply { update(module) }
        val output = StringBuilder()
        val writer = MslWriter(output, module, moduleInfo, options, namer, layouter)
        return writer.write()
    }
}
