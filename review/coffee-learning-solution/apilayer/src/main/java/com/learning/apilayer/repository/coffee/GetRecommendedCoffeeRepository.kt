package com.learning.apilayer.repository.coffee

import com.learning.networks.model.BaseRequestHelper.getBaseData
import com.learning.networks.model.Failure
import com.learning.networks.model.FormData
import com.learning.networks.model.FormHead
import com.learning.networks.model.Result
import com.learning.networks.repository.NetworkDataSource

class GetRecommendedCoffeeRepository(
    private val getRecommendedCoffeeApi: GetRecommendedCoffeeApi
) : NetworkDataSource() {
    fun getRecommendedCoffee(request: FormData): Result<Failure, GetRecommendedCoffeeResponse> {
        return requestFormData(
            getRecommendedCoffeeApi.getRecommendedCoffee(
                getBaseData(
                    request,
                    FormHead(GetRecommendedCoffeeApi.FORM_HEAD_REQ)
                )
            )
        )
    }
}
