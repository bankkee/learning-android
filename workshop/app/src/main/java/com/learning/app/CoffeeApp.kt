package com.learning.app

import android.app.Application
import com.learning.app.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CoffeeApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CoffeeApp)
            modules(appModule)
        }
    }
}
