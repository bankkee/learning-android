package com.learning.playground.lesson02

import com.learning.playground.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class Step6Test {
    private val layout = parseLayout("activity_order_form")

    @Test
    fun `6_1 phone field uses the phone keyboard`() {
        assertEquals("phone", layout.byId("etPhone").android("inputType"))
    }

    @Test
    fun `6_2 sizes are horizontal with M selected`() {
        val group = layout.byId("rgSize")
        assertEquals("horizontal", group.android("orientation"))
        assertEquals("rbSizeM", group.android("checkedButton").idName())
    }

    @Test
    fun `6_3 address and progress are gone`() {
        assertEquals("gone", layout.byId("tilAddress").android("visibility"))
        assertEquals("gone", layout.byId("progressOrder").android("visibility"))
    }
}
