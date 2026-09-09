package org.graphiks.wgsl.parser

import org.graphiks.wgsl.arena.Handle
import org.graphiks.wgsl.ast.TranslationUnit
import org.graphiks.wgsl.ir.*
import org.graphiks.wgsl.lexer.Lexer
import org.graphiks.wgsl.parser.Parser
import org.graphiks.wgsl.parser.TypeResolver

// Regular functions
fun parseWgsl(source: String): TranslationUnit {
    return Parser(Lexer(source)).parse()
}

fun resolveWgsl(source: String): TypeResolver.ResolutionResult {
    val unit = parseWgsl(source)
    return TypeResolver().resolve(unit)
}

fun lowerWgsl(source: String): Module {
    val resolved = resolveWgsl(source)
    return Lowerer().lower(resolved.resolvedUnit)
}

// Extension functions for Module
fun Module.findLiteralExpressionInFunction(funcName: String, value: ScalarValue): Expression? {
    val func = functions.toList().find { it.name == funcName } ?: return null
    return func.expressions.toList().find { expr ->
        expr.kind is ExpressionKind.Literal &&
        (expr.kind as ExpressionKind.Literal).value == LiteralValue.Scalar(value)
    }
}

fun Module.findBinaryExpressionInFunction(funcName: String, op: BinaryOperator): Expression? {
    val func = functions.toList().find { it.name == funcName } ?: return null
    return func.expressions.toList().find { expr ->
        expr.kind is ExpressionKind.Binary &&
        (expr.kind as ExpressionKind.Binary).operator == op
    }
}

fun Module.findExpressionInFunction(funcName: String, predicate: (Expression) -> Boolean): Expression? {
    val func = functions.toList().find { it.name == funcName } ?: return null
    return func.expressions.toList().find(predicate)
}

fun Module.findType(predicate: (TypeInner) -> Boolean): Type? {
    return types.toList().find { predicate(it.inner) }
}

fun Module.findScalarType(kind: ScalarKind, width: Int): Type? {
    return findType { inner ->
        inner is TypeInner.Scalar && inner.kind == kind && inner.width == width
    }
}

fun Module.findVectorType(size: VectorSize): Type? {
    return findType { inner ->
        inner is TypeInner.Vector && inner.size == size
    }
}

fun Module.findStructType(): Type? {
    return findType { inner -> inner is TypeInner.Struct }
}

fun Module.getReturnValue(funcName: String): Handle<Expression>? {
    val func = functions.toList().find { it.name == funcName } ?: return null
    val bodyBlock = func.blocks[func.body]
    val returnStmt = bodyBlock.statements.find { it is Statement.Return } as? Statement.Return
    return returnStmt?.value
}

// Legacy object for backward compatibility
object TestUtils {
    fun parseWgsl(source: String): TranslationUnit = org.graphiks.wgsl.parser.parseWgsl(source)
    fun resolveWgsl(source: String): TypeResolver.ResolutionResult = org.graphiks.wgsl.parser.resolveWgsl(source)
    fun lowerWgsl(source: String): Module = org.graphiks.wgsl.parser.lowerWgsl(source)
}
