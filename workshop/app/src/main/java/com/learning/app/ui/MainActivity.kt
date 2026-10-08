package com.learning.app.ui

import com.learning.app.databinding.ActivityMainBinding
import com.learning.core.base.NewBaseActivity
import com.learning.core.extension.singleClick
import com.learning.core.router.CoffeeRouter
import org.koin.android.ext.android.inject

class MainActivity : NewBaseActivity<ActivityMainBinding>() {

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    private val coffeeRouter: CoffeeRouter by inject()

    override fun setUpViews() {
        binding.btnEnterCoffee.singleClick {
            coffeeRouter.onCoffeeMenu(this)
        }
        // TODO(Session 4 · ภารกิจ 2): กด btnBestSeller แล้วเปิดหน้ารายละเอียดของ BEST_SELLER ผ่าน coffeeRouter
    }

    companion object {
        const val BEST_SELLER = "ลาเต้"
    }
}
