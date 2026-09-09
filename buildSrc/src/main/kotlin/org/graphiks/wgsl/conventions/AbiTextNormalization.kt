package org.graphiks.wgsl.conventions

internal fun normalizeAbiText(contents: String): String =
    contents.replace(Regex("[ \\t\\r\\n]+$"), "\n")
