package org.graphiks.wgsl.cli

import com.github.ajalt.clikt.core.CliktError

internal enum class FilePathKind {
    FILE,
    DIRECTORY,
    MISSING
}

internal expect fun filePathKind(path: String): FilePathKind

internal fun requireInputFile(path: String) {
    if (filePathKind(path) != FilePathKind.FILE) {
        throw CliktError("Input file must exist and cannot be a directory: $path", statusCode = 1)
    }
}

internal fun requireOutputFile(path: String) {
    if (filePathKind(path) == FilePathKind.DIRECTORY) {
        throw CliktError("Output file cannot be a directory: $path", statusCode = 1)
    }
}

internal expect fun readTextFromPath(path: String): String

internal expect fun writeTextToPath(path: String, content: String)
