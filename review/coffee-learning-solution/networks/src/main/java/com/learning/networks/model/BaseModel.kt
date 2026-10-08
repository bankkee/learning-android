package com.learning.networks.model

import com.google.gson.annotations.SerializedName

// ซองที่ห่อ request และ response ของทุก API
// { "Form": [ { "FormHead": { "FormID": "..." }, "FormData": { ...ข้อมูลจริง... } } ] }

data class BaseRequest<T : FormData>(@SerializedName("Form") val form: List<Form<T>>)
data class BaseResponse<T : FormData>(@SerializedName("Form") val form: List<Form<T>>)

class Form<T : FormData>(
    @SerializedName("FormHead") val formHead: FormHead,
    @SerializedName("FormData") val formData: T?
)

/** ข้อมูลจริงของ API ทุก Request และ Response สืบทอด class นี้ */
abstract class FormData {
    /** server ใส่ค่านี้มาเมื่อทำรายการไม่สำเร็จ ถ้าว่างแปลว่าสำเร็จ */
    @SerializedName("RetMsgCode") open val retMsgCode: String? = null
    @SerializedName("RetMessage") open val retMessage: String? = null
}

/** หัวซอง: รหัสที่บอก server ว่านี่คือฟอร์มของ API ไหน */
open class FormHead(
    @SerializedName("FormID") open var formID: String? = null
)
