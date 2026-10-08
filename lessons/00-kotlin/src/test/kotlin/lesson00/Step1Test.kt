package lesson00

import org.junit.Test
import kotlin.test.assertEquals

class Step1Test {
    @Test
    fun `1_1 menuLabel`() {
        assertEquals("ลาเต้ · 65 บาท", menuLabel("ลาเต้", 65))
        assertEquals("มอคค่า · 75 บาท", menuLabel("มอคค่า", 75))
    }

    @Test
    fun `1_2 totalPrice`() {
        assertEquals(130, totalPrice(65, 2))
        assertEquals(0, totalPrice(65, 0))
    }
}
