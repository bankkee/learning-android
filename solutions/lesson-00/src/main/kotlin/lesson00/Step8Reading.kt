package lesson00

// ขั้นที่ 8 — ไวยากรณ์ที่ต้องอ่านออก: let, apply, by lazy และ generic

class Order {
    var menu: String = ""
    var cups: Int = 0
    var note: String = ""
}

// generic: T คือ "ชนิดอะไรก็ได้ ที่คนใช้เป็นคนกำหนด"
class Box<T>(val value: T)

class Shop {
    // by lazy: ยังไม่สร้างจนกว่าจะถูกใช้ครั้งแรก แล้วเก็บไว้ใช้ต่อ
    val menus: List<String> by lazy {
        println("(กำลังเตรียมเมนู... เกิดครั้งเดียว)")
        listOf("ลาเต้", "มอคค่า")
    }
}

fun main() {
    // let: "ถ้าไม่ใช่ null ให้ทำสิ่งนี้กับมัน" ใช้คู่กับ ?.
    val note: String? = "หวานน้อย"
    note?.let { println("หมายเหตุ: $it") }

    val empty: String? = null
    empty?.let { println("บรรทัดนี้จะไม่ถูกพิมพ์") }

    // apply: สร้างของแล้วตั้งค่าต่อทันที ในปีกกา this คือของชิ้นนั้น
    val order = Order().apply {
        menu = "ลาเต้"
        cups = 2
    }
    println("${order.menu} ${order.cups} แก้ว")

    // by lazy
    val shop = Shop()
    println("สร้างร้านแล้ว")
    println(shop.menus)
    println(shop.menus)

    // generic
    val boxOfText: Box<String> = Box("ลาเต้")
    val boxOfNumber: Box<Int> = Box(65)
    println("${boxOfText.value} ${boxOfNumber.value}")
}

// ---------- แบบฝึก ----------

/** แบบฝึก 8.1: ถ้า [text] มีค่า ให้คืนตัวพิมพ์ใหญ่ทั้งหมดต่อท้ายด้วย "!" เช่น "latte" -> "LATTE!" ถ้าเป็น null ให้คืน "" */
fun shout(text: String?): String {
    return text?.let { it.uppercase() + "!" } ?: ""
}

/** แบบฝึก 8.2: สร้าง Order ที่ตั้งค่า menu และ cups ตามที่รับมา โดยใช้ apply */
fun buildOrder(menu: String, cups: Int): Order {
    return Order().apply {
        this.menu = menu
        this.cups = cups
    }
}

/** แบบฝึก 8.3: คืนค่าใน [box] ถ้า box เป็น null ให้คืน [default] */
fun <T> unwrapOr(box: Box<T>?, default: T): T {
    return box?.value ?: default
}
