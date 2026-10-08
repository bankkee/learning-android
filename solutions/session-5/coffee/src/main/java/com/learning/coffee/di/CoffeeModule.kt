package com.learning.coffee.di

import com.learning.apilayer.di.apilayerModule
import com.learning.coffee.ui.menu.CoffeeMenuViewModel
import com.learning.core.di.ModuleInject
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module

object CoffeeModule : ModuleInject {
    private val coffeeModule = module {
        viewModel { CoffeeMenuViewModel(get(), get()) }
    }

    private val loadModule by lazy {
        loadKoinModules(
            listOf(coffeeModule, apilayerModule)
        )
    }

    private val unloadModule by lazy {
        unloadKoinModules(
            listOf(apilayerModule)
        )
    }

    override fun dropFeature(): Unit? = unloadModule

    override fun injectFeature() {
        loadModule
    }
}
