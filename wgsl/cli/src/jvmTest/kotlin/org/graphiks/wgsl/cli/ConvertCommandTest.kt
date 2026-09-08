package org.graphiks.wgsl.cli

import com.github.ajalt.clikt.core.CliktError
import com.github.ajalt.clikt.core.parse
import com.github.ajalt.clikt.core.subcommands
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.io.path.createTempFile
import kotlin.io.path.createTempDirectory
import kotlin.io.path.deleteIfExists

private const val MINIMAL_WGSL = """
    @vertex
    fn main() -> @builtin(position) vec4<f32> {
        return vec4<f32>();
    }
"""

class ConvertCommandTest : FunSpec({
    test("convert writes the requested format to stdout without --output") {
        val input = createTempFile(suffix = ".wgsl")
        val stdout = ByteArrayOutputStream()
        val originalStdout = System.out

        try {
            input.toFile().writeText(MINIMAL_WGSL)
            System.setOut(PrintStream(stdout))

            WgslKTypes()
                .subcommands(ConvertCommand())
                .parse(listOf("convert", input.toString(), "--format", "wgsl"))
        } finally {
            System.setOut(originalStdout)
            input.deleteIfExists()
        }

        stdout.toString() shouldContain "@vertex"
    }

    test("convert writes the requested format to --output") {
        val input = createTempFile(suffix = ".wgsl")
        val output = createTempFile(suffix = ".wgsl")

        try {
            input.toFile().writeText(MINIMAL_WGSL)

            WgslKTypes()
                .subcommands(ConvertCommand())
                .parse(listOf("convert", input.toString(), "--format", "wgsl", "--output", output.toString()))

            output.toFile().readText() shouldContain "@vertex"
        } finally {
            input.deleteIfExists()
            output.deleteIfExists()
        }
    }

    test("convert reads WGSL from a temporary file") {
        val input = createTempFile(suffix = ".wgsl")
        val output = createTempFile(suffix = ".wgsl")

        try {
            input.toFile().writeText(MINIMAL_WGSL)

                WgslKTypes()
                    .subcommands(ConvertCommand())
                    .parse(listOf("convert", input.toString(), "--format", "glsl", "--output", output.toString()))

            output.toFile().readText() shouldContain "#version"
        } finally {
            input.deleteIfExists()
            output.deleteIfExists()
        }
    }

    test("convert reports parse errors with status code 1") {
        val input = createTempFile(suffix = ".wgsl")
        val stderr = ByteArrayOutputStream()
        val originalStderr = System.err

        try {
            input.toFile().writeText("@location(0)")
            System.setErr(PrintStream(stderr))

            val error = shouldThrow<CliktError> {
                WgslKTypes()
                    .subcommands(ConvertCommand())
                    .parse(listOf("convert", input.toString(), "--format", "wgsl"))
            }

            error.statusCode shouldBe 1
            error.message shouldBe "Parsing failed"
        } finally {
            System.setErr(originalStderr)
            input.deleteIfExists()
        }

        stderr.toString() shouldContain "Errors during parsing:"
    }

    test("convert rejects a missing input with a controlled Clikt error") {
        val input = createTempFile(suffix = ".wgsl")
        input.deleteIfExists()

        val error = shouldThrow<CliktError> {
            WgslKTypes()
                .subcommands(ConvertCommand())
                .parse(listOf("convert", input.toString(), "--format", "wgsl"))
        }

        error.statusCode shouldBe 1
        error.message shouldContain "Input file"
    }

    test("convert rejects an input directory with a controlled Clikt error") {
        val input = createTempDirectory("wgsl-cli-input")

        try {
            val error = shouldThrow<CliktError> {
                WgslKTypes()
                    .subcommands(ConvertCommand())
                    .parse(listOf("convert", input.toString(), "--format", "wgsl"))
            }

            error.statusCode shouldBe 1
            error.message shouldContain "Input file"
        } finally {
            input.deleteIfExists()
        }
    }

    test("convert rejects an output directory with a controlled Clikt error") {
        val input = createTempFile(suffix = ".wgsl")
        val output = createTempDirectory("wgsl-cli-output")

        try {
            input.toFile().writeText(MINIMAL_WGSL)

            val error = shouldThrow<CliktError> {
                WgslKTypes()
                    .subcommands(ConvertCommand())
                    .parse(listOf("convert", input.toString(), "--format", "wgsl", "--output", output.toString()))
            }

            error.statusCode shouldBe 1
            error.message shouldContain "Output file"
        } finally {
            input.deleteIfExists()
            output.deleteIfExists()
        }
    }
})
