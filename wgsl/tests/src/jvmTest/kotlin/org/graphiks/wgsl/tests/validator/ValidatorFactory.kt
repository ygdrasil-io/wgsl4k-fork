package org.graphiks.wgsl.tests.validator

import java.nio.file.Files
import java.nio.file.Path

/**
 * Validator factory
 */
object ValidatorFactory {

    private val nativeBackends = listOf(
        BackendType.GLSL,
        BackendType.SPIRV,
        BackendType.HLSL,
        BackendType.MSL,
    )

    private val validators: MutableMap<BackendType, BackendValidator> = mutableMapOf()

    init {
        // Register available validators
        if (GlslValidator.isAvailable()) {
            validators[BackendType.GLSL] = GlslValidator()
        }

        if (SpirvValidator.isAvailable()) {
            validators[BackendType.SPIRV] = SpirvValidator()
        }

        if (HlslValidator.isAvailable()) {
            validators[BackendType.HLSL] = HlslValidator()
        }

        // Metal is disabled because it requires Metal Toolchain which might not be installed
        /*
        if (MetalValidator.isAvailable()) {
            validators[BackendType.MSL] = MetalValidator()
        }
        */
    }

    /**
     * Get validator for backend type
     */
    fun getValidator(backendType: BackendType): BackendValidator? {
        return validators[backendType]
    }

    /**
     * Get all available validators
     */
    fun getAllValidators(): Map<BackendType, BackendValidator> {
        return validators.toMap()
    }

    /**
     * Check if validator is available for backend type
     */
    fun isAvailable(backendType: BackendType): Boolean {
        return validators.containsKey(backendType)
    }

    /** Write optional native validator status for the golden coverage report. */
    fun writeStatusReport(rootDir: Path) {
        val report = rootDir.resolve("wgsl/tests/build/reports/golden-coverage/validator-status.txt")
        Files.createDirectories(report.parent)
        Files.writeString(
            report,
            nativeBackends.joinToString(separator = "\n") { backend ->
                "$backend=${if (isAvailable(backend)) "available" else "skipped"}"
            } + "\n"
        )
    }
}
