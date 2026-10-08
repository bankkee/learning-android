package lesson00

// ขั้นที่ 7 — sealed class กับ when

// sealed class: ชนิดที่เป็นได้แค่ไม่กี่แบบตามที่ประกาศไว้ตรงนี้ และแต่ละแบบพกข้อมูลต่างกันได้
sealed class LoadState {
    object Loading : LoadState()
    data class Success(val menuName: String) : LoadState()
    data class Error(val message: String) : LoadState()
}

// when: เลือกทำตามกรณี กับ sealed class ต้องเขียนให้ครบทุกแบบ ไม่อย่างนั้น compile ไม่ผ่าน
fun icon(state: LoadState): String = when (state) {
    is LoadState.Loading -> "..."
    is LoadState.Success -> "OK"
    is LoadState.Error -> "!"
}

fun main() {
    val states = listOf(
        LoadState.Loading,
        LoadState.Success("ลาเต้"),
        LoadState.Error("เชื่อมต่อไม่ได้")
    )
    for (state in states) {
        println("${icon(state)}  $state")
    }

    // when ใช้กับค่าธรรมดาก็ได้
    val cups = 3
    val size = when {
        cups <= 1 -> "น้อย"
        cups <= 5 -> "กลาง"
        else -> "มาก"
    }
    println(size)
}

// ---------- แบบฝึก ----------

/**
 * แบบฝึก 7.1: คืนข้อความตามสถานะ
 * Loading -> "กำลังโหลด"
 * Success -> "ได้เมนู ลาเต้"        (ใช้ menuName)
 * Error   -> "ผิดพลาด: เชื่อมต่อไม่ได้" (ใช้ message)
 */
fun describe(state: LoadState): String {
    return when (state) {
        is LoadState.Loading -> "กำลังโหลด"
        is LoadState.Success -> "ได้เมนู ${state.menuName}"
        is LoadState.Error -> "ผิดพลาด: ${state.message}"
    }
}

/** แบบฝึก 7.2: คืน true ถ้าโหลดจบแล้ว (สำเร็จหรือผิดพลาดก็ได้) และ false ถ้ายังโหลดอยู่ */
fun isFinished(state: LoadState): Boolean {
    return state !is LoadState.Loading
}
