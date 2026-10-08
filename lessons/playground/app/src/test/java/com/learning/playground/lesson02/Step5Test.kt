package com.learning.playground.lesson02

import com.learning.playground.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class Step5Test {
    private val layout = parseLayout("lesson02_step5_panel")

    private fun link(id: String, attribute: String): String {
        return layout.byId(id).app(attribute).idName()
    }

    @Test
    fun `5_1 size buttons form a chain`() {
        assertEquals("parent", link("btnSizeS", "layout_constraintStart_toStartOf"))
        assertEquals("btnSizeM", link("btnSizeS", "layout_constraintEnd_toStartOf"))
        assertEquals("btnSizeS", link("btnSizeM", "layout_constraintStart_toEndOf"))
        assertEquals("btnSizeL", link("btnSizeM", "layout_constraintEnd_toStartOf"))
        assertEquals("btnSizeM", link("btnSizeL", "layout_constraintStart_toEndOf"))
        assertEquals("parent", link("btnSizeL", "layout_constraintEnd_toEndOf"))
    }

    @Test
    fun `5_2 barrier follows both labels`() {
        val barrier = layout.byId("barrierLabels")
        assertEquals("Barrier", barrier.shortTag())
        assertTrue(barrier.app("barrierDirection") in setOf("end", "right"))
        val ids = barrier.app("constraint_referenced_ids").split(",").map { it.trim() }
        assertTrue("barrier ต้องอ้างถึง tvLabelName", "tvLabelName" in ids)
        assertTrue("barrier ต้องอ้างถึง tvLabelPhone", "tvLabelPhone" in ids)
    }

    @Test
    fun `5_2 values start after the barrier`() {
        assertEquals("barrierLabels", link("tvValueName", "layout_constraintStart_toEndOf"))
        assertEquals("barrierLabels", link("tvValuePhone", "layout_constraintStart_toEndOf"))
    }
}
