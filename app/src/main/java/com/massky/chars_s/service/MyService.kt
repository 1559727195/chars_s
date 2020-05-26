package com.massky.chars_s.service

import android.app.IntentService
import android.content.Intent
import android.util.Log

//class MyService : IntentService(TAG) {
class MyService:IntentService(TAG){
    private var count = 0
    override fun onHandleIntent(intent: Intent?) {
        // TODO Auto-generated method stub
        //在这里添加我们要执行的代码，Intent中可以保存我们所需的数据，
        //每一次通过Intent发送的命令将被顺序执行
        count++
        Log.w(TAG, "count::$count")
    }

    companion object {
        private val TAG = MyService::class.java.simpleName
    }
}