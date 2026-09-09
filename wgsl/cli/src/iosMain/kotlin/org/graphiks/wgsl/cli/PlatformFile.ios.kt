package org.graphiks.wgsl.cli

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import platform.posix.fclose
import platform.posix.fopen
import platform.posix.fread
import platform.posix.fwrite
import platform.posix.fseek
import platform.posix.ftell
import platform.posix.SEEK_END
import platform.posix.SEEK_SET
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
    val file = fopen(path, "rb") ?: error("Unable to read input file: $path")

    return try {
        if (fseek(file, 0, SEEK_END) != 0) {
            error("Unable to read input file: $path")
        }
        val fileSize = ftell(file)
        if (fileSize < 0 || fseek(file, 0, SEEK_SET) != 0) {
            error("Unable to read input file: $path")
        }
        if (fileSize == 0L) return ""

        val bytes = ByteArray(fileSize.toInt())
        val bytesRead = bytes.usePinned { pinned ->
            fread(pinned.addressOf(0), 1UL, bytes.size.toULong(), file)
        }.toInt()
        if (bytesRead != bytes.size) {
            error("Unable to read input file: $path")
        }
        bytes.decodeToString()
    } finally {
        fclose(file)
    }
}

@OptIn(ExperimentalForeignApi::class)
internal actual fun writeTextToPath(path: String, content: String) {
    val file = fopen(path, "wb") ?: error("Unable to write output file: $path")
    val bytes = content.encodeToByteArray()

    try {
        if (bytes.isNotEmpty()) {
            val bytesWritten = bytes.usePinned { pinned ->
                fwrite(pinned.addressOf(0), 1UL, bytes.size.toULong(), file)
            }.toInt()
            if (bytesWritten != bytes.size) {
                error("Unable to write output file: $path")
            }
        }
    } finally {
        fclose(file)
    }
}
