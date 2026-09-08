package org.graphiks.wgsl.cli

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import platform.posix.fclose
import platform.posix.fopen
import platform.posix.fread
import platform.posix.fwrite
import platform.posix.closedir
import platform.posix.opendir

@OptIn(ExperimentalForeignApi::class)
internal actual fun filePathKind(path: String): FilePathKind {
    val directory = opendir(path)
    if (directory != null) {
        closedir(directory)
        return FilePathKind.DIRECTORY
    }

    val file = fopen(path, "rb")
    if (file != null) {
        fclose(file)
        return FilePathKind.FILE
    }

    return FilePathKind.MISSING
}

@OptIn(ExperimentalForeignApi::class)
internal actual fun readTextFromPath(path: String): String {
    val file = fopen(path, "rb") ?: error("Unable to open input file: $path")

    return try {
        val buffer = ByteArray(4096)
        buildString {
            while (true) {
                val bytesRead = buffer.usePinned { pinned ->
                    fread(pinned.addressOf(0), 1UL, buffer.size.toULong(), file)
                }.toInt()
                append(buffer.decodeToString(0, bytesRead))
                if (bytesRead < buffer.size) break
            }
        }
    } finally {
        fclose(file)
    }
}

@OptIn(ExperimentalForeignApi::class)
internal actual fun writeTextToPath(path: String, content: String) {
    val file = fopen(path, "wb") ?: error("Unable to open output file: $path")
    val bytes = content.encodeToByteArray()

    try {
        if (bytes.isNotEmpty()) {
            val bytesWritten = bytes.usePinned { pinned ->
                fwrite(pinned.addressOf(0), 1UL, bytes.size.toULong(), file)
            }.toInt()
            check(bytesWritten == bytes.size) { "Unable to write output file: $path" }
        }
    } finally {
        fclose(file)
    }
}
