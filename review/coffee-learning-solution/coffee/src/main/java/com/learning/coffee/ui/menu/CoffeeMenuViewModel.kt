package com.learning.coffee.ui.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.learning.apilayer.repository.coffee.GetCoffeeMenuRequest
import com.learning.apilayer.repository.coffee.GetCoffeeMenuResponse
import com.learning.apilayer.repository.coffee.GetRecommendedCoffeeRequest
import com.learning.apilayer.repository.coffee.GetRecommendedCoffeeResponse
import com.learning.apilayer.repository.dessert.GetRecommendedDessertRequest
import com.learning.apilayer.repository.dessert.GetRecommendedDessertResponse
import com.learning.apilayer.usecase.coffee.GetCoffeeMenuUseCase
import com.learning.apilayer.usecase.coffee.GetRecommendedCoffeeUseCase
import com.learning.apilayer.usecase.dessert.GetRecommendedDessertUseCase
import com.learning.networks.model.Response

// ทั้ง class นี้ผู้เรียนเป็นคนเขียน starter ให้มาแค่ class เปล่ากับ list ของชื่อเมนู
// Session 2 เขียนเวอร์ชันที่สุ่มจาก list ในเครื่อง (LiveData<String> + recommend()) แล้วถูกแทนที่ใน Session 3
// โค้ดของเวอร์ชัน Session 2 อยู่ใน learning.md หัวข้อ Session 2 · ภารกิจ 1
class CoffeeMenuViewModel(
    private val getRecommendedCoffeeUseCase: GetRecommendedCoffeeUseCase, // Session 3 · ภารกิจ 2
    private val getCoffeeMenuUseCase: GetCoffeeMenuUseCase, // Session 5 · ภารกิจ 2
    private val getRecommendedDessertUseCase: GetRecommendedDessertUseCase // Session 6 · ภารกิจ 3
) : ViewModel() {

    // ผู้เรียนเขียน: Session 3 · ภารกิจ 2
    private val _recommendedCoffeeResult = MutableLiveData<Response<GetRecommendedCoffeeResponse>>()
    val recommendedCoffeeResult: LiveData<Response<GetRecommendedCoffeeResponse>>
        get() = _recommendedCoffeeResult

    // ผู้เรียนเขียน: Session 5 · ภารกิจ 2
    private val _coffeeMenuResult = MutableLiveData<Response<GetCoffeeMenuResponse>>()
    val coffeeMenuResult: LiveData<Response<GetCoffeeMenuResponse>>
        get() = _coffeeMenuResult

    // ผู้เรียนเขียน: Session 6 · ภารกิจ 3
    private val _recommendedDessertResult = MutableLiveData<Response<GetRecommendedDessertResponse>>()
    val recommendedDessertResult: LiveData<Response<GetRecommendedDessertResponse>>
        get() = _recommendedDessertResult

    // ผู้เรียนเขียน: Session 3 · ภารกิจ 2
    fun getRecommendedCoffee(params: GetRecommendedCoffeeRequest) {
        _recommendedCoffeeResult.value = Response.Loading
        getRecommendedCoffeeUseCase(params) { res ->
            res.result({
                _recommendedCoffeeResult.value = Response.Error(it)
            }, {
                it?.let {
                    _recommendedCoffeeResult.value = Response.Success(it)
                }
            })
        }
    }

    // ผู้เรียนเขียน: Session 5 · ภารกิจ 2
    fun getCoffeeMenu(params: GetCoffeeMenuRequest) {
        _coffeeMenuResult.value = Response.Loading
        getCoffeeMenuUseCase(params) { res ->
            res.result({
                _coffeeMenuResult.value = Response.Error(it)
            }, {
                it?.let {
                    _coffeeMenuResult.value = Response.Success(it)
                }
            })
        }
    }

    // ผู้เรียนเขียน: Session 6 · ภารกิจ 3
    fun getRecommendedDessert(params: GetRecommendedDessertRequest) {
        _recommendedDessertResult.value = Response.Loading
        getRecommendedDessertUseCase(params) { res ->
            res.result({
                _recommendedDessertResult.value = Response.Error(it)
            }, {
                it?.let {
                    _recommendedDessertResult.value = Response.Success(it)
                }
            })
        }
    }
}
