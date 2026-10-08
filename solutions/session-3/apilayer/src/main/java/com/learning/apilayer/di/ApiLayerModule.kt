package com.learning.apilayer.di

import com.learning.apilayer.repository.coffee.GetCoffeeMenuApi
import com.learning.apilayer.repository.coffee.GetCoffeeMenuRepository
import com.learning.apilayer.repository.coffee.GetRecommendedCoffeeApi
import com.learning.apilayer.repository.coffee.GetRecommendedCoffeeRepository
import com.learning.apilayer.usecase.coffee.GetRecommendedCoffeeUseCase
import org.koin.dsl.module
import retrofit2.Retrofit

val apilayerModule = module {

    // Coffee : เมนูแนะนำ
    factory { get<Retrofit>().create(GetRecommendedCoffeeApi::class.java) }
    factory { GetRecommendedCoffeeRepository(get()) }
    single { GetRecommendedCoffeeUseCase(get()) }

    // Coffee : เมนูทั้งหมด
    factory { get<Retrofit>().create(GetCoffeeMenuApi::class.java) }
    factory { GetCoffeeMenuRepository(get()) }
    // TODO(Session 5 · ภารกิจ 1): ลงทะเบียน GetCoffeeMenuUseCase

    // TODO(Session 6 · ภารกิจ 3): ขนมแนะนำ — ลงทะเบียน Api, Repository, UseCase ครบทั้งสามบรรทัด
}
