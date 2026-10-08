package lesson00

// ขั้นที่ 4 — lambda: ก้อนโค้ดที่ส่งต่อให้คนอื่นเรียกทีหลัง

/** ปุ่มจำลอง หน้าตาเหมือนปุ่มของ Android: เก็บโค้ดไว้ก่อน แล้วเรียกตอนถูกกด */
class FakeButton {
    private var listener: (() -> Unit)? = null

    fun setOnClickListener(listener: () -> Unit) {
        this.listener = listener
    }

    fun click() {
        listener?.invoke()
    }
}

// function ที่รับ lambda: (Int) -> Unit อ่านว่า "โค้ดที่รับ Int หนึ่งตัวและไม่คืนค่า"
fun repeatOrder(times: Int, action: (Int) -> Unit) {
    for (round in 1..times) {
        action(round)
    }
}

fun main() {
    // lambda เก็บใส่ตัวแปรได้
    val double = { x: Int -> x * 2 }
    println(double(4))

    // ส่ง lambda ให้ function: ถ้าเป็น parameter ตัวสุดท้าย เขียนไว้นอกวงเล็บได้
    repeatOrder(3) { round -> println("สั่งรอบที่ $round") }

    // ถ้า lambda รับค่าตัวเดียว ไม่ต้องตั้งชื่อก็ได้ ใช้ it แทน
    repeatOrder(2) { println("รอบ $it") }

    // ใช้กับ list
    val prices = listOf(55, 60, 65, 75, 80)
    println(prices.filter { it < 70 })   // เลือกเฉพาะที่ตรงเงื่อนไข
    println(prices.map { it + 5 })       // แปลงทุกตัว

    // รูปแบบที่จะเจอทุกหน้าจอใน Android
    val button = FakeButton()
    button.setOnClickListener { println("ปุ่มถูกกด") }
    println("ยังไม่มีอะไรเกิดขึ้น จนกว่าจะมีคนกด")
    button.click()
}

// ---------- แบบฝึก ----------

/** แบบฝึก 4.1: คืนเฉพาะราคาที่ไม่เกิน [max] */
fun affordablePrices(prices: List<Int>, max: Int): List<Int> {
    TODO("แบบฝึก 4.1")
}

/** แบบฝึก 4.2: ถ้า [success] เป็น true ให้เรียก [onSuccess] ไม่อย่างนั้นให้เรียก [onError] */
fun handleResult(success: Boolean, onSuccess: () -> Unit, onError: () -> Unit) {
    TODO("แบบฝึก 4.2")
}

/**
 * แบบฝึก 4.3: ตั้ง listener ให้ [button] ซึ่งเพิ่มตัวนับทีละ 1 ทุกครั้งที่ถูกกด
 * แล้วกดปุ่ม [times] ครั้งด้วย button.click() และคืนค่าตัวนับ
 */
fun countClicks(button: FakeButton, times: Int): Int {
    TODO("แบบฝึก 4.3")
}
