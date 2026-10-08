package com.learning.playground.lesson01

import com.learning.playground.byId
import com.learning.playground.android
import com.learning.playground.mainFile
import com.learning.playground.parseLayout
import com.learning.playground.stringResource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class Step4Test {
    @Test
    fun `4_1 shop_order is in strings and used by btnOrder`() {
        assertEquals("สั่งกาแฟ", stringResource("shop_order"))

        val button = parseLayout("activity_coffee_shop").byId("btnOrder")
        assertEquals("@string/shop_order", button.android("text"))
    }

    @Test
    fun `4_2 shop_order_count is in strings and used by the Activity`() {
        val text = stringResource("shop_order_count")
        assertTrue(
            "shop_order_count ต้องเป็น \"สั่งไปแล้ว %1\$d แก้ว\" แต่ได้ $text",
            text == "สั่งไปแล้ว %1\$d แก้ว" || text == "สั่งไปแล้ว %d แก้ว"
        )

        val source = mainFile("java/com/learning/playground/lesson01/CoffeeShopActivity.kt").readText()
        assertTrue("Activity ยังไม่ได้ใช้ R.string.shop_order_count", "R.string.shop_order_count" in source)
        assertFalse("ยังมีข้อความเขียนตรงใน Activity", "\"สั่งไปแล้ว" in source)
    }
}
