package lesson00

import org.junit.Test
import kotlin.test.assertEquals

class Step7Test {
    @Test
    fun `7_1 describe`() {
        assertEquals("กำลังโหลด", describe(LoadState.Loading))
        assertEquals("ได้เมนู ลาเต้", describe(LoadState.Success("ลาเต้")))
        assertEquals("ผิดพลาด: เชื่อมต่อไม่ได้", describe(LoadState.Error("เชื่อมต่อไม่ได้")))
    }

    @Test
    fun `7_2 isFinished`() {
        assertEquals(false, isFinished(LoadState.Loading))
        assertEquals(true, isFinished(LoadState.Success("ลาเต้")))
        assertEquals(true, isFinished(LoadState.Error("x")))
    }
}
