package com.learning.apilayer.repository.coffee

import com.google.gson.annotations.SerializedName
import com.learning.networks.model.BaseRequest
import com.learning.networks.model.BaseResponse
import com.learning.networks.model.FormData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

class GetRecommendedCoffeeRequest : FormData()

data class GetRecommendedCoffeeResponse(
    @SerializedName("Name") val name: String?,
    @SerializedName("Price") val price: Int?,
    @SerializedName("Description") val description: String?
) : FormData()

interface GetRecommendedCoffeeApi {
    @POST("v1/coffee/recommended")
    fun getRecommendedCoffee(@Body reqParam: BaseRequest<FormData>): Call<BaseResponse<GetRecommendedCoffeeResponse>>

    companion object {
        const val FORM_HEAD_REQ = "COFFEE0101"
    }
}
