package com.learning.playground.lesson02

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.learning.playground.databinding.ActivityMenuListBinding

// บทที่ 02 — หน้ารายการเมนู (ขั้นที่ 7) เขียนไว้แล้ว ไม่ต้องแก้
// การเรียงของแถวกำหนดใน activity_menu_list.xml ด้วย app:layoutManager ไม่ได้กำหนดที่นี่

class MenuListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvMenu.adapter = MenuAdapter(sampleMenu)
    }
}
