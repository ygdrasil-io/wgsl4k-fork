package org.graphiks.wgsl.generator.glsl

import org.graphiks.wgsl.back.GlslOptions
import org.graphiks.wgsl.ir.Module
import org.graphiks.wgsl.proc.Layouter
import org.graphiks.wgsl.proc.Namer
import org.graphiks.wgsl.valid.ModuleInfo

/**
 * API publique pour le backend GLSL.
 */
object GlslModule {

    /**
     * Génère le code source GLSL pour un module.
     */
    fun writeString(
        module: Module,
        moduleInfo: ModuleInfo = ModuleInfo.empty(),
        options: GlslOptions = GlslOptions()
    ): String {
        val namer = Namer().apply { reset(emptySet()) }
        val layouter = Layouter().apply { update(module) }
        val output = StringBuilder()
        val writer = GlslWriter(output, module, moduleInfo, options, namer, layouter)
        return writer.write()
    }
}
