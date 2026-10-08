package com.learning.apilayer.usecase.dessert

import com.learning.apilayer.repository.dessert.GetRecommendedDessertRepository
import com.learning.apilayer.repository.dessert.GetRecommendedDessertRequest
import com.learning.apilayer.repository.dessert.GetRecommendedDessertResponse
import com.learning.networks.model.Failure
import com.learning.networks.model.Result
import com.learning.networks.usecase.UseCase

class GetRecommendedDessertUseCase(private val repository: GetRecommendedDessertRepository) :
    UseCase<GetRecommendedDessertResponse, GetRecommendedDessertRequest>() {
    override suspend fun run(
        params: GetRecommendedDessertRequest,
        useCache: Boolean
    ): Result<Failure, GetRecommendedDessertResponse?> {
        return repository.getRecommendedDessert(params)
    }
}
