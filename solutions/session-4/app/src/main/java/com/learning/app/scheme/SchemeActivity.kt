package com.learning.app.scheme

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.learning.core.router.CoffeeRouter
import org.koin.android.ext.android.inject

/**
 * ประตูหน้าของแอปสำหรับลิงก์จากข้างนอก ไม่มีหน้าตาของตัวเอง:
 * อ่านลิงก์ → ตัดสินใจว่าจะไปหน้าไหน → ส่งต่อ → ปิดตัวเอง
 *
 * coffeelearning://open.app/detail?name=Mocha  → หน้ารายละเอียดของ Mocha
 * coffeelearning://open.app                    → หน้าร้านกาแฟ
 */
class SchemeActivity : AppCompatActivity() {

    private val coffeeRouter: CoffeeRouter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handlerDeepLink()
    }

    private fun handlerDeepLink() {
        val data: Uri? = intent?.data
        data ?: return finish()

        val coffeeName = data.getQueryParameter(QUERY_NAME)
        if (data.path == PATH_DETAIL && !coffeeName.isNullOrBlank()) {
            coffeeRouter.onCoffeeDetail(this, coffeeName)
        } else {
            coffeeRouter.onCoffeeMenu(this)
        }
        finish()
    }

    companion object {
        const val PATH_DETAIL = "/detail"
        const val QUERY_NAME = "name"
    }
}
