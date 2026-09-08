package org.graphiks.wgsl.cli

import com.github.ajalt.clikt.core.CliktError
import java.io.File

internal actual fun filePathKind(path: String): FilePathKind {
    val file = File(path)
    return when {
        file.isDirectory -> FilePathKind.DIRECTORY
        file.isFile -> FilePathKind.FILE
        else -> FilePathKind.MISSING
    }
}

internal actual fun readTextFromPath(path: String): String = try {
    File(path).readText()
} catch (error: Exception) {
    throw CliktError("Unable to read input file: $path", statusCode = 1)
}

internal actual fun writeTextToPath(path: String, content: String) {
    File(path).writeText(content)
}
