package com.learning.coffee.ui.detail

import com.learning.coffee.common.BaseCoffeeActivity
import com.learning.coffee.databinding.ActivityCoffeeDetailBinding

class CoffeeDetailActivity : BaseCoffeeActivity<ActivityCoffeeDetailBinding>() {

    override fun getViewBinding(): ActivityCoffeeDetailBinding = ActivityCoffeeDetailBinding.inflate(layoutInflater)

    override fun setUpViews() {
        // TODO(Session 4 · ภารกิจ 1): อ่านชื่อเมนูจาก Intent extra มาแสดงที่ tvCoffeeName
    }

    // TODO(Session 4 · ภารกิจ 1): เพิ่ม companion object ที่มี newInstance(context, coffeeName)
}
