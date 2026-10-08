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
        binding.btnBestSeller.singleClick {
            coffeeRouter.onCoffeeDetail(this, BEST_SELLER)
        }
    }

    companion object {
        const val BEST_SELLER = "ลาเต้"
    }
}
