package lesson00

// ขั้นที่ 1 — ตัวแปร ชนิดข้อมูล และข้อความ
// กดลูกศรสีเขียวหน้า fun main เพื่อรันตัวอย่าง

fun main() {
    val shopName = "ร้านกาแฟ"   // val: กำหนดค่าได้ครั้งเดียว
    var cups = 1                // var: เปลี่ยนค่าทีหลังได้
    cups = cups + 1

    val price: Int = 65         // เขียนชนิดกำกับเองก็ได้ ปกติ Kotlin เดาให้

    // ใส่ค่าลงในข้อความด้วย $ชื่อ หรือ ${นิพจน์}
    println("$shopName ขายไปแล้ว $cups แก้ว รวม ${cups * price} บาท")

    // ลองเอา comment บรรทัดล่างออก แล้วดูว่า IDE ขึ้นแดงว่าอะไร
    // shopName = "ร้านชา"
}

// ---------- แบบฝึก ----------

/** แบบฝึก 1.1: คืนข้อความรูปแบบ "ลาเต้ · 65 บาท" */
fun menuLabel(name: String, price: Int): String {
    return "$name · $price บาท"
}

/** แบบฝึก 1.2: คืนราคารวมของกาแฟ [cups] แก้ว แก้วละ [price] บาท */
fun totalPrice(price: Int, cups: Int): Int {
    return price * cups
}
