package org.graphiks.wgsl.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.CliktError
import com.github.ajalt.clikt.core.main
import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.ajalt.clikt.parameters.types.enum
import org.graphiks.wgsl.generator.glsl.GlslWriterFactory
import org.graphiks.wgsl.generator.hlsl.HlslWriterFactory
import org.graphiks.wgsl.generator.msl.MslWriterFactory
import org.graphiks.wgsl.wgsl.WgslWriterFactory

enum class OutputFormat {
    glsl, hlsl, msl, wgsl
}

class ConvertCommand : CliktCommand(name = "convert") {
    val input by argument()
    val format by option().enum<OutputFormat>().required()
    val output by option()

    override fun run() {
        requireInputFile(input)
        val source = try {
            readTextFromPath(input)
        } catch (error: CliktError) {
            throw error
        } catch (error: Exception) {
            throw CliktError("Unable to read input file: $input", statusCode = 1)
        }
        val parseResult = org.graphiks.wgsl.parser.parseWgslResult(source)

        if (!parseResult.isSuccess) {
            echo("Errors during parsing:", err = true)
            parseResult.errors.forEach { echo("${it.message} at ${it.span}", err = true) }
            throw CliktError("Parsing failed", statusCode = 1)
        }

        val resolutionResult = try {
            org.graphiks.wgsl.parser.TypeResolver().resolve(parseResult.translationUnit)
        } catch (error: Exception) {
            throw CliktError("Type resolution failed: ${error.message}", statusCode = 1)
        }

        if (!resolutionResult.isSuccess) {
            echo("Errors during type resolution:", err = true)
            resolutionResult.unresolvedReferences.forEach { echo(it, err = true) }
            throw CliktError("Type resolution failed", statusCode = 1)
        }

        val lowerer = org.graphiks.wgsl.parser.Lowerer()
        val loweredModule = lowerer.lower(resolutionResult.resolvedUnit)

        val validator = org.graphiks.wgsl.proc.Validator()
        val validationErrors = validator.validate(loweredModule)
        if (validationErrors.isNotEmpty()) {
            echo("Validation errors:", err = true)
            validationErrors.forEach { echo(it.message, err = true) }
            // We continue anyway, or should we stop?
        }

        val writerFactory = when (format) {
            OutputFormat.glsl -> GlslWriterFactory()
            OutputFormat.hlsl -> HlslWriterFactory()
            OutputFormat.msl -> MslWriterFactory()
            OutputFormat.wgsl -> WgslWriterFactory()
        }

        val writer = writerFactory.create()
        val moduleInfo = org.graphiks.wgsl.valid.ModuleInfo.empty()
        val result = writer.write(loweredModule, moduleInfo)

        if (output != null) {
            val outputPath = output!!
            requireOutputFile(outputPath)
            try {
                writeTextToPath(outputPath, result)
            } catch (error: CliktError) {
                throw error
            } catch (error: Exception) {
                throw CliktError("Unable to write output file: $outputPath", statusCode = 1)
            }
            echo("Written to $output")
        } else {
            echo(result)
        }
    }
}

class WgslKTypes : CliktCommand() {
    override fun run() = Unit
}

fun main(args: Array<String>) = WgslKTypes()
    .subcommands(ConvertCommand())
    .main(args)
