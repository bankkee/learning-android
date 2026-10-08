package com.learning.app.di

import com.learning.app.BuildConfig
import com.learning.app.public_impl.FeatureFlagImpl
import com.learning.app.public_impl.MockApiInterceptor
import com.learning.coffee.route.CoffeeRoute
import com.learning.core.featureflag.FeatureFlag
import com.learning.core.router.CoffeeRouter
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {

    // Router: ผูก interface ใน core เข้ากับตัวจริงใน feature module
    factory<CoffeeRouter> { CoffeeRoute() }

    single<FeatureFlag> { FeatureFlagImpl() }

    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .apply {
                if (BuildConfig.USE_MOCK_API) {
                    addInterceptor(MockApiInterceptor(androidContext()))
                }
            }
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
