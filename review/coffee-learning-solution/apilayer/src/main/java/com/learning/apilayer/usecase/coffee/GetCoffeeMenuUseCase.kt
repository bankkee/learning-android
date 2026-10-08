// ผู้เรียนสร้างไฟล์นี้เองทั้งไฟล์: Session 5 · ภารกิจ 1 (ทำตามแบบ GetRecommendedCoffeeUseCase)
package com.learning.apilayer.usecase.coffee

import com.learning.apilayer.repository.coffee.GetCoffeeMenuRepository
import com.learning.apilayer.repository.coffee.GetCoffeeMenuRequest
import com.learning.apilayer.repository.coffee.GetCoffeeMenuResponse
import com.learning.networks.model.Failure
import com.learning.networks.model.Result
import com.learning.networks.usecase.UseCase

class GetCoffeeMenuUseCase(private val repository: GetCoffeeMenuRepository) :
    UseCase<GetCoffeeMenuResponse, GetCoffeeMenuRequest>() {
    override suspend fun run(
        params: GetCoffeeMenuRequest,
        useCache: Boolean
    ): Result<Failure, GetCoffeeMenuResponse?> {
        return repository.getCoffeeMenu(params)
    }
}
