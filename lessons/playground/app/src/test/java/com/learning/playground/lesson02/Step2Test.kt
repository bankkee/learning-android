package com.learning.playground.lesson02

import com.learning.playground.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class Step2Test {
    private val layout = parseLayout("lesson02_step2_row_linear")
    private val row = layout.byId("rowMenu")

    @Test
    fun `2_1 row is horizontal`() {
        assertEquals("horizontal", row.android("orientation"))
    }

    @Test
    fun `2_1 name and description share a vertical group that takes the remaining width`() {
        val group = layout.byId("tvName").parent()
        assertTrue("tvName กับ tvDescription ต้องอยู่ใน LinearLayout ชั้นในตัวเดียวกัน", group !== row)
        assertEquals("tvDescription ต้องอยู่ในก้อนเดียวกับ tvName", group, layout.byId("tvDescription").parent())
        assertEquals("LinearLayout", group.shortTag())
        assertEquals("vertical", group.android("orientation"))
        assertEquals("0dp", group.android("layout_width"))
        assertEquals("1", group.android("layout_weight"))
    }

    @Test
    fun `2_1 image and price stay in the row`() {
        assertEquals(row, layout.byId("ivCoffee").parent())
        assertEquals(row, layout.byId("tvPrice").parent())
    }
}
