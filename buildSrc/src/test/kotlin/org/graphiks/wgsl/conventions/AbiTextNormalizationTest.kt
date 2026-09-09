package org.graphiks.wgsl.conventions

import kotlin.test.Test
import kotlin.test.assertEquals

class AbiTextNormalizationTest {
    @Test
    fun `normalizes trailing ABI whitespace to a real newline`() {
        assertEquals("header\n}\n", normalizeAbiText("header\n}\n\n"))
    }
}
