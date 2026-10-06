package com.jarves.mh.ui

/** Keeps only the most recent characters while a process is producing output. */
internal class BoundedTextBuffer(private val maxCharacters: Int) {
    private val value = StringBuilder()

    init {
        require(maxCharacters > 0) { "maxCharacters must be positive" }
    }

    fun append(text: String) {
        if (text.isEmpty()) return
        value.append(text)
        val excess = value.length - maxCharacters
        if (excess > 0) value.delete(0, excess)
    }

    fun takeLast(characters: Int): String {
        require(characters >= 0) { "characters must not be negative" }
        return value.substring((value.length - characters).coerceAtLeast(0))
    }

    override fun toString(): String = value.toString()
}
