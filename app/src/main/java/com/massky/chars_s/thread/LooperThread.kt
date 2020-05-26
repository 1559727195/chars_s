package com.massky.chars_s.thread

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log

class LooperThread : Thread() {
    var mHandler: Handler? = null
    override fun run() {
        // TODO Auto-generated method stub
        Looper.prepare()
        synchronized(this) {
         //   mHandler = object : Handler() {
            mHandler = object :Handler() {
                override fun handleMessage(msg: Message) {
                    // TODO Auto-generated method stub
                    super.handleMessage(msg)
                    Log.w("LooperThread", "handleMessage::Thread id---$id")
                }
            }
        }
        Looper.loop()
       // notifyAll()
    }
}