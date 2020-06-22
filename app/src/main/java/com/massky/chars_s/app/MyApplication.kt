package com.massky.chars_s.app

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        refWatcher = LeakCanary.install(this)

    }
    //ContextImpl,ContextWrapper

    companion object {
        var instance: MyApplication? = null
            //private set
        private set

        var refWatcher:RefWatcher ? = null

        private  set

    }


    //提供给外部调用的方法
   fun getRefWatcher(): RefWatcher? {
        return refWatcher
    }


}