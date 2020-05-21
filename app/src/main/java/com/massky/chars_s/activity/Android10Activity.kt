package com.massky.chars_s.activity

import android.content.ContentValues
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream

class Android10Activity : AppCompatActivity() {
   var   button: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.massky.chars_s.R.layout.android_10)
        var cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                , null, null, null, "${MediaStore.MediaColumns.DATE_ADDED} desc")


    }


    fun addBitmapToAlbum(bitmap: Bitmap, displayName: String, mimeType: String, compressFormat: Bitmap.CompressFormat) {
        val values = ContentValues()
        values.put(MediaStore.MediaColumns.DISPLAY_NAME, displayName)
        values.put(MediaStore.MediaColumns.MIME_TYPE, mimeType)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DCIM)
        } else {
            values.put(MediaStore.MediaColumns.DATA, "${Environment.getExternalStorageDirectory().path}/${Environment.DIRECTORY_DCIM}/$displayName")
        }
        val uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        if (uri != null) {
            val outputStream = contentResolver.openOutputStream(uri)
            if (outputStream != null) {
                bitmap.compress(compressFormat, 100, outputStream)
                outputStream.close()
            }
        }
    }



    fun copyUriToExternalFilesDir(uri: Uri, fileName: String) {
       // val inputStream = contentResolver.openInputStream(uri)
       // val tempDir = getExternalFilesDir("temp")
        val inputStream =contentResolver.openInputStream(uri)
        val  tempDir = getExternalFilesDir("tempDir")
        if (inputStream != null && tempDir != null) {
           // val file = File("$tempDir/$fileName")
            val file = File("$tempDir/$fileName")
            val fos = FileOutputStream(file)
            val bis = BufferedInputStream(inputStream)
            val bos = BufferedOutputStream(fos)
            val byteArray = ByteArray(1024)
            var bytes = bis.read(byteArray)
            while (bytes > 0) {
                bos.write(byteArray, 0, bytes)
                bos.flush()
                bytes = bis.read(byteArray)
            }
            bos.close()
            fos.close()
        }
    }


    /**
     * 判断绑定滑动事件的View是不是一个基础layout，不支持自定义layout，只支持四种基本layout,
     * AbsoluteLayout已被弃用。
     *
     * @return 如果绑定滑动事件的View是LinearLayout,RelativeLayout,FrameLayout,
     * TableLayout之一就返回true，否则返回false。
     */
    private val mBindView: View? = null

    private fun isBindBasicLayout(): Boolean {
        if (mBindView == null) {
            return false
        }


        val viewName: String = mBindView.javaClass.getName()
        return viewName == LinearLayout::class.java.getName() ||
                viewName == RelativeLayout::class.java.getName() ||
                viewName == FrameLayout::class.java.getName() ||
                viewName == TableLayout::class.java.getName()
    }

}
