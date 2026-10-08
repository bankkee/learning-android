package com.learning.networks.repository

import com.learning.networks.model.BaseResponse
import com.learning.networks.model.Failure
import com.learning.networks.model.FormData
import com.learning.networks.model.Result
import retrofit2.Call
import java.io.IOException

/** base ของทุก Repository: ยิง API แกะซอง แล้วแปลงทุกผลลัพธ์ (รวมถึง exception) ให้เป็น [Result] */
abstract class NetworkDataSource {

    fun <R : FormData> requestData(call: Call<BaseResponse<R>>): Result<Failure, R> {
        return try {
            val response = call.clone().execute()
            if (response.isSuccessful) {
                val formData = response.body()?.form?.firstOrNull()?.formData
                when {
                    formData == null ->
                        Result.Error(Failure.UnKnownError(IllegalArgumentException("No Data")))
                    // HTTP สำเร็จ แต่ server แจ้งว่าทำรายการไม่ได้ผ่าน ErrorCode
                    !formData.errorCode.isNullOrEmpty() ->
                        Result.Error(Failure.ServerError(formData, formData.errorMessage ?: "API ERROR"))
                    else -> Result.Success(formData)
                }
            } else {
                when {
                    response.code() >= ERROR_SERVER -> Result.Error(Failure.ServiceUnavailable())
                    else -> Result.Error(Failure.ServerError(response.code().toString()))
                }
            }
        } catch (exception: IOException) {
            Result.Error(Failure.NetworkConnection())
        } catch (exception: Exception) {
            Result.Error(Failure.UnKnownError(exception))
        }
    }

    companion object {
        const val ERROR_SERVER = 500
    }
}
