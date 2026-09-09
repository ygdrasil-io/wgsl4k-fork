package org.graphiks.wgsl.cli

import java.io.File

internal actual fun filePathKind(path: String): FilePathKind {
    val file = File(path)
    return when {
        file.isDirectory -> FilePathKind.DIRECTORY
        file.isFile -> FilePathKind.FILE
        else -> FilePathKind.MISSING
    }
}

internal actual fun readTextFromPath(path: String): String = File(path).readText()

internal actual fun writeTextToPath(path: String, content: String) {
    File(path).writeText(content)
}
