// ผู้เรียนสร้างไฟล์นี้เองทั้งไฟล์: Session 6 · ภารกิจ 3 (ไม่มีโครงให้ มีแค่สเปกของ API)
package com.learning.apilayer.repository.dessert

import com.google.gson.annotations.SerializedName
import com.learning.networks.model.BaseRequest
import com.learning.networks.model.BaseResponse
import com.learning.networks.model.FormData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

class GetRecommendedDessertRequest : FormData()

data class GetRecommendedDessertResponse(
    @SerializedName("Name") val name: String?,
    @SerializedName("Price") val price: Int?
) : FormData()

interface GetRecommendedDessertApi {
    @POST("v1/dessert/recommended")
    fun getRecommendedDessert(@Body reqParam: BaseRequest<FormData>): Call<BaseResponse<GetRecommendedDessertResponse>>

    companion object {
        const val FORM_HEAD_REQ = "DESSERT0101"
    }
}
