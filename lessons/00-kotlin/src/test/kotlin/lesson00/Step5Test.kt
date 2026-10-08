package lesson00

import org.junit.Test
import kotlin.test.assertEquals

class Step5Test {
    @Test
    fun `5_1 Counter increment`() {
        val counter = Counter()
        counter.increment()
        counter.increment()
        assertEquals(2, counter.count)
    }

    @Test
    fun `5_2 MenuItem label`() {
        assertEquals("ลาเต้ · 65 บาท", MenuItem("ลาเต้", 65).label())
    }

    @Test
    fun `5_3 raisePrice`() {
        val original = MenuItem("ลาเต้", 65)
        assertEquals(MenuItem("ลาเต้", 70), raisePrice(original, 5))
        assertEquals(65, original.price)
    }
}
