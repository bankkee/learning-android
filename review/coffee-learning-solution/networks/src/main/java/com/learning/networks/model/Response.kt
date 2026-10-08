package com.learning.networks.model

/** สถานะของหน้าจอที่ ViewModel ส่งออกทาง LiveData เป็นได้ทีละสถานะเท่านั้น */
sealed class Response<out T> {
    class Error(val failure: Failure) : Response<Nothing>()
    object Loading : Response<Nothing>()
    class Success<T>(val value: T) : Response<T>()
}
