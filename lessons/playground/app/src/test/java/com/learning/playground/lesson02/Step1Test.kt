package com.learning.playground.lesson02

import com.learning.playground.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class Step1Test {
    private val layout = parseLayout("lesson02_step1_card")
    private val card = layout.byId("cardMenu")

    @Test
    fun `1_1 card fills the width`() {
        assertEquals("match_parent", card.android("layout_width"))
    }

    @Test
    fun `1_1 card is 16dp from the screen edge`() {
        assertTrue("cardMenu ต้องมี layout_margin 16dp ทุกด้าน", card.has16dpAllSides("layout_margin"))
    }

    @Test
    fun `1_1 text is 16dp from the card edge`() {
        assertTrue("cardMenu ต้องมี padding 16dp ทุกด้าน", card.has16dpAllSides("padding"))
    }

    @Test
    fun `1_2 text size uses sp`() {
        val size = layout.byId("tvName").android("textSize")
        assertTrue("textSize ของ tvName เป็น $size ต้องใช้หน่วย sp", size.endsWith("sp"))
    }
}
