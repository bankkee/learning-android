package com.learning.playground.lesson02

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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

        // แบบฝึก 9.2: เมื่อ switchDelivery เปลี่ยน ให้แสดง tilAddress เมื่อเปิด และซ่อนเมื่อปิด

        // แบบฝึก 9.3: เมื่อกด btnSubmit ให้แสดงผลของ orderSummary(...) ใน tvSummary
    }

    // ขนาดแก้วที่เลือกอยู่ใน rgSize ใช้ในแบบฝึก 9.3
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
