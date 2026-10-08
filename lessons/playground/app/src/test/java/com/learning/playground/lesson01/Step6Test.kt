package com.learning.playground.lesson01

import org.junit.Assert.assertEquals
import org.junit.Test

class Step6Test {
    @Test
    fun `6_1 parsePrice reads price with unit`() {
        assertEquals(55, parsePrice("55 บาท"))
        assertEquals(120, parsePrice("120 บาท"))
    }

    @Test
    fun `6_1 parsePrice still reads plain number`() {
        assertEquals(65, parsePrice("65"))
    }

    @Test
    fun `6_1 loadPromotion shows today promotion`() {
        assertEquals("โปรวันนี้: ลาเต้เย็น เหลือ 55 บาท", loadPromotion())
    }
}
