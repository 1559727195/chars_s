package com.massky.chars_s.app

import android.app.Application

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
    //ContextImpl,ContextWrapper

    companion object {
        var instance: MyApplication? = null
            //private set
        private set
    }
}