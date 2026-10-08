package com.learning.coffee.route

import android.content.Context
import com.learning.coffee.ui.detail.CoffeeDetailActivity
import com.learning.coffee.ui.menu.CoffeeMenuActivity
import com.learning.core.router.CoffeeRouter

class CoffeeRoute : CoffeeRouter {
    override fun onCoffeeMenu(context: Context) {
        context.startActivity(CoffeeMenuActivity.newInstance(context))
    }

    // >>> ผู้เรียนเขียน: Session 4 · ภารกิจ 2
    override fun onCoffeeDetail(context: Context, coffeeName: String) {
        context.startActivity(CoffeeDetailActivity.newInstance(context, coffeeName))
    }
    // <<< จบส่วนที่ผู้เรียนเขียน
}
