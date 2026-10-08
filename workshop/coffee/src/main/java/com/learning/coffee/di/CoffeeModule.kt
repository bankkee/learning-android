package com.learning.coffee.di

import com.learning.apilayer.di.apilayerModule
import com.learning.core.di.ModuleInject
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module

object CoffeeModule : ModuleInject {
    private val coffeeModule = module {
        // TODO(Session 2 · ภารกิจ 3): ลงทะเบียน CoffeeMenuViewModel ด้วย viewModel { }
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
