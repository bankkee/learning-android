package com.learning.playground.lesson02

// บทที่ 02 — ข้อมูลของรายการเมนู (ขั้นที่ 7) เขียนไว้แล้ว ไม่ต้องแก้

data class CoffeeItem(val name: String, val price: Int)

val sampleMenu = listOf(
    CoffeeItem("เอสเปรสโซ", 50),
    CoffeeItem("อเมริกาโน", 55),
    CoffeeItem("ลาเต้", 65),
    CoffeeItem("คาปูชิโน", 65),
    CoffeeItem("มอคค่า", 70),
    CoffeeItem("คาราเมลมัคคิอาโต", 75),
    CoffeeItem("แฟลตไวท์", 70),
    CoffeeItem("โคลด์บรูว์", 80),
    CoffeeItem("มัทฉะลาเต้", 75),
    CoffeeItem("โกโก้", 60)
)
