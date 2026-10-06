package com.jarves.mh.ui

import org.junit.Assert.assertEquals
import org.junit.Test

class BoundedTextBufferTest {
    @Test
    fun keepsOnlyNewestCharactersWhenInputExceedsLimit() {
        val buffer = BoundedTextBuffer(maxCharacters = 5)

        buffer.append("abc")
        buffer.append("defg")

        assertEquals("cdefg", buffer.toString())
    }

    @Test
    fun supportsSeveralSmallAppendsAndEmptyInput() {
        val buffer = BoundedTextBuffer(maxCharacters = 4)

        buffer.append("ab")
        buffer.append("")
        buffer.append("c")
        buffer.append("de")

        assertEquals("bcde", buffer.toString())
    }
}
