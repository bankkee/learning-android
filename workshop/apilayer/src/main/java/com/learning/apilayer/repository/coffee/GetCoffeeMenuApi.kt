package com.learning.apilayer.repository.coffee

import com.google.gson.annotations.SerializedName
import com.learning.networks.model.BaseRequest
import com.learning.networks.model.BaseResponse
import com.learning.networks.model.FormData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

class GetCoffeeMenuRequest : FormData()

data class CoffeeMenuItem(
    @SerializedName("Name") val name: String?,
    @SerializedName("Price") val price: Int?
)

data class GetCoffeeMenuResponse(
    @SerializedName("Items") val items: List<CoffeeMenuItem>?
) : FormData()

interface GetCoffeeMenuApi {
    @POST("v1/coffee/menu")
    fun getCoffeeMenu(@Body reqParam: BaseRequest<FormData>): Call<BaseResponse<GetCoffeeMenuResponse>>

    companion object {
        const val API_CODE = "COFFEE0201"
    }
}
