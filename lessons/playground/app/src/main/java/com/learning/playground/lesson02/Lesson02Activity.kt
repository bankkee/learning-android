package com.learning.playground.lesson02

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.learning.playground.R
import com.learning.playground.databinding.ActivityLesson02Binding

// บทที่ 02 — สารบัญของบท เขียนไว้แล้ว ไม่ต้องแก้
// แต่ละปุ่มเปิดไฟล์ layout ของขั้นนั้นเต็มจอ

class Lesson02Activity : AppCompatActivity() {

    private lateinit var binding: ActivityLesson02Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLesson02Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStep1.setOnClickListener { openLayout(R.layout.lesson02_step1_card) }
        binding.btnStep2Example.setOnClickListener { openLayout(R.layout.lesson02_step2_weight_example) }
        binding.btnStep2.setOnClickListener { openLayout(R.layout.lesson02_step2_row_linear) }
        binding.btnStep3.setOnClickListener { openLayout(R.layout.lesson02_step3_detail) }
        binding.btnStep4.setOnClickListener { openLayout(R.layout.lesson02_step4_row_constraint) }
        binding.btnStep5.setOnClickListener { openLayout(R.layout.lesson02_step5_panel) }
        binding.btnStep6.setOnClickListener {
            startActivity(Intent(this, OrderFormActivity::class.java))
        }
        binding.btnStep7.setOnClickListener {
            startActivity(Intent(this, MenuListActivity::class.java))
        }
        binding.btnStep8.setOnClickListener { openLayout(R.layout.lesson02_step8_reuse) }
    }

    private fun openLayout(layoutRes: Int) {
        startActivity(LayoutDemoActivity.newInstance(this, layoutRes))
    }
}
