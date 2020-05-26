package com.massky.chars_s.activity

import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.*
import android.util.Log
import android.util.LruCache
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.massky.chars_s.R
import com.massky.chars_s.service.MyService

class AnimalActivity : AppCompatActivity() {
    var imageView: ImageView? = null
    private var mMemoryCache: LruCache<String?, Bitmap>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView = findViewById<View>(R.id.image_view) as ImageView


        // for (i in 0..9) {
        for (i in 0..9) {
            val intent = Intent(this@AnimalActivity, MyService::class.java)
            startService(intent)
        }


        // val mHandlerThread = HandlerThread("LooperThread")
        val mHandlerThread = HandlerThread("looperThread")
        mHandlerThread.start()
        //   val mHandler: Handler = object : Handler(mHandlerThread.looper) {
        val mHandler: Handler = object : Handler(mHandlerThread.looper) {
            override fun handleMessage(msg: Message) {
                // TODO Auto-generated method stub
                super.handleMessage(msg)
                Log.w("LooperThread", "handleMessage::Thread id---" + mHandlerThread.id)
            }
        }

        object : Thread() {
            override fun run() {
                // TODO Auto-generated method stub
                mHandler.sendEmptyMessage(0)
                // Log.w(TAG, "Send Message::Thread id ---" + getId());
            }
        }.start()


    }

    // 使用manifest文件 ->standard,single_top,new_task,new_instance,

    //Intent flags
    //flag_activity_new_task,
    // flag_activity_clear_top,
    // flag_activity_single_top

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }


    fun loadImage(view: View?) {
        val url = "http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg"
        //  Glide.with(this).load(url).into(imageView);
        Glide
                .with(this)
                .load(url)
                .asGif()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView)
    }


    private fun get_memory() {
        val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
        Log.d("TAG", "Max memory is " + maxMemory + "KB")


        // 使用最大可用内存值的1/8作为缓存的大小。
        val cacheSize = maxMemory / 8
        //  mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
        mMemoryCache = object : LruCache<String?, Bitmap>(cacheSize) {
            override fun sizeOf(key: String?, bitmap: Bitmap): Int {
                // 重写此方法来衡量每张图片的大小，默认返回图片数量。
                // return bitmap.getByteCount() / 1024;
                return bitmap.byteCount / 1024
            }
        }
        val options = BitmapFactory.Options()
        // options.inJustDecodeBounds = true;
        options.inJustDecodeBounds = true //将这个参数的inJustDecodeBounds属性设置为true就可以让解析方法禁止为bitmap分配内存
        options.inSampleSize = 4 //将inSampleSize的值设置为4-缩放宽高倍数4 * 4
        BitmapFactory.decodeResource(resources, R.id.image_view, options)

        //imageview - 128 * 96
        //1024*768像素-realy
        val imageHeight = options.outHeight
        val imageWidth = options.outWidth
        val imageType = options.outMimeType
    }

    fun addBitmapToMemoryCache(key: String?, bitmap: Bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache!!.put(key, bitmap)
        }
    }

    fun getBitmapFromMemCache(key: String?): Bitmap? {
        return mMemoryCache!![key]
    }

    fun loadBitmap(resId: Int, imageView: ImageView) {
        val imageKey = resId.toString()
        val bitmap = getBitmapFromMemCache(imageKey)
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap)
        } else {
            imageView.setImageResource(R.drawable.ic_launcher_background)
            val task = BitmapWorkerTask()
            task.execute(resId)
        }
    }

    internal inner class BitmapWorkerTask : AsyncTask<Int?, Void?, Bitmap>() {
        protected override fun doInBackground(vararg params: Int?): Bitmap? {
            val bitmap = decodeSampledBitmapFromResource(
                    resources, params[0]!!, 100, 100)
            addBitmapToMemoryCache(params[0].toString(), bitmap)
            return bitmap
        }
    }

    companion object {
        fun calculateInSampleSize(options: BitmapFactory.Options,
                                  reqWidth: Int, reqHeight: Int): Int {
            // 源图片的高度和宽度
            val height = options.outHeight
            val width = options.outWidth
            var inSampleSize = 1
            if (height > reqHeight || width > reqWidth) {
                // 计算出实际宽高和目标宽高的比率
                val heightRatio = Math.round(height.toFloat() / reqHeight.toFloat())
                val widthRatio = Math.round(width.toFloat() / reqWidth.toFloat())
                // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
                // 一定都会大于等于目标的宽和高。
                inSampleSize = if (heightRatio < widthRatio) heightRatio else widthRatio
            }
            return inSampleSize
        }

        // 使用这个方法，首先你要将BitmapFactory.Options的inJustDecodeBounds属性设置为true，解析一次图片。然后将BitmapFactory.Options连同期望的宽度和高度一起传递到到calculateInSampleSize方法中，就可以得到合适的inSampleSize值了。之后再解析一次图片，使用新获取到的inSampleSize值，并把inJustDecodeBounds设置为false，就可以得到压缩后的图片了。
        fun decodeSampledBitmapFromResource(res: Resources?, resId: Int,
                                            reqWidth: Int, reqHeight: Int): Bitmap {
            // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
            val options = BitmapFactory.Options()
            options.inJustDecodeBounds = true
            BitmapFactory.decodeResource(res, resId, options)
            // 调用上面定义的方法计算inSampleSize值
            options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)
            // 使用获取到的inSampleSize值再次解析图片
            options.inJustDecodeBounds = false
            return BitmapFactory.decodeResource(res, resId, options)
        }
    }
}