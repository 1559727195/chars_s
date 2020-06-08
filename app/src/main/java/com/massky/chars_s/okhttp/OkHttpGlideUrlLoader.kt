package com.massky.chars_s.okhttp

import android.content.Context
import com.bumptech.glide.load.data.DataFetcher
import com.bumptech.glide.load.model.GenericLoaderFactory
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoaderFactory
import okhttp3.OkHttpClient
import java.io.InputStream

class OkHttpGlideUrlLoader(private val okHttpClient: OkHttpClient) : ModelLoader<GlideUrl?, InputStream> {

    class Factory : ModelLoaderFactory<GlideUrl, InputStream> {
        private var client: OkHttpClient? = null

        constructor() {}
        constructor(client: OkHttpClient?) {
            this.client = client
        }

        @Synchronized
        private fun getOkHttpClient(): OkHttpClient {
            if (client == null) {
                client = OkHttpClient()
            }
            return client!!
        }

        override fun build(context: Context, factories: GenericLoaderFactory): OkHttpGlideUrlLoader {
            return OkHttpGlideUrlLoader(getOkHttpClient())
        }

        override fun teardown() {}
    }

    override fun getResourceFetcher(model: GlideUrl?, width: Int, height: Int): OkHttpFetcher {
        return OkHttpFetcher(okHttpClient, model!!)
    }

}