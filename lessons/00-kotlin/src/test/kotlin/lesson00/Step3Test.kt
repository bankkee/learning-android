package lesson00

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class Step3Test {
    @Test
    fun `3_1 priceAfterDiscount`() {
        assertEquals(80, priceAfterDiscount(100, 20))
        assertEquals(100, priceAfterDiscount(100, 0))
        assertEquals(60, priceAfterDiscount(80, 25))
    }

    @Test
    fun `3_1 discountPercent has default value`() {
        val function = Class.forName("lesson00.Step3FunctionsKt").methods
            .any { it.name == "priceAfterDiscount\$default" }
        assertTrue(function, "discountPercent ยังไม่มีค่าเริ่มต้น ให้แก้เป็น discountPercent: Int = 0")
    }
}
