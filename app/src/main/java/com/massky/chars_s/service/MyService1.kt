package com.massky.chars_s.service

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.os.Process.myPid
import android.os.RemoteException
import android.util.Log
import com.massky.chars_s.MainActivity
import com.massky.chars_s.MyAIDLService
import com.massky.chars_s.R
import kotlin.math.log


class MyService1 : Service() {


   // private val mBinder = MyBinder()


    override fun onCreate() {
        super.onCreate()


        Log.d("TAG", "process id is " + myPid());

        val notification = Notification(R.drawable.ic_launcher_background,
                "有通知到来", System.currentTimeMillis())
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0)
        //notification.setLatestEventInfo(this, "这是通知的标题", "这是通知的内容",
                //pendingIntent)
        startForeground(1, notification)
       // Log.d(FragmentActivity.TAG, "onCreate() executed")


        Log.d(TAG, "onCreate() executed")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand() executed")

        Thread(Runnable {
            // 开始执行后台任务
        }).start()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() executed")
    }

    override fun onBind(intent: Intent): IBinder? {
        return mBinder
    }


    var mBinder: MyAIDLService.Stub = object : MyAIDLService.Stub() {
        @Throws(RemoteException::class)
        override fun toUpperCase(str: String?): String? {
            return str?.toUpperCase()
        }

        @Throws(RemoteException::class)
        override fun plus(a: Int, b: Int): Int {
            Log.e("TAG","plus:")
            return a + b
        }
    }


    internal class MyBinder : Binder() {
        fun startDownload() {
            Log.d("TAG", "startDownload() executed")
            // 执行具体的下载任务
            Thread(Runnable {

            }).start()
        }
    }





    companion object {
        const val TAG = "MyService"
    }
}