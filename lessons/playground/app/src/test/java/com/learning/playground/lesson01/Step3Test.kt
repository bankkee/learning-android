package com.learning.playground.lesson01

import com.learning.playground.android
import com.learning.playground.children
import com.learning.playground.parseXml
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class Step3Test {
    private val activities = parseXml("AndroidManifest.xml").children("activity")

    @Test
    fun `3_1 CoffeeShopActivity is portrait`() {
        val activity = activities.first { it.android("name").endsWith("CoffeeShopActivity") }
        assertEquals("portrait", activity.android("screenOrientation"))
    }

    @Test
    fun `3_1 HomeActivity is still the launcher`() {
        val home = activities.first { it.android("name").endsWith("HomeActivity") }
        val actions = home.children("action").map { it.android("name") }
        val categories = home.children("category").map { it.android("name") }
        assertTrue("ไม่มี intent-filter ของหน้าแรก", "android.intent.action.MAIN" in actions)
        assertTrue("ไม่มี intent-filter ของหน้าแรก", "android.intent.category.LAUNCHER" in categories)
    }
}
