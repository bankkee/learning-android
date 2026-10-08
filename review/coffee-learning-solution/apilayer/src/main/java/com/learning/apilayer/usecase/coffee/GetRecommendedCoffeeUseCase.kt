// ผู้เรียนสร้างไฟล์นี้เองทั้งไฟล์: Session 3 · ภารกิจ 1
package com.learning.apilayer.usecase.coffee

import com.learning.apilayer.repository.coffee.GetRecommendedCoffeeRepository
import com.learning.apilayer.repository.coffee.GetRecommendedCoffeeRequest
import com.learning.apilayer.repository.coffee.GetRecommendedCoffeeResponse
import com.learning.networks.model.Failure
import com.learning.networks.model.Result
import com.learning.networks.usecase.UseCase

class GetRecommendedCoffeeUseCase(private val repository: GetRecommendedCoffeeRepository) :
    UseCase<GetRecommendedCoffeeResponse, GetRecommendedCoffeeRequest>() {
    override suspend fun run(
        params: GetRecommendedCoffeeRequest,
        useCache: Boolean
    ): Result<Failure, GetRecommendedCoffeeResponse?> {
        return repository.getRecommendedCoffee(params)
    }
}
