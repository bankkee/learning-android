package com.learning.core.router

import android.content.Context

interface CoffeeRouter {
    fun onCoffeeMenu(context: Context)
    fun onCoffeeDetail(context: Context, coffeeName: String)
}
