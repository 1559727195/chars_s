package com.massky.chars_s.okhttp

import com.bumptech.glide.Priority
import com.bumptech.glide.load.data.DataFetcher
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.util.ContentLengthInputStream
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody
import java.io.IOException
import java.io.InputStream

class OkHttpFetcher(private val client: OkHttpClient, private val url: GlideUrl) : DataFetcher<InputStream?> {
    private var stream: InputStream? = null
    private var responseBody: ResponseBody? = null

    @Volatile
    private var isCancelled = false

    @Throws(Exception::class)
    override fun loadData(priority: Priority): InputStream? {
        val requestBuilder = Request.Builder()
                .url(url.toStringUrl())
        for ((key, value) in url.headers) {
            requestBuilder.addHeader(key, value)
        }
        val request = requestBuilder.build()
        if (isCancelled) {
            return null
        }
        val response = client.newCall(request).execute()
        responseBody = response.body()
        if (!response.isSuccessful || responseBody == null) {
            throw IOException("Request failed with code: " + response.code())
        }
        stream = ContentLengthInputStream.obtain(responseBody!!.byteStream(),
                responseBody!!.contentLength())
        return stream
    }

    override fun cleanup() {
        try {
            if (stream != null) {
                stream!!.close()
            }
            if (responseBody != null) {
                responseBody!!.close()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun getId(): String {
        return url.cacheKey
    }

    override fun cancel() {
        isCancelled = true
    }

}