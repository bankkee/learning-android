package com.learning.apilayer.repository.dessert

import com.learning.networks.model.BaseRequestHelper.getBaseData
import com.learning.networks.model.Failure
import com.learning.networks.model.FormData
import com.learning.networks.model.ApiHeader
import com.learning.networks.model.Result
import com.learning.networks.repository.NetworkDataSource

class GetRecommendedDessertRepository(
    private val getRecommendedDessertApi: GetRecommendedDessertApi
) : NetworkDataSource() {
    fun getRecommendedDessert(request: FormData): Result<Failure, GetRecommendedDessertResponse> {
        return requestData(
            getRecommendedDessertApi.getRecommendedDessert(
                getBaseData(
                    request,
                    ApiHeader(GetRecommendedDessertApi.API_CODE)
                )
            )
        )
    }
}
