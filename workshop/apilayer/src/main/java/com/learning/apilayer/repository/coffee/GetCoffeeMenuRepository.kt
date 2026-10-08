package com.learning.apilayer.repository.coffee

import com.learning.networks.model.BaseRequestHelper.getBaseData
import com.learning.networks.model.Failure
import com.learning.networks.model.FormData
import com.learning.networks.model.ApiHeader
import com.learning.networks.model.Result
import com.learning.networks.repository.NetworkDataSource

class GetCoffeeMenuRepository(
    private val getCoffeeMenuApi: GetCoffeeMenuApi
) : NetworkDataSource() {
    fun getCoffeeMenu(request: FormData): Result<Failure, GetCoffeeMenuResponse> {
        return requestData(
            getCoffeeMenuApi.getCoffeeMenu(
                getBaseData(
                    request,
                    ApiHeader(GetCoffeeMenuApi.API_CODE)
                )
            )
        )
    }
}
