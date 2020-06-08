package com.massky.chars_s.okhttp

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.GlideModule
import com.massky.chars_s.interceptor.ProgressInterceptor
import okhttp3.OkHttpClient
import java.io.InputStream

class MyGlideModule : GlideModule {
    override fun applyOptions(context: Context, builder: GlideBuilder) {

    }
    override fun registerComponents(context: Context, glide: Glide) {
        //  glide.register(GlideUrl.class, InputStream.class, new OkHttpGlideUrlLoader.Factory());

//        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        builder.addInterceptor(new ProgressInterceptor());
//        OkHttpClient okHttpClient = builder.build();

       //var  builder = OkHttpClient.Builder

        val builder = OkHttpClient.Builder()
        builder.addInterceptor(ProgressInterceptor())
        val okHttpClient = builder.build()
        glide.register(GlideUrl::class.java, InputStream::class.java, OkHttpGlideUrlLoader.Factory(okHttpClient))





    }
}