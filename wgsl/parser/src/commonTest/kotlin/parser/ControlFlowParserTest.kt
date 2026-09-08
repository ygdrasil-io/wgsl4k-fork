package org.graphiks.wgsl.parser

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import org.graphiks.wgsl.ast.*
import org.graphiks.wgsl.lexer.Lexer

class ControlFlowParserTest : FunSpec({
    test("parse if without parentheses") {
        val source = "fn f() { if true { return; } }"
        val lexer = Lexer(source)
        val parser = Parser(lexer)
        val unit = parser.parse()
        
        unit.declarations shouldHaveSize 1
        val func = unit.declarations[0] as FunctionDecl
        val ifStmt = func.body!!.statements[0] as IfStatement
        ifStmt.condition shouldBe BoolLiteral(true, ifStmt.condition.span)
    }

    test("parse while without parentheses") {
        val source = "fn f() { while true { break; } }"
        val lexer = Lexer(source)
        val parser = Parser(lexer)
        val unit = parser.parse()
        
        unit.declarations shouldHaveSize 1
        val func = unit.declarations[0] as FunctionDecl
        val whileStmt = func.body!!.statements[0] as WhileStatement
        whileStmt.condition shouldBe BoolLiteral(true, whileStmt.condition.span)
    }

    test("parse switch without parentheses") {
        val source = "fn f() { switch 1 { case 1: { break; } default: {} } }"
        val lexer = Lexer(source)
        val parser = Parser(lexer)
        val unit = parser.parse()
        
        unit.declarations shouldHaveSize 1
        val func = unit.declarations[0] as FunctionDecl
        val switchStmt = func.body!!.statements[0] as SwitchStatement
        switchStmt.expression.shouldBeInstanceOf<IntLiteral>()
    }

    test("parse for without parentheses") {
        val source = "fn f() { for var i = 0; i < 10; i = i + 1 { } }"
        val lexer = Lexer(source)
        val parser = Parser(lexer)
        val unit = parser.parse()
        
        unit.declarations shouldHaveSize 1
        val func = unit.declarations[0] as FunctionDecl
        val forStmt = func.body!!.statements[0] as ForStatement
        forStmt.init.shouldBeInstanceOf<VariableDeclStatement>()
    }

    test("parse for with typed var decl produces no errors") {
        val source = "fn f() { for (var i: u32 = 0u; i < 10u; i = i + 1u) { } }"
        val result = parseWgslResult(source)
        result.isSuccess shouldBe true
        result.errors shouldBe emptyList()
    }

    test("parse const_assert without parentheses") {
        val source = "const_assert 1 == 1;"
        val lexer = Lexer(source)
        val parser = Parser(lexer)
        val unit = parser.parse()
        
        unit.declarations shouldHaveSize 1
        val assert = unit.declarations[0] as ConstAssertDecl
        assert.expression.shouldBeInstanceOf<BinaryExpr>()
    }
})
