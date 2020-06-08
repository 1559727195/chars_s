package com.massky.chars_s.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.IOException

class ProgressInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()

        var response = chain.proceed(request)
        var url = request.url().toString()
        var body = response.body()
        var newResponse  = response.newBuilder().body(
             ProgressResponseBody(url,body!!)).build()
        return newResponse
    }

    companion object {

         val LISTENER_MAP: MutableMap<String, ProgressListener> = HashMap()
        fun addListener(url: String, listener: ProgressListener) {
            LISTENER_MAP[url] = listener
        }

        fun removeListener(url: String) {
            LISTENER_MAP.remove(url)
        }
    }
}