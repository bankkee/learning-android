package lesson00

// ขั้นที่ 2 — ค่าที่อาจไม่มี (null)

fun main() {
    val name: String = "ลาเต้"        // String: มีค่าเสมอ
    val description: String? = null    // String?: อาจเป็น null

    println(name.length)               // เรียกได้ตรง ๆ

    // println(description.length)     // เอา comment ออกแล้วจะ compile ไม่ผ่าน

    println(description?.length)             // ?.  ถ้าเป็น null ให้ผลเป็น null ไม่พัง
    println(description ?: "ไม่มีคำอธิบาย")  // ?:  ถ้าเป็น null ให้ใช้ค่าทางขวาแทน
    println(description?.length ?: 0)        // ใช้คู่กัน: ความยาว หรือ 0 ถ้าไม่มี

    // !! แปลว่า "ฉันมั่นใจว่าไม่ใช่ null" ถ้าผิดแอปจะพังทันที ควรเลี่ยง
    // println(description!!.length)
}

// ---------- แบบฝึก ----------

/** แบบฝึก 2.1: คืน [name] ถ้ามีค่า ถ้าเป็น null ให้คืน "ไม่ระบุชื่อ" */
fun displayName(name: String?): String {
    return name ?: "ไม่ระบุชื่อ"
}

/** แบบฝึก 2.2: คืนความยาวของ [name] ถ้าเป็น null ให้คืน 0 */
fun nameLength(name: String?): Int {
    return name?.length ?: 0
}
