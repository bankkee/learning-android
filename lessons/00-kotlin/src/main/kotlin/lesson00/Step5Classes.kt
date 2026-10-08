package lesson00

// ขั้นที่ 5 — class และ data class

// ของในวงเล็บหลังชื่อ class คือ constructor
// val/var ข้างหน้าทำให้มันเป็น property ของ object ไปด้วย
class Coffee(val name: String, var price: Int) {

    fun label(): String = "$name · $price บาท"
}

// data class: class ที่มีไว้เก็บข้อมูล ได้ toString, equals และ copy มาให้เอง
data class Customer(val name: String, val points: Int)

fun main() {
    val latte = Coffee("ลาเต้", 65)   // สร้าง object ไม่ต้องใช้คำว่า new
    latte.price = 70
    println(latte.label())
    println(latte)                     // class ธรรมดา: พิมพ์แล้วอ่านไม่ออก

    val somchai = Customer("สมชาย", 10)
    println(somchai)                                  // data class: พิมพ์แล้วอ่านออก
    println(somchai == Customer("สมชาย", 10))         // เทียบจากค่าข้างใน ได้ true
    val promoted = somchai.copy(points = 20)          // สำเนาที่เปลี่ยนบางค่า ตัวเดิมไม่เปลี่ยน
    println("$somchai -> $promoted")
}

// ---------- แบบฝึก ----------

class Counter {
    var count: Int = 0
        private set   // ข้างนอกอ่านได้ แต่แก้ค่าได้เฉพาะจากใน class นี้

    /** แบบฝึก 5.1: เพิ่ม count ขึ้น 1 */
    fun increment() {
        TODO("แบบฝึก 5.1")
    }
}

data class MenuItem(val name: String, val price: Int) {

    /** แบบฝึก 5.2: คืนข้อความรูปแบบ "ลาเต้ · 65 บาท" */
    fun label(): String {
        TODO("แบบฝึก 5.2")
    }
}

/** แบบฝึก 5.3: คืน MenuItem ตัวใหม่ที่ราคาเพิ่มขึ้น [amount] โดยใช้ copy */
fun raisePrice(item: MenuItem, amount: Int): MenuItem {
    TODO("แบบฝึก 5.3")
}
