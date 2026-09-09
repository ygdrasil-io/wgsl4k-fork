package org.graphiks.wgsl.parser

import org.graphiks.wgsl.proc.WgslReflectionDiagnostic
import org.graphiks.wgsl.proc.WgslReflectionReport
import org.graphiks.wgsl.proc.WgslValidationSummary
import org.graphiks.wgsl.proc.reflectWgslModule

fun validateAndReflectWgsl(
    sourceId: String,
    source: String,
    moduleHash: String? = null,
): WgslReflectionReport {
    val parseResult = parseWgslResult(source)
    if (!parseResult.isSuccess) {
        return WgslReflectionReport(
            sourceId = sourceId,
            moduleHash = moduleHash,
            validation = WgslValidationSummary(
                success = false,
                diagnostics = parseResult.errors.map {
                    WgslReflectionDiagnostic(
                        reason = "wgsl4k.validation.syntax_error",
                        message = it.message,
                    )
                },
            ),
        )
    }

    val resolution = TypeResolver().resolve(parseResult.translationUnit)
    if (!resolution.isSuccess) {
        return WgslReflectionReport(
            sourceId = sourceId,
            moduleHash = moduleHash,
            validation = WgslValidationSummary(
                success = false,
                diagnostics = resolution.unresolvedReferences.map {
                    WgslReflectionDiagnostic(
                        reason = "wgsl4k.validation.semantic_error",
                        message = it.message,
                    )
                },
            ),
        )
    }

    return Lowerer().lower(resolution.resolvedUnit).reflectWgslModule(
        sourceId = sourceId,
        moduleHash = moduleHash,
    )
}
