package lesson00

// ขั้นที่ 3 — function

// รูปเต็ม
fun greetFull(name: String): String {
    return "สวัสดี $name"
}

// รูปย่อ: ถ้ามีแค่นิพจน์เดียว ใช้ = แทนปีกกาและ return ได้
fun greetShort(name: String): String = "สวัสดี $name"

// ค่าเริ่มต้นของ parameter: คนเรียกไม่ส่งก็ได้
fun greet(name: String, greeting: String = "สวัสดี"): String = "$greeting $name"

// function ที่ไม่คืนค่า ชนิดที่คืนคือ Unit ซึ่งละไว้ได้
fun printReceipt(menu: String, price: Int) {
    println("$menu $price บาท")
}

fun main() {
    println(greet("สมชาย"))
    println(greet("สมชาย", "อรุณสวัสดิ์"))
    println(greet(greeting = "ยินดีต้อนรับ", name = "สมชาย"))  // ระบุชื่อ parameter สลับลำดับได้
    printReceipt("ลาเต้", 65)
}

// ---------- แบบฝึก ----------

/**
 * แบบฝึก 3.1: คืนราคาหลังหักส่วนลด [discountPercent] เปอร์เซ็นต์
 * แก้บรรทัดประกาศ function ให้ discountPercent มีค่าเริ่มต้นเป็น 0 ด้วย
 * เช่น priceAfterDiscount(100, 20) ได้ 80 และ priceAfterDiscount(100) ได้ 100
 */
fun priceAfterDiscount(price: Int, discountPercent: Int = 0): Int {
    return price - (price * discountPercent / 100)
}
