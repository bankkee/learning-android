package lesson00

import org.junit.Test
import kotlin.test.assertEquals

class Step6Test {
    @Test
    fun `6_1 openingText`() {
        assertEquals("ร้านกาแฟ เปิดแล้ว", ShopConfig.openingText())
    }

    @Test
    fun `6_2 newInstance`() {
        val intent = DetailScreen.newInstance("มอคค่า")
        assertEquals("DetailScreen", intent.target)
        assertEquals("มอคค่า", intent.getStringExtra("EXTRA_COFFEE_NAME"))
    }

    @Test
    fun `6_3 readCoffeeName`() {
        assertEquals("มอคค่า", DetailScreen.readCoffeeName(DetailScreen.newInstance("มอคค่า")))
        assertEquals("", DetailScreen.readCoffeeName(FakeIntent("DetailScreen")))
    }
}
