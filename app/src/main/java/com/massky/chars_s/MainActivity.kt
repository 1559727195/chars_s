package com.massky.chars_s

import androidx.appcompat.app.AppCompatActivity
import pub.devrel.easypermissions.EasyPermissions
import android.Manifest
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.ScrollView
import android.widget.Toast
import android.os.Build
import com.massky.chars_s.utils.LogUtil


class MainActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {


    private var scroll_view: ScrollView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init_view()
        init_event()
        val f = Environment.getExternalStorageDirectory()//获取SD卡目录
        Log.e("f", f.absolutePath + "/test.txt")
        // Example of a call to a native method
        //        TextView tv = findViewById(R.id.sample_text);
        //        tv.setText(stringFromJNI(f.getAbsolutePath() + "/test.txt"));

        //通过hasPermissions检查权限
        if (EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            // 该应用已经有打电话的权限
            Toast.makeText(this, "TODO: Camera things", Toast.LENGTH_LONG).show()
        } else {
            //请求权限
            EasyPermissions.requestPermissions(this, "需要获取系统的读取的权限！", RC_CAMERA_PERM, Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        //        Glide.with(this).load("")
        //
        //                .error(R.mipmap.ic_launcher)
        //                .centerCrop()
        //                .skipMemoryCache(true)
        //                .diskCacheStrategy(DiskCacheStrategy.RESULT)
        //                .priority(Priority.HIGH)
        //                .into(mIvMn);


        //File fileDir =new File(f,"test.txt");

    }

    private fun init_event() {
        LogUtil.instance!!.debug("")

        scroll_view!!.getViewTreeObserver().addOnScrollChangedListener({
            Log.d("MainActivity", "getViewTreeObserver - onScrollChanged")
        })


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            scroll_view!!.setOnScrollChangeListener({ v, scrollX, scrollY, oldScrollX, oldScrollY ->
                Log.d("MainActivity", "setOnScrollChangeListener - onScrollChange")
            })
        }


    }

    private fun init_view() {
        scroll_view = findViewById(R.id.scroll_view)
    }


    //重新以下三个方法  1,把执行结果的操作给EasyPermissions
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    //请求成功执行相应的操作
    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        Toast.makeText(this, "onPermissionsGranted", Toast.LENGTH_SHORT).show()
    }

    //申请失败时调用
    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        Toast.makeText(this, "onPermissionsDenied", Toast.LENGTH_SHORT).show()
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(path: String): String

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }

        private val RC_CAMERA_PERM = 123
    }


}


