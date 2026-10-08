// ผู้เรียนสร้างไฟล์นี้เองทั้งไฟล์: Session 6 · ภารกิจ 3 (ไม่มีโครงให้ มีแค่สเปกของ API)
package com.learning.apilayer.repository.dessert

import com.learning.networks.model.BaseRequestHelper.getBaseData
import com.learning.networks.model.Failure
import com.learning.networks.model.FormData
import com.learning.networks.model.FormHead
import com.learning.networks.model.Result
import com.learning.networks.repository.NetworkDataSource

class GetRecommendedDessertRepository(
    private val getRecommendedDessertApi: GetRecommendedDessertApi
) : NetworkDataSource() {
    fun getRecommendedDessert(request: FormData): Result<Failure, GetRecommendedDessertResponse> {
        return requestFormData(
            getRecommendedDessertApi.getRecommendedDessert(
                getBaseData(
                    request,
                    FormHead(GetRecommendedDessertApi.FORM_HEAD_REQ)
                )
            )
        )
    }
}
