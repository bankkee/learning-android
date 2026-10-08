package com.learning.core.extension

import com.learning.core.R
import com.learning.core.base.BaseActivity
import com.learning.networks.model.Response

/**
 * แตก 3 สถานะของ [Response] ให้: แสดง/ซ่อน loading, แสดง dialog ตอน error, เรียก [onSuccess] ตอนสำเร็จ
 *
 * @param onError คืน true เมื่อจัดการ error เองแล้ว (ไม่ต้องแสดง dialog มาตรฐาน)
 */
fun <T> BaseActivity.handleResponse(
    response: Response<T>,
    onLoading: (show: Boolean) -> Unit = { showLoading(it) },
    onError: (error: Response.Error) -> Boolean = { false },
    onSuccess: (data: T) -> Unit
) {
    when (response) {
        is Response.Loading -> onLoading(true)
        is Response.Success -> {
            onLoading(false)
            onSuccess(response.value)
        }
        is Response.Error -> {
            onLoading(false)
            if (!onError(response)) {
                showAlertDialog(response.failure.message ?: getString(R.string.common_error))
            }
        }
    }
}
