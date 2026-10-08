package lesson00

import org.junit.Test
import kotlin.test.assertEquals

class Step2Test {
    @Test
    fun `2_1 displayName`() {
        assertEquals("สมชาย", displayName("สมชาย"))
        assertEquals("ไม่ระบุชื่อ", displayName(null))
    }

    @Test
    fun `2_2 nameLength`() {
        assertEquals(5, nameLength("latte"))
        assertEquals(0, nameLength(null))
    }
}
