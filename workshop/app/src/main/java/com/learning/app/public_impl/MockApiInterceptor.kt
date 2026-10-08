package com.learning.app.public_impl

import android.content.Context
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger

/**
 * ตอบ API ด้วยไฟล์ JSON ใน assets/apiData แทนการยิง server จริง (ใช้เฉพาะ flavor sit)
 * แต่ละ path มีรายการคำตอบที่วนไปเรื่อย ๆ ตามจำนวนครั้งที่เรียก
 */
class MockApiInterceptor(private val context: Context) : Interceptor {

    private val callCounts = ConcurrentHashMap<String, AtomicInteger>()

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val path = request.url.encodedPath.substringAfter("/api/")
        val mocks = MOCKS[path] ?: return chain.proceed(request)

        Thread.sleep(DELAY_MILLIS)

        val index = callCounts.getOrPut(path) { AtomicInteger() }.getAndIncrement() % mocks.size
        return buildResponse(request, readAsset(mocks[index]))
    }

    private fun readAsset(fileName: String): String =
        context.assets.open(fileName).bufferedReader().use { it.readText() }

    private fun buildResponse(request: Request, json: String): Response =
        Response.Builder()
            .request(request)
            .protocol(Protocol.HTTP_1_1)
            .code(200)
            .message("OK")
            .body(json.toResponseBody("application/json".toMediaType()))
            .build()

    companion object {
        private const val DELAY_MILLIS = 1500L

        private val MOCKS = mapOf(
            // ครั้งที่ 3 ตั้งใจให้ server ตอบว่าทำรายการไม่สำเร็จ (มี RetMsgCode) เพื่อให้เห็นสถานะ Error
            "v1/coffee/recommended" to listOf(
                "apiData/coffee/recommended_1.json",
                "apiData/coffee/recommended_2.json",
                "apiData/coffee/recommended_error.json"
            ),
            "v1/coffee/menu" to listOf("apiData/coffee/menu.json"),
            "v1/dessert/recommended" to listOf("apiData/dessert/recommended.json")
        )
    }
}
