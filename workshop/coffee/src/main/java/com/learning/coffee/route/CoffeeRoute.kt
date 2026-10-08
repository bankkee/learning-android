package com.learning.coffee.route

import android.content.Context
import com.learning.coffee.ui.menu.CoffeeMenuActivity
import com.learning.core.router.CoffeeRouter

class CoffeeRoute : CoffeeRouter {
    override fun onCoffeeMenu(context: Context) {
        context.startActivity(CoffeeMenuActivity.newInstance(context))
    }

    // TODO(Session 4 · ภารกิจ 2): override onCoffeeDetail ให้เปิด CoffeeDetailActivity
}
