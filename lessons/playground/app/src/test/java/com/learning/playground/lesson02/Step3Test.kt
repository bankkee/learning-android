package com.learning.playground.lesson02

import com.learning.playground.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class Step3Test {
    private val layout = parseLayout("lesson02_step3_detail")

    @Test
    fun `3_1 badge sits on the image at the top end corner`() {
        val badge = layout.byId("tvBadge")
        assertEquals("tvBadge ต้องอยู่ใน frameImage", layout.byId("frameImage"), badge.parent())
        val gravity = badge.android("layout_gravity").split("|").map { it.trim() }
        assertTrue("layout_gravity ของ tvBadge ต้องมี top", "top" in gravity)
        assertTrue("layout_gravity ของ tvBadge ต้องมี end", "end" in gravity || "right" in gravity)
    }

    @Test
    fun `3_2 the whole screen scrolls`() {
        assertTrue(
            "tag นอกสุดเป็น ${layout.tagName} ต้องเป็น ScrollView",
            layout.shortTag() in setOf("ScrollView", "NestedScrollView")
        )
        assertEquals("ScrollView มีลูกได้ตัวเดียว", 1, layout.directChildren().size)
        assertTrue("ปุ่มต้องอยู่ในส่วนที่เลื่อนได้", layout.hasId("btnOrderThis"))
    }
}
