package com.learning.playground.lesson02

import com.learning.playground.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class Step9Test {
    private val layout = parseLayout("activity_order_form")

    // โค้ดของ Activity ที่ตัดบรรทัด comment ออกแล้ว
    private val source = mainFile("java/com/learning/playground/lesson02/OrderFormActivity.kt")
        .readLines()
        .filterNot { it.trim().startsWith("//") }
        .joinToString("\n")

    @Test
    fun `9_1 stepper is in the form and limited to 5`() {
        val stepper = layout.byId("stepperCups")
        assertEquals("com.learning.playground.lesson02.QuantityStepperView", stepper.tagName)
        assertEquals("5", stepper.app("maxQuantity"))
    }

    @Test
    fun `9_2 switch shows and hides the address`() {
        assertTrue("ยังไม่ได้ฟังการเปลี่ยนของ switchDelivery", "switchDelivery.setOnCheckedChangeListener" in source)
        assertTrue("ยังไม่ได้ใช้ tilAddress ในโค้ด", "binding.tilAddress" in source)
    }

    @Test
    fun `9_3 submit shows the summary`() {
        assertTrue("ยังไม่ได้ฟังการกดของ btnSubmit", "btnSubmit.setOnClickListener" in source)
        assertTrue("ยังไม่ได้เรียก orderSummary", "orderSummary(" in source)
        assertTrue("ยังไม่ได้แสดงผลใน tvSummary", "binding.tvSummary" in source)
        assertTrue("ยังไม่ได้อ่านจำนวนจาก stepperCups", "binding.stepperCups" in source)
    }
}
