package com.learning.coffee.ui.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.learning.apilayer.repository.coffee.GetRecommendedCoffeeRequest
import com.learning.apilayer.repository.coffee.GetRecommendedCoffeeResponse
import com.learning.apilayer.usecase.coffee.GetRecommendedCoffeeUseCase
import com.learning.networks.model.Response

class CoffeeMenuViewModel(
    private val getRecommendedCoffeeUseCase: GetRecommendedCoffeeUseCase
) : ViewModel() {

    private val _recommendedCoffeeResult = MutableLiveData<Response<GetRecommendedCoffeeResponse>>()
    val recommendedCoffeeResult: LiveData<Response<GetRecommendedCoffeeResponse>>
        get() = _recommendedCoffeeResult

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
}
