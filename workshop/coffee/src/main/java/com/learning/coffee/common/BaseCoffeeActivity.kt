package com.learning.coffee.common

import androidx.viewbinding.ViewBinding
import com.learning.coffee.di.CoffeeModule
import com.learning.core.base.NewBaseActivity

abstract class BaseCoffeeActivity<VBinding : ViewBinding> : NewBaseActivity<VBinding>() {

    override fun getDi() = CoffeeModule
}
