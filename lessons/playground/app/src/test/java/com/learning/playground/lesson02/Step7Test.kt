package com.learning.playground.lesson02

import com.learning.playground.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class Step7Test {
    private val list = parseLayout("activity_menu_list").byId("rvMenu")

    @Test
    fun `7_1 editor previews the real row layout`() {
        assertEquals("@layout/item_menu", list.tools("listitem"))
    }

    @Test
    fun `7_2 a row is as tall as its content`() {
        assertEquals("wrap_content", parseLayout("item_menu").android("layout_height"))
        assertEquals("RecyclerView ต้องเต็มจอเหมือนเดิม", "match_parent", list.android("layout_height"))
    }

    @Test
    fun `7_3 list is a two column grid`() {
        val manager = list.app("layoutManager")
        assertTrue("layoutManager เป็น $manager ต้องเป็น GridLayoutManager", manager.endsWith("GridLayoutManager"))
        assertEquals("2", list.app("spanCount"))
    }
}
