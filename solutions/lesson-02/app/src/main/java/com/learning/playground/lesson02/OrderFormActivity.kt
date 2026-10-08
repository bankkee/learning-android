package com.learning.playground.lesson02

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.learning.playground.R
import com.learning.playground.databinding.ActivityOrderFormBinding

// บทที่ 02 — ฟอร์มสั่งกาแฟ (ขั้นที่ 6 แก้ layout, ขั้นที่ 9 แก้ไฟล์นี้)

class OrderFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ตัวอย่าง: รับรู้เมื่อ checkbox เปลี่ยน
        binding.cbExtraShot.setOnCheckedChangeListener { _, isChecked ->
            Log.d(TAG, "เพิ่มช็อต = $isChecked")
        }

        binding.switchDelivery.setOnCheckedChangeListener { _, isChecked ->
            binding.tilAddress.isVisible = isChecked
        }

        binding.btnSubmit.setOnClickListener {
            binding.tvSummary.text = orderSummary(
                name = binding.etName.text.toString(),
                size = selectedSize(),
                extraShot = binding.cbExtraShot.isChecked,
                cups = binding.stepperCups.quantity
            )
        }
    }

    // ขนาดแก้วที่เลือกอยู่ใน rgSize
    private fun selectedSize(): String {
        return when (binding.rgSize.checkedRadioButtonId) {
            R.id.rbSizeS -> "S"
            R.id.rbSizeL -> "L"
            else -> "M"
        }
    }

    companion object {
        private const val TAG = "OrderForm"
    }
}
