package com.learning.coffee.ui.detail

import android.content.Context
import android.content.Intent
import com.learning.coffee.common.BaseCoffeeActivity
import com.learning.coffee.databinding.ActivityCoffeeDetailBinding

class CoffeeDetailActivity : BaseCoffeeActivity<ActivityCoffeeDetailBinding>() {

    override fun getViewBinding(): ActivityCoffeeDetailBinding = ActivityCoffeeDetailBinding.inflate(layoutInflater)

    override fun setUpViews() {
        binding.tvCoffeeName.text = intent.getStringExtra(EXTRA_COFFEE_NAME).orEmpty()
    }

    companion object {
        private const val EXTRA_COFFEE_NAME = "EXTRA_COFFEE_NAME"

        fun newInstance(context: Context, coffeeName: String): Intent {
            return Intent(context, CoffeeDetailActivity::class.java).apply {
                putExtra(EXTRA_COFFEE_NAME, coffeeName)
            }
        }
    }
}
