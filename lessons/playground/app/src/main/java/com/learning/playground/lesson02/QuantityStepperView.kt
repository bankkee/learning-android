package com.learning.playground.lesson02

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.learning.playground.R
import com.learning.playground.databinding.ViewQuantityStepperBinding

// บทที่ 02 — custom view สำหรับเลือกจำนวน (ใช้ในขั้นที่ 9) เขียนไว้แล้ว ไม่ต้องแก้
// ผู้ใช้ View นี้ต้องรู้แค่สามอย่าง:
//   app:minQuantity, app:maxQuantity   ตั้งค่าใน XML
//   quantity                           อ่านจำนวนปัจจุบันจากโค้ด
//   onQuantityChanged                  รับรู้เมื่อจำนวนเปลี่ยน

class QuantityStepperView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val binding = ViewQuantityStepperBinding.inflate(LayoutInflater.from(context), this)

    private var minQuantity = 1
    private var maxQuantity = 10

    var quantity = 1
        private set

    var onQuantityChanged: (Int) -> Unit = {}

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL

        val values = context.obtainStyledAttributes(attrs, R.styleable.QuantityStepperView)
        minQuantity = values.getInt(R.styleable.QuantityStepperView_minQuantity, minQuantity)
        maxQuantity = values.getInt(R.styleable.QuantityStepperView_maxQuantity, maxQuantity)
        values.recycle()

        quantity = minQuantity
        binding.btnMinus.setOnClickListener { changeBy(-1) }
        binding.btnPlus.setOnClickListener { changeBy(1) }
        render()
    }

    private fun changeBy(amount: Int) {
        val newQuantity = (quantity + amount).coerceIn(minQuantity, maxQuantity)
        if (newQuantity == quantity) return
        quantity = newQuantity
        render()
        onQuantityChanged(quantity)
    }

    private fun render() {
        binding.tvQuantity.text = quantity.toString()
        binding.btnMinus.isEnabled = quantity > minQuantity
        binding.btnPlus.isEnabled = quantity < maxQuantity
    }
}
