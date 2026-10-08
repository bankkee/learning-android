package com.learning.playground.lesson02

import com.learning.playground.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class Step4Test {
    private val layout = parseLayout("lesson02_step4_row_constraint")
    private val name = layout.byId("tvName")
    private val description = layout.byId("tvDescription")

    @Test
    fun `4_1 name stretches between image and price`() {
        assertEquals("ivCoffee", name.app("layout_constraintStart_toEndOf").idName())
        assertEquals("tvPrice", name.app("layout_constraintEnd_toStartOf").idName())
        assertEquals("0dp", name.android("layout_width"))
    }

    @Test
    fun `4_1 description is under the name`() {
        assertEquals("tvName", description.app("layout_constraintTop_toBottomOf").idName())
        val start = listOf("layout_constraintStart_toStartOf", "layout_constraintStart_toEndOf")
            .map { description.app(it) }
        assertTrue("tvDescription ยังไม่มี constraint แนวนอน", start.any { it.isNotEmpty() })
    }

    @Test
    fun `4_1 no nested layout`() {
        val children = layout.directChildren()
        assertEquals("View ทั้งสี่ตัวต้องเป็นลูกชั้นแรกของ ConstraintLayout", 4, children.size)
        assertTrue("ห้ามเพิ่ม layout ซ้อน", children.all { it.directChildren().isEmpty() })
    }
}
