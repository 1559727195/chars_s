package com.massky.chars_s.activity

import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import com.massky.chars_s.R

internal class HandlerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.handler_main)

        // 这个死循环用于找出当前消息(msg)应该插入到消息列表中的哪个消息的后面（应该插入到prev这条消息的后面）


        // 这个死循环用于找出当前消息(msg)应该插入到消息列表中的哪个消息的后面（应该插入到prev这条消息的后面）
        while (true) {
            prev = p //1.prev
            p = p.next // 2.p
            if (p == null || `when` < p.`when`) {
                break
            }
            if (needWake && p.isAsynchronous()) {
                needWake = false
            }
        }
        msg.next = p; // invariant: p == prev.next // 1.msg
        prev.next = msg;//0.prev

        //上述代码解析如下:

       // prev <-p
       // prev->p
        //0.prev,1.msg,2.p =
    }


    var handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
        }
    }


}