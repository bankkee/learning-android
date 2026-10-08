package com.learning.networks.model

sealed class Failure(message: String? = "", ex: Exception = Exception()) : Exception(message, ex) {

    class NetworkConnection : Failure("Please check your Internet connection")

    class ServiceUnavailable : Failure("Service is not available")

    /** server ตอบกลับมาได้ แต่แจ้งว่าทำรายการไม่สำเร็จ [formData] คือสิ่งที่ server ส่งมา (มี errorCode และ errorMessage) */
    class ServerError(
        message: String? = "Server Error",
        ex: Exception = Exception()
    ) : Failure(message, ex) {
        var formData: FormData? = null

        constructor(
            formData: FormData,
            message: String? = "Server Error",
            ex: Exception = Exception()
        ) : this(message, ex) {
            this.formData = formData
        }
    }

    class UnKnownError(ex: Exception = Exception(), message: String? = "Unknow Error") : Failure(ex = ex, message = message)
}
