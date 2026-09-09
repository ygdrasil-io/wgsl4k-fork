package org.graphiks.wgsl.back

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.string.shouldContain
import org.graphiks.wgsl.ir.Expression
import org.graphiks.wgsl.ir.ExpressionKind
import org.graphiks.wgsl.ir.LiteralValue
import org.graphiks.wgsl.ir.Module
import org.graphiks.wgsl.ir.ScalarValue
import org.graphiks.wgsl.valid.ModuleInfo

class IrWriterTest : FunSpec({
    test("serializes non-finite float literals from conversion clamp edge cases") {
        val module = Module()
        module.globalExpressions.append(
            Expression(ExpressionKind.Literal(LiteralValue.Scalar(ScalarValue.F32(Float.POSITIVE_INFINITY))))
        )
        module.globalExpressions.append(
            Expression(ExpressionKind.Literal(LiteralValue.Scalar(ScalarValue.F64(Double.NEGATIVE_INFINITY))))
        )
        module.globalExpressions.append(
            Expression(ExpressionKind.Literal(LiteralValue.Scalar(ScalarValue.AbstractFloat(Double.NaN))))
        )

        val json = IrWriter().write(module, ModuleInfo.empty())

        json shouldContain "Infinity"
        json shouldContain "-Infinity"
        json shouldContain "NaN"
    }
})
