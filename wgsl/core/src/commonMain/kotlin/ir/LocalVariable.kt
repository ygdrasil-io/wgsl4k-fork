package org.graphiks.wgsl.ir

import org.graphiks.wgsl.arena.Handle
import kotlinx.serialization.Serializable

/**
 * A local variable within a function.
 */
@Serializable
data class LocalVariable(
    /**
     * The name of the variable (may be empty for unnamed variables).
     */
    val name: String,

    /**
     * The type of the variable.
     */
    val type: Handle<Type>,

    /**
     * The initial value expression (if any).
     */
    val init: Handle<Expression>? = null,
) {
    override fun toString(): String = if (name.isNotEmpty()) name else "local_${type.index}"
}
