package lesson00

// ขั้นที่ 6 — object และ companion object

// object: มีตัวเดียวทั้งโปรแกรม ไม่ต้องสร้าง เรียกผ่านชื่อได้เลย
object ShopConfig {
    const val SHOP_NAME = "ร้านกาแฟ"
    const val MAX_CUPS_PER_ORDER = 10

    /** แบบฝึก 6.1: คืนข้อความ "ร้านกาแฟ เปิดแล้ว" โดยใช้ SHOP_NAME */
    fun openingText(): String {
        return "$SHOP_NAME เปิดแล้ว"
    }
}

/** จดหมายจำลอง หน้าตาเหมือน Intent ของ Android: บอกปลายทาง และแนบข้อมูลไปได้ */
class FakeIntent(val target: String) {
    private val extras = mutableMapOf<String, String>()

    fun putExtra(key: String, value: String) {
        extras[key] = value
    }

    fun getStringExtra(key: String): String? = extras[key]
}

class MenuScreen {

    // companion object: ของที่เป็นของ class ไม่ใช่ของ object ตัวใดตัวหนึ่ง
    // เรียกผ่านชื่อ class ได้เลย เช่น MenuScreen.newInstance()
    companion object {
        fun newInstance(): FakeIntent = FakeIntent("MenuScreen")
    }
}

class DetailScreen {

    companion object {
        private const val EXTRA_COFFEE_NAME = "EXTRA_COFFEE_NAME"

        /** แบบฝึก 6.2: สร้าง FakeIntent ที่ target เป็น "DetailScreen" แล้วใส่ [coffeeName] ไว้ด้วย key EXTRA_COFFEE_NAME */
        fun newInstance(coffeeName: String): FakeIntent {
            return FakeIntent("DetailScreen").apply {
                putExtra(EXTRA_COFFEE_NAME, coffeeName)
            }
        }

        /** แบบฝึก 6.3: อ่านชื่อกาแฟออกจาก [intent] ถ้าไม่มีให้คืนข้อความว่าง "" */
        fun readCoffeeName(intent: FakeIntent): String {
            return intent.getStringExtra(EXTRA_COFFEE_NAME) ?: ""
        }
    }
}

fun main() {
    println(ShopConfig.SHOP_NAME)
    println(ShopConfig.MAX_CUPS_PER_ORDER)

    val intent = MenuScreen.newInstance()
    println("จะเปิดหน้า ${intent.target}")
}
