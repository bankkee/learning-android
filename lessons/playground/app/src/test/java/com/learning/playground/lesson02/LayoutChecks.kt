package com.learning.playground.lesson02

import com.learning.playground.android
import org.w3c.dom.Element

// ตัวช่วยของ test บทที่ 02

private val SIXTEEN = setOf("16dp", "@dimen/default_16")

/**
 * จริงเมื่อระยะ [prefix] ("layout_margin" หรือ "padding") เป็น 16dp ครบสี่ด้าน
 * ไม่ว่าจะเขียนรวมตัวเดียว เขียนแยกแนวนอนกับแนวตั้ง หรือเขียนแยกทีละด้าน
 */
fun Element.has16dpAllSides(prefix: String): Boolean {
    val all = android(prefix)
    fun side(vararg names: String): String {
        return names.map { android(prefix + it) }.firstOrNull { it.isNotEmpty() } ?: all
    }
    val sides = listOf(
        side("Start", "Left", "Horizontal"),
        side("End", "Right", "Horizontal"),
        side("Top", "Vertical"),
        side("Bottom", "Vertical")
    )
    return sides.all { it in SIXTEEN }
}

/** tag แบบสั้น เช่น "androidx.constraintlayout.widget.Barrier" กลายเป็น "Barrier" */
fun Element.shortTag(): String = tagName.substringAfterLast(".")
