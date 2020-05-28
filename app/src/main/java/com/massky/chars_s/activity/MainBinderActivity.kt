package com.massky.chars_s.activity

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import android.view.View
import android.widget.Button
import com.massky.chars_s.MyAIDLService
import com.massky.chars_s.R
import com.massky.chars_s.service.MyService
import com.massky.chars_s.service.MyService1
import com.massky.chars_s.service.MyService1.MyBinder

class MainBinderActivity : Activity(), View.OnClickListener {
    private var startService: Button? = null
    private var stopService: Button? = null
    private var bindService: Button? = null
    private var unbindService: Button? = null
    private var myBinder: MyBinder? = null
    private val connection: ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName) {}
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
          //  myBinder = service as MyBinder
//            myBinder = service as MyBinder
//            myBinder!!.startDownload()


           var myAIDLService = MyAIDLService.Stub.asInterface(service)
            try {

                val result: Int = myAIDLService.plus(3, 5)
                val upperStr: String = myAIDLService.toUpperCase("hello world")
                Log.d("TAG", "result is $result")
                Log.d("TAG", "upperStr is $upperStr")
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_binder_main)
        startService = findViewById<View>(R.id.start_service) as Button
        stopService = findViewById<View>(R.id.stop_service) as Button
        bindService = findViewById<View>(R.id.bind_service) as Button
        unbindService = findViewById<View>(R.id.unbind_service) as Button
        startService!!.setOnClickListener(this)
        stopService!!.setOnClickListener(this)
        bindService!!.setOnClickListener(this)
        unbindService!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.start_service -> {
                val startIntent = Intent(this, MyService1::class.java)
                startService(startIntent)
            }
            R.id.stop_service -> {
                val stopIntent = Intent(this, MyService1::class.java)
                stopService(stopIntent)
            }
            R.id.bind_service -> {
                val bindIntent = Intent(this, MyService1::class.java)
             //   bindService(bindIntent, connection, Context.BIND_AUTO_CREATE)
                bindService(bindIntent,connection,Context.BIND_AUTO_CREATE)
            }
            R.id.unbind_service ->
                //unbindService(connection)
            unbindService(connection)
            else -> {
            }
        }
    }
}