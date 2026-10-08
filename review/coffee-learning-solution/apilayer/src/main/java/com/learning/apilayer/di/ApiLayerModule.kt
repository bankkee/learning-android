package com.learning.apilayer.di

import com.learning.apilayer.repository.coffee.GetCoffeeMenuApi
import com.learning.apilayer.repository.coffee.GetCoffeeMenuRepository
import com.learning.apilayer.repository.coffee.GetRecommendedCoffeeApi
import com.learning.apilayer.repository.coffee.GetRecommendedCoffeeRepository
import com.learning.apilayer.repository.dessert.GetRecommendedDessertApi
import com.learning.apilayer.repository.dessert.GetRecommendedDessertRepository
import com.learning.apilayer.usecase.coffee.GetCoffeeMenuUseCase
import com.learning.apilayer.usecase.coffee.GetRecommendedCoffeeUseCase
import com.learning.apilayer.usecase.dessert.GetRecommendedDessertUseCase
import org.koin.dsl.module
import retrofit2.Retrofit

val apilayerModule = module {

    // Coffee : เมนูแนะนำ
    factory { get<Retrofit>().create(GetRecommendedCoffeeApi::class.java) }
    factory { GetRecommendedCoffeeRepository(get()) }
    single { GetRecommendedCoffeeUseCase(get()) } // ผู้เรียนเขียน: Session 3 · ภารกิจ 1

    // Coffee : เมนูทั้งหมด
    factory { get<Retrofit>().create(GetCoffeeMenuApi::class.java) }
    factory { GetCoffeeMenuRepository(get()) }
    single { GetCoffeeMenuUseCase(get()) } // ผู้เรียนเขียน: Session 5 · ภารกิจ 1

    // >>> ผู้เรียนเขียน: Session 6 · ภารกิจ 3
    // Dessert : ขนมแนะนำ
    factory { get<Retrofit>().create(GetRecommendedDessertApi::class.java) }
    factory { GetRecommendedDessertRepository(get()) }
    single { GetRecommendedDessertUseCase(get()) }
    // <<< จบส่วนที่ผู้เรียนเขียน
}
