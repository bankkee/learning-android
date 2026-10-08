package com.learning.playground

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.learning.playground.databinding.ActivityHomeBinding
import com.learning.playground.lesson01.CoffeeShopActivity
import com.learning.playground.lesson02.Lesson02Activity

// หน้าแรกของ playground: สารบัญของทุกบท เมื่อเพิ่มบทใหม่ให้เพิ่มปุ่มที่นี่
// การเปิดหน้าจอด้วย Intent อยู่ในบทที่ 08 ยังไม่ต้องเข้าใจ

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLesson01.setOnClickListener {
            startActivity(Intent(this, CoffeeShopActivity::class.java))
        }
        binding.btnLesson02.setOnClickListener {
            startActivity(Intent(this, Lesson02Activity::class.java))
        }
    }
}
