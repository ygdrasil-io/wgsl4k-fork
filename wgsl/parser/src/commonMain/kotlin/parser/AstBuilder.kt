package org.graphiks.wgsl.parser

import org.graphiks.wgsl.ast.ArrayType
import org.graphiks.wgsl.ast.AssignmentStatement
import org.graphiks.wgsl.ast.AtomicType
import org.graphiks.wgsl.ast.Attribute
import org.graphiks.wgsl.ast.BinaryExpr
import org.graphiks.wgsl.ast.BinaryOperator
import org.graphiks.wgsl.ast.BitcastExpr
import org.graphiks.wgsl.ast.BlockStatement
import org.graphiks.wgsl.ast.BoolLiteral
import org.graphiks.wgsl.ast.BreakIfStatement
import org.graphiks.wgsl.ast.BreakStatement
import org.graphiks.wgsl.ast.BuiltinValue
import org.graphiks.wgsl.ast.CallExpr
import org.graphiks.wgsl.ast.Case
import org.graphiks.wgsl.ast.ConstAssertDecl
import org.graphiks.wgsl.ast.ConstantType
import org.graphiks.wgsl.ast.ContinueStatement
import org.graphiks.wgsl.ast.DefaultCase
import org.graphiks.wgsl.ast.DiagnosticDirective
import org.graphiks.wgsl.ast.DiscardStatement
import org.graphiks.wgsl.ast.EnableDirective
import org.graphiks.wgsl.ast.EntryPointAttribute
import org.graphiks.wgsl.ast.Expression
import org.graphiks.wgsl.ast.ExpressionStatement
import org.graphiks.wgsl.ast.FloatLiteral
import org.graphiks.wgsl.ast.ForStatement
import org.graphiks.wgsl.ast.FragmentInput
import org.graphiks.wgsl.ast.FunctionDecl
import org.graphiks.wgsl.ast.GlobalDecl
import org.graphiks.wgsl.ast.IdentExpr
import org.graphiks.wgsl.ast.IfStatement
import org.graphiks.wgsl.ast.IncDecStatement
import org.graphiks.wgsl.ast.IndexExpr
import org.graphiks.wgsl.ast.IntLiteral
import org.graphiks.wgsl.ast.LoopStatement
import org.graphiks.wgsl.ast.MatrixType
import org.graphiks.wgsl.ast.MemberAccessExpr
import org.graphiks.wgsl.ast.NamedType
import org.graphiks.wgsl.ast.OverrideDecl
import org.graphiks.wgsl.ast.Param
import org.graphiks.wgsl.ast.PhonyAssignmentStatement
import org.graphiks.wgsl.ast.PointerType
import org.graphiks.wgsl.ast.RayQueryType
import org.graphiks.wgsl.ast.ReferenceType
import org.graphiks.wgsl.ast.RequiresDirective
import org.graphiks.wgsl.ast.ReturnStatement
import org.graphiks.wgsl.ast.SamplerType
import org.graphiks.wgsl.ast.ScalarKind
import org.graphiks.wgsl.ast.ScalarType
import org.graphiks.wgsl.ast.Statement
import org.graphiks.wgsl.ast.StorageClass
import org.graphiks.wgsl.ast.StringLiteral
import org.graphiks.wgsl.ast.StructDecl
import org.graphiks.wgsl.ast.StructMember
import org.graphiks.wgsl.ast.StructType
import org.graphiks.wgsl.ast.SwitchBody
import org.graphiks.wgsl.ast.SwitchCase
import org.graphiks.wgsl.ast.SwitchStatement
import org.graphiks.wgsl.ast.SwizzleExpr
import org.graphiks.wgsl.ast.TemplateParam
import org.graphiks.wgsl.ast.TemplateType
import org.graphiks.wgsl.ast.TextureKind
import org.graphiks.wgsl.ast.TextureType
import org.graphiks.wgsl.ast.TernaryExpr
import org.graphiks.wgsl.ast.TranslationUnit
import org.graphiks.wgsl.ast.TypeAliasDecl
import org.graphiks.wgsl.ast.TypeCastExpr
import org.graphiks.wgsl.ast.TypeDecl
import org.graphiks.wgsl.ast.UnaryExpr
import org.graphiks.wgsl.ast.UnaryOperator
import org.graphiks.wgsl.ast.VariableDecl
import org.graphiks.wgsl.ast.VariableDeclKind
import org.graphiks.wgsl.ast.VariableDeclStatement
import org.graphiks.wgsl.ast.VectorType
import org.graphiks.wgsl.ast.VertexOutput
import org.graphiks.wgsl.ast.WhileStatement
import org.graphiks.wgsl.ir.Span

/**
 * Centralized builder for creating AST nodes.
 * 
 * This class provides helper methods for creating all types of AST nodes,
 * managing arenas, and tracking statistics.
 */
class AstBuilder {

    /** Statistics tracking */
    private var _declarationCount: Int = 0
    private var _expressionCount: Int = 0
    private var _typeCount: Int = 0
    private var _statementCount: Int = 0

    /** Reset all statistics */
    fun reset() {
        _declarationCount = 0
        _expressionCount = 0
        _typeCount = 0
        _statementCount = 0
    }

    // ========== Statistic Getters ==========

    val declarationCount: Int get() = _declarationCount
    val expressionCount: Int get() = _expressionCount
    val typeCount: Int get() = _typeCount
    val statementCount: Int get() = _statementCount

    val totalCount: Int get() = _declarationCount + _expressionCount + _typeCount + _statementCount

    // ========== TranslationUnit ==========

    fun TranslationUnit(
        declarations: List<GlobalDecl>,
        span: Span = Span.UNDEFINED
    ): TranslationUnit {
        return TranslationUnit(declarations, span)
    }

    // ========== Global Declarations ==========

    fun FunctionDecl(
        attributes: List<Attribute> = emptyList(),
        name: String,
        templateParams: List<TemplateParam> = emptyList(),
        parameters: List<Param> = emptyList(),
        returnType: TypeDecl? = null,
        body: BlockStatement? = null,
        span: Span = Span.UNDEFINED
    ): FunctionDecl {
        _declarationCount++
        return FunctionDecl(attributes, name, templateParams, parameters, returnType, body, span)
    }

    fun StructDecl(
        attributes: List<Attribute> = emptyList(),
        name: String,
        templateParams: List<TemplateParam> = emptyList(),
        members: List<StructMember> = emptyList(),
        span: Span = Span.UNDEFINED
    ): StructDecl {
        _declarationCount++
        return StructDecl(attributes, name, templateParams, members, span)
    }

    fun VariableDecl(
        kind: VariableDeclKind,
        attributes: List<Attribute> = emptyList(),
        name: String,
        storageClass: String? = null,
        accessMode: String? = null,
        type: TypeDecl? = null,
        initializer: Expression? = null,
        span: Span = Span.UNDEFINED
    ): VariableDecl {
        _declarationCount++
        return VariableDecl(kind, attributes, name, storageClass, accessMode, type, initializer, span)
    }

    fun TypeAliasDecl(
        attributes: List<Attribute> = emptyList(),
        name: String,
        templateParams: List<TemplateParam> = emptyList(),
        type: TypeDecl,
        span: Span = Span.UNDEFINED
    ): TypeAliasDecl {
        _declarationCount++
        return TypeAliasDecl(attributes, name, templateParams, type, span)
    }

    fun OverrideDecl(
        attributes: List<Attribute> = emptyList(),
        name: String,
        type: TypeDecl? = null,
        initializer: Expression? = null,
        span: Span = Span.UNDEFINED
    ): OverrideDecl {
        _declarationCount++
        return OverrideDecl(attributes, name, type, initializer, span)
    }

    fun EnableDirective(
        extensions: List<String>,
        span: Span = Span.UNDEFINED
    ): EnableDirective {
        _declarationCount++
        return EnableDirective(extensions, span)
    }

    fun RequiresDirective(
        features: List<String>,
        span: Span = Span.UNDEFINED
    ): RequiresDirective {
        _declarationCount++
        return RequiresDirective(features, span)
    }

    fun DiagnosticDirective(
        severity: String,
        rule: String,
        span: Span = Span.UNDEFINED
    ): DiagnosticDirective {
        _declarationCount++
        return DiagnosticDirective(severity, rule, span)
    }

    fun ConstAssertDecl(
        expression: Expression,
        span: Span = Span.UNDEFINED
    ): ConstAssertDecl {
        _declarationCount++
        return ConstAssertDecl(expression, span)
    }

    // ========== Types ==========

    fun scalarType(
        kind: ScalarKind,
        span: Span = Span.UNDEFINED
    ): ScalarType {
        _typeCount++
        return ScalarType(kind, span)
    }

    fun vectorType(
        size: Int,
        elementType: TypeDecl,
        span: Span = Span.UNDEFINED
    ): VectorType {
        _typeCount++
        return VectorType(size, elementType, span)
    }

    fun matrixType(
        columns: Int,
        rows: Int,
        elementType: TypeDecl,
        span: Span = Span.UNDEFINED
    ): MatrixType {
        _typeCount++
        return MatrixType(columns, rows, elementType, span)
    }

    fun ArrayType(
        elementType: TypeDecl,
        length: Expression? = null,
        stride: Int? = null,
        span: Span = Span.UNDEFINED
    ): ArrayType {
        _typeCount++
        return ArrayType(elementType, length, stride, span)
    }

    fun StructType(
        name: String,
        span: Span = Span.UNDEFINED
    ): StructType {
        _typeCount++
        return StructType(name, span)
    }

    fun NamedType(
        name: String,
        span: Span = Span.UNDEFINED
    ): NamedType {
        _typeCount++
        return NamedType(name, span)
    }

    fun PointerType(
        storageClass: StorageClass,
        elementType: TypeDecl,
        span: Span = Span.UNDEFINED
    ): PointerType {
        _typeCount++
        return PointerType(storageClass, elementType, span)
    }

    fun ReferenceType(
        storageClass: StorageClass,
        elementType: TypeDecl,
        span: Span = Span.UNDEFINED
    ): ReferenceType {
        _typeCount++
        return ReferenceType(storageClass, elementType, span)
    }

    fun TemplateType(
        name: String,
        args: List<TypeDecl> = emptyList(),
        span: Span = Span.UNDEFINED
    ): TemplateType {
        _typeCount++
        return TemplateType(name, args, span)
    }

    fun AtomicType(
        elementType: TypeDecl,
        span: Span = Span.UNDEFINED
    ): AtomicType {
        _typeCount++
        return AtomicType(elementType, span)
    }

    fun SamplerType(
        isComparison: Boolean,
        span: Span = Span.UNDEFINED
    ): SamplerType {
        _typeCount++
        return SamplerType(isComparison, span)
    }

    fun TextureType(
        kind: TextureKind,
        elementType: TypeDecl? = null,
        accessMode: String? = null,
        span: Span = Span.UNDEFINED
    ): TextureType {
        _typeCount++
        return TextureType(kind, elementType, accessMode, span)
    }

    fun ConstantType(
        expression: Expression,
        span: Span = Span.UNDEFINED
    ): ConstantType {
        _typeCount++
        return ConstantType(expression, span)
    }

    fun RayQueryType(
        span: Span = Span.UNDEFINED
    ): RayQueryType {
        _typeCount++
        return RayQueryType(span)
    }

    // ========== Expressions ==========

    fun IntLiteral(
        value: Long,
        suffix: String? = null,
        span: Span = Span.UNDEFINED
    ): IntLiteral {
        _expressionCount++
        return IntLiteral(value, suffix, span)
    }

    fun FloatLiteral(
        value: Double,
        suffix: String? = null,
        span: Span = Span.UNDEFINED
    ): FloatLiteral {
        _expressionCount++
        return FloatLiteral(value, suffix, span)
    }

    fun BoolLiteral(
        value: Boolean,
        span: Span = Span.UNDEFINED
    ): BoolLiteral {
        _expressionCount++
        return BoolLiteral(value, span)
    }

    fun StringLiteral(
        value: String,
        span: Span = Span.UNDEFINED
    ): StringLiteral {
        _expressionCount++
        return StringLiteral(value, span)
    }

    fun IdentExpr(
        name: String,
        span: Span = Span.UNDEFINED
    ): IdentExpr {
        _expressionCount++
        return IdentExpr(name, span)
    }

    fun CallExpr(
        callee: Expression,
        args: List<Expression> = emptyList(),
        templateArgs: List<TypeDecl>? = null,
        span: Span = Span.UNDEFINED
    ): CallExpr {
        _expressionCount++
        return CallExpr(callee, args, templateArgs, span)
    }

    fun MemberAccessExpr(
        objectExpr: Expression,
        member: String,
        span: Span = Span.UNDEFINED
    ): MemberAccessExpr {
        _expressionCount++
        return MemberAccessExpr(objectExpr, member, span)
    }

    fun IndexExpr(
        objectExpr: Expression,
        index: Expression,
        span: Span = Span.UNDEFINED
    ): IndexExpr {
        _expressionCount++
        return IndexExpr(objectExpr, index, span)
    }

    fun UnaryExpr(
        op: UnaryOperator,
        operand: Expression,
        span: Span = Span.UNDEFINED
    ): UnaryExpr {
        _expressionCount++
        return UnaryExpr(op, operand, span)
    }

    fun BinaryExpr(
        left: Expression,
        op: BinaryOperator,
        right: Expression,
        span: Span = Span.UNDEFINED
    ): BinaryExpr {
        _expressionCount++
        return BinaryExpr(left, op, right, span)
    }

    fun TernaryExpr(
        condition: Expression,
        trueExpr: Expression,
        falseExpr: Expression,
        span: Span = Span.UNDEFINED
    ): TernaryExpr {
        _expressionCount++
        return TernaryExpr(condition, trueExpr, falseExpr, span)
    }

    fun TypeCastExpr(
        expr: Expression,
        type: TypeDecl,
        span: Span = Span.UNDEFINED
    ): TypeCastExpr {
        _expressionCount++
        return TypeCastExpr(expr, type, span)
    }

    fun SwizzleExpr(
        objectExpr: Expression,
        components: List<String>,
        span: Span = Span.UNDEFINED
    ): SwizzleExpr {
        _expressionCount++
        return SwizzleExpr(objectExpr, components, span)
    }

    fun BitcastExpr(
        expr: Expression,
        type: TypeDecl,
        span: Span = Span.UNDEFINED
    ): BitcastExpr {
        _expressionCount++
        return BitcastExpr(expr, type, span)
    }

    // ========== Statements ==========

    fun BlockStatement(
        statements: List<Statement> = emptyList(),
        span: Span = Span.UNDEFINED
    ): BlockStatement {
        _statementCount++
        return BlockStatement(statements, span)
    }

    fun IfStatement(
        condition: Expression,
        thenBranch: Statement,
        elseBranch: Statement? = null,
        span: Span = Span.UNDEFINED
    ): IfStatement {
        _statementCount++
        return IfStatement(condition, thenBranch, elseBranch, span)
    }

    fun SwitchStatement(
        expression: Expression,
        body: SwitchBody,
        span: Span = Span.UNDEFINED
    ): SwitchStatement {
        _statementCount++
        return SwitchStatement(expression, body, span)
    }

    fun SwitchBody(
        cases: List<SwitchCase> = emptyList(),
        span: Span = Span.UNDEFINED
    ): SwitchBody {
        return SwitchBody(cases, span)
    }

    fun Case(
        selectors: List<Expression>,
        isDefault: Boolean = false,
        body: BlockStatement,
        span: Span = Span.UNDEFINED
    ): Case {
        return Case(selectors, isDefault, body, span)
    }

    fun DefaultCase(
        body: BlockStatement,
        span: Span = Span.UNDEFINED
    ): DefaultCase {
        return DefaultCase(body, span)
    }

    fun LoopStatement(
        body: BlockStatement,
        continuing: BlockStatement? = null,
        span: Span = Span.UNDEFINED
    ): LoopStatement {
        _statementCount++
        return LoopStatement(body, continuing, span)
    }

    fun WhileStatement(
        condition: Expression,
        body: BlockStatement,
        continuing: BlockStatement? = null,
        span: Span = Span.UNDEFINED
    ): WhileStatement {
        _statementCount++
        return WhileStatement(condition, body, continuing, span)
    }

    fun ForStatement(
        init: Statement? = null,
        condition: Expression? = null,
        update: Expression? = null,
        body: BlockStatement,
        span: Span = Span.UNDEFINED
    ): ForStatement {
        _statementCount++
        return ForStatement(init, condition, update, body, span)
    }

    fun BreakStatement(
        span: Span = Span.UNDEFINED
    ): BreakStatement {
        _statementCount++
        return BreakStatement(span)
    }

    fun BreakIfStatement(
        condition: Expression,
        span: Span = Span.UNDEFINED
    ): BreakIfStatement {
        _statementCount++
        return BreakIfStatement(condition, span)
    }

    fun ContinueStatement(
        span: Span = Span.UNDEFINED
    ): ContinueStatement {
        _statementCount++
        return ContinueStatement(span)
    }

    fun ReturnStatement(
        value: Expression? = null,
        span: Span = Span.UNDEFINED
    ): ReturnStatement {
        _statementCount++
        return ReturnStatement(value, span)
    }

    fun DiscardStatement(
        span: Span = Span.UNDEFINED
    ): DiscardStatement {
        _statementCount++
        return DiscardStatement(span)
    }

    fun VariableDeclStatement(
        kind: VariableDeclKind,
        name: String,
        storageClass: String? = null,
        accessMode: String? = null,
        type: TypeDecl? = null,
        initializer: Expression? = null,
        span: Span = Span.UNDEFINED
    ): VariableDeclStatement {
        _statementCount++
        return VariableDeclStatement(kind, name, storageClass, accessMode, type, initializer, span)
    }

    fun AssignmentStatement(
        lhs: Expression,
        rhs: Expression,
        op: BinaryOperator? = null,
        span: Span = Span.UNDEFINED
    ): AssignmentStatement {
        _statementCount++
        return AssignmentStatement(lhs, rhs, op, span)
    }

    fun PhonyAssignmentStatement(
        expression: Expression,
        span: Span = Span.UNDEFINED
    ): PhonyAssignmentStatement {
        _statementCount++
        return PhonyAssignmentStatement(expression, span)
    }

    fun IncDecStatement(
        expr: Expression,
        isIncrement: Boolean,
        span: Span = Span.UNDEFINED
    ): IncDecStatement {
        _statementCount++
        return IncDecStatement(expr, isIncrement, span)
    }

    fun ExpressionStatement(
        expr: Expression,
        span: Span = Span.UNDEFINED
    ): ExpressionStatement {
        _statementCount++
        return ExpressionStatement(expr, span)
    }

    // ========== Miscellaneous ==========

    fun Attribute(
        name: String,
        args: List<Expression> = emptyList(),
        span: Span = Span.UNDEFINED
    ): Attribute {
        return Attribute(name, args, span)
    }

    fun Param(
        attributes: List<Attribute> = emptyList(),
        name: String,
        type: TypeDecl,
        defaultValue: Expression? = null,
        span: Span = Span.UNDEFINED
    ): Param {
        return Param(attributes, name, type, defaultValue, span)
    }

    fun TemplateParam(
        name: String,
        constraint: TypeDecl? = null,
        span: Span = Span.UNDEFINED
    ): TemplateParam {
        return TemplateParam(name, constraint, span)
    }

    fun StructMember(
        attributes: List<Attribute> = emptyList(),
        name: String,
        type: TypeDecl,
        defaultValue: Expression? = null,
        span: Span = Span.UNDEFINED
    ): StructMember {
        return StructMember(attributes, name, type, defaultValue, span)
    }

    fun entryPointCompute(): EntryPointAttribute.Compute {
        return EntryPointAttribute.Compute
    }

    fun entryPointFragment(
        inputs: List<FragmentInput> = emptyList()
    ): EntryPointAttribute.Fragment {
        return EntryPointAttribute.Fragment(inputs)
    }

    fun entryPointVertex(
        outputs: List<VertexOutput> = emptyList()
    ): EntryPointAttribute.Vertex {
        return EntryPointAttribute.Vertex(outputs)
    }

    fun fragmentInput(
        location: Int? = null,
        builtin: BuiltinValue? = null,
        type: TypeDecl
    ): FragmentInput {
        return FragmentInput(location, builtin, type)
    }

    fun vertexOutput(
        location: Int? = null,
        builtin: BuiltinValue? = null,
        type: TypeDecl
    ): VertexOutput {
        return VertexOutput(location, builtin, type)
    }
}
