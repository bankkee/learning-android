package com.learning.playground.lesson01

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.learning.playground.BuildConfig
import com.learning.playground.R
import com.learning.playground.databinding.ActivityCoffeeShopBinding

// บทที่ 01 — หน้าจอเดียวของบทนี้
// ยังไม่ต้องเข้าใจทุกบรรทัด Activity กับ onCreate อยู่ในบทที่ 03 ส่วน binding อยู่ในบทที่ 02

class CoffeeShopActivity : AppCompatActivity() {

    // lateinit: ยังไม่มีค่าตอนสร้าง จะกำหนดใน onCreate
    private lateinit var binding: ActivityCoffeeShopBinding

    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoffeeShopBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "onCreate: เปิดหน้าร้าน")

        binding.tvBranch.text = BuildConfig.BRANCH_NAME
        binding.tvVersion.text = getString(R.string.shop_version, BuildConfig.VERSION_NAME)
        showOrder()

        binding.btnOrder.setOnClickListener {
            count++
            Log.d(TAG, "กดสั่งกาแฟ count=$count")
            showOrder()
        }

        binding.btnPromotion.setOnClickListener {
            Log.d(TAG, "กดดูโปรโมชัน")
            binding.tvPromotion.text = loadPromotion()
        }
    }

    private fun showOrder() {
        // แบบฝึก 4.2: ย้ายข้อความบรรทัดถัดไปไปไว้ใน strings.xml แล้วใช้ getString แบบบรรทัด tvTotal
        binding.tvOrderCount.text = "สั่งไปแล้ว $count แก้ว"
        binding.tvTotal.text = getString(R.string.shop_total_price, totalPrice(count))
    }

    companion object {
        private const val TAG = "CoffeeShop"
    }
}
