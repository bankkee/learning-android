package lesson00

import org.junit.Test
import kotlin.test.assertEquals

class Step4Test {
    @Test
    fun `4_1 affordablePrices`() {
        assertEquals(listOf(55, 60, 65), affordablePrices(listOf(55, 60, 65, 75, 80), 65))
        assertEquals(emptyList(), affordablePrices(listOf(75, 80), 50))
    }

    @Test
    fun `4_2 handleResult`() {
        val calls = mutableListOf<String>()
        handleResult(true, onSuccess = { calls.add("success") }, onError = { calls.add("error") })
        handleResult(false, onSuccess = { calls.add("success") }, onError = { calls.add("error") })
        assertEquals(listOf("success", "error"), calls)
    }

    @Test
    fun `4_3 countClicks`() {
        assertEquals(3, countClicks(FakeButton(), 3))
        assertEquals(0, countClicks(FakeButton(), 0))
    }
}
