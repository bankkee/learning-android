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
        // ผู้เรียนเขียน: Session 2 · ภารกิจ 3 (ตอนนั้นยังไม่มี parameter)
        // แล้วเพิ่ม get() ทีละตัวใน Session 3 · ภารกิจ 3, Session 5 · ภารกิจ 2, Session 6 · ภารกิจ 3
        viewModel { CoffeeMenuViewModel(get(), get(), get()) }
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
