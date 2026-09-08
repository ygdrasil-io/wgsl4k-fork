package org.graphiks.wgsl.tests

import org.graphiks.wgsl.back.BackendRegistry
import org.graphiks.wgsl.wgsl.WgslWriterFactory

class RoundTripTest : GoldenTestBase("wgsl", "roundtrip") {
    init {
        // Ensure WGSL backend is registered
        BackendRegistry.DEFAULT.register("wgsl", WgslWriterFactory())
    }
}
