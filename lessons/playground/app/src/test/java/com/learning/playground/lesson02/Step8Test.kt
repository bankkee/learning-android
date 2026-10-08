package com.learning.playground.lesson02

import com.learning.playground.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class Step8Test {
    private val layout = parseLayout("lesson02_step8_reuse")
    private val labels = listOf("tvLabelOpen", "tvLabelPhone", "tvLabelAddress").map { layout.byId(it) }

    @Test
    fun `8_1 header comes from include`() {
        val includes = layout.children("include").map { it.getAttribute("layout") }
        assertTrue("ยังไม่มี <include layout=\"@layout/lesson02_header\" />", "@layout/lesson02_header" in includes)
        assertFalse("ก้อนหัวที่เขียนซ้ำยังอยู่ในไฟล์", layout.hasId("tvHeaderTitle"))
    }

    @Test
    fun `8_2 no literal color or 16dp in the layout`() {
        val values = layout.all().flatMap { it.attributeValues() }
        assertEquals("ยังมีสีที่เขียนตรง ๆ", emptyList<String>(), values.filter { it.startsWith("#") })
        assertEquals("ยังมี 16dp ที่เขียนตรง ๆ", emptyList<String>(), values.filter { it == "16dp" })
    }

    @Test
    fun `8_3 labels share the ShopInfoLabel style`() {
        val style = styleResource("ShopInfoLabel") ?: error("ยังไม่มี style ชื่อ ShopInfoLabel ใน res/values/")
        val items = style.children("item").associate { it.getAttribute("name") to it.textContent.trim() }
        assertTrue("style ต้องมี android:textColor", "android:textColor" in items)
        assertTrue("style ต้องมี android:textSize", "android:textSize" in items)
        assertTrue("ค่าใน style ต้องอ้างถึง @color ไม่ใช่เขียนสีตรง ๆ", items.values.none { it.startsWith("#") })

        labels.forEach { label ->
            val id = label.android("id").idName()
            assertEquals("$id ยังไม่ได้ใช้ style", "@style/ShopInfoLabel", label.getAttribute("style"))
            assertEquals("$id ยังมี textColor ซ้ำกับ style", "", label.android("textColor"))
            assertEquals("$id ยังมี textSize ซ้ำกับ style", "", label.android("textSize"))
        }
    }
}
