package com.learning.playground.lesson02

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

// บทที่ 02 — แสดงไฟล์ layout หนึ่งไฟล์เต็มจอ เขียนไว้แล้ว ไม่ต้องแก้

class LayoutDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(intent.getIntExtra(EXTRA_LAYOUT_RES, 0))
    }

    companion object {
        private const val EXTRA_LAYOUT_RES = "EXTRA_LAYOUT_RES"

        fun newInstance(context: Context, layoutRes: Int): Intent {
            return Intent(context, LayoutDemoActivity::class.java).apply {
                putExtra(EXTRA_LAYOUT_RES, layoutRes)
            }
        }
    }
}
