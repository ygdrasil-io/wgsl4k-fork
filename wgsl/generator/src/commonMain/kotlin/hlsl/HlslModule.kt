package org.graphiks.wgsl.generator.hlsl

import org.graphiks.wgsl.back.HlslOptions
import org.graphiks.wgsl.ir.Module
import org.graphiks.wgsl.proc.Layouter
import org.graphiks.wgsl.proc.Namer
import org.graphiks.wgsl.valid.ModuleInfo

/**
 * API publique pour le backend HLSL.
 */
object HlslModule {

    /**
     * Génère le code source HLSL pour un module.
     */
    fun writeString(
        module: Module,
        moduleInfo: ModuleInfo = ModuleInfo.empty(),
        options: HlslOptions = HlslOptions()
    ): String {
        val namer = Namer().apply { reset(emptySet()) }
        val layouter = Layouter().apply { update(module) }
        val output = StringBuilder()
        val writer = HlslWriter(output, module, moduleInfo, options, namer, layouter)
        return writer.write()
    }
}
