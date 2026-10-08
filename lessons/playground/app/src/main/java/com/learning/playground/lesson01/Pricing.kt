package com.learning.playground.lesson01

// บทที่ 01 — คำนวณราคา (ใช้ในขั้นที่ 7)

const val PRICE_PER_CUP = 60
const val DISCOUNT_MIN_CUPS = 3
const val DISCOUNT_PER_CUP = 10

fun totalPrice(cups: Int): Int {
    var pricePerCup = PRICE_PER_CUP
    if (cups > DISCOUNT_MIN_CUPS) {
        pricePerCup -= DISCOUNT_PER_CUP
    }
    return cups * pricePerCup
}
