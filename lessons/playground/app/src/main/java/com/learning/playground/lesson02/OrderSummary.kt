package com.learning.playground.lesson02

// บทที่ 02 — ข้อความสรุปการสั่ง (ใช้ในขั้นที่ 9) เขียนไว้แล้ว ไม่ต้องแก้

fun orderSummary(name: String, size: String, extraShot: Boolean, cups: Int): String {
    val customer = name.trim().ifEmpty { "ลูกค้า" }
    val shot = if (extraShot) " เพิ่มช็อต" else ""
    return "$customer สั่งขนาด $size$shot $cups แก้ว"
}
