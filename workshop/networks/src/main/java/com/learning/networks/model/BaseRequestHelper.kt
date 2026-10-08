package com.learning.networks.model

object BaseRequestHelper {
    /** ห่อ [data] ลงในซองพร้อมหัวซอง [head] */
    fun getBaseData(data: FormData, head: FormHead): BaseRequest<FormData> {
        return BaseRequest(listOf(Form(head, data)))
    }
}
