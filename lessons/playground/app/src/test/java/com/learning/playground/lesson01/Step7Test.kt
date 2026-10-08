package com.learning.playground.lesson01

import org.junit.Assert.assertEquals
import org.junit.Test

class Step7Test {
    @Test
    fun `7_1 no discount below 3 cups`() {
        assertEquals(0, totalPrice(0))
        assertEquals(120, totalPrice(2))
    }

    @Test
    fun `7_1 discount starts at 3 cups`() {
        assertEquals(150, totalPrice(3))
        assertEquals(200, totalPrice(4))
    }
}
