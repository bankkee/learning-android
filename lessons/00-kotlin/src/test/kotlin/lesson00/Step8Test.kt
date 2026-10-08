package lesson00

import org.junit.Test
import kotlin.test.assertEquals

class Step8Test {
    @Test
    fun `8_1 shout`() {
        assertEquals("LATTE!", shout("latte"))
        assertEquals("", shout(null))
    }

    @Test
    fun `8_2 buildOrder`() {
        val order = buildOrder("ลาเต้", 2)
        assertEquals("ลาเต้", order.menu)
        assertEquals(2, order.cups)
    }

    @Test
    fun `8_3 unwrapOr`() {
        assertEquals("ลาเต้", unwrapOr(Box("ลาเต้"), "ไม่มี"))
        assertEquals("ไม่มี", unwrapOr(null, "ไม่มี"))
        assertEquals(65, unwrapOr(Box(65), 0))
    }
}
