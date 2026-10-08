package com.learning.playground.lesson01

// บทที่ 01 — โปรโมชันของวัน (ใช้ในขั้นที่ 6)

data class Promotion(val menuName: String, val priceText: String)

// ข้อมูลมาเป็นข้อความแบบเดียวกับที่ได้จาก server ห้ามแก้
private val todayPromotion = Promotion(menuName = "ลาเต้เย็น", priceText = "55 บาท")

fun parsePrice(priceText: String): Int {
    return priceText.filter { it.isDigit() }.toInt()
}

fun loadPromotion(): String {
    try {
        val price = parsePrice(todayPromotion.priceText)
        return "โปรวันนี้: ${todayPromotion.menuName} เหลือ $price บาท"
    } catch (e: Exception) {
        throw IllegalStateException("โหลดโปรโมชันไม่ได้", e)
    }
}
