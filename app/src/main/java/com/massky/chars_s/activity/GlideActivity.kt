package com.massky.chars_s.activity

import android.os.Bundle
import android.widget.ImageView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.ViewTarget


import com.massky.chars_s.view.FutureStudioView
import android.R
import android.animation.ObjectAnimator
import android.widget.RemoteViews

import android.app.NotificationManager
import android.content.Context
import android.graphics.Bitmap
import androidx.core.app.NotificationCompat
import com.bumptech.glide.request.target.NotificationTarget



class GlideActivity : AppCompatActivity()  {
    private var img: ImageView? = null

    private var list_view: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.massky.chars_s.R.layout.glide_lay)
        init_view()
    }

    private fun init_view() {
        img = findViewById(com.massky.chars_s.R.id.img)
        list_view = findViewById(com.massky.chars_s.R.id.list_view)

    }


    private fun init_data() {
        // list_view!!.setAdapter(ImageListAdapter(this@GlideActivity, eatFoodyImages))
        Glide.with(this).load("")
        val rv = RemoteViews(getPackageName(), com.massky.chars_s.R.layout.remoteview_notification)
        //.placeholder(R.mipmap.ic_launcher) // can also be a drawable.into(imageViewPlaceholder)

        rv.setImageViewResource(com.massky.chars_s.R.id.remoteview_notification_icon, com.massky.chars_s.R.mipmap.ic_launcher);

        rv.setTextViewText(com.massky.chars_s.R.id.remoteview_notification_headline, "Headline");
        rv.setTextViewText(com.massky.chars_s.R.id.remoteview_notification_short_message, "Short Message");


// build notification
        val mBuilder = NotificationCompat.Builder(this)
                .setSmallIcon(com.massky.chars_s.R.mipmap.ic_launcher)
                .setContentTitle("Content Title")
                .setContentText("Content Text")
                .setContent(rv)
                .setPriority(NotificationCompat.PRIORITY_MIN)

        val notification = mBuilder.build()

// set big content view for newer androids
        if (android.os.Build.VERSION.SDK_INT >= 16) {
            notification.bigContentView = rv
        }

        val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotificationManager.notify(NOTIFICATION_ID, notification)





//        Glide.with(applicationContext)
//                .load(eatFoodyImages[3])
//                .listener(object : RequestListener<Bitmap> {
//                    override fun onResourceReady(resource: Bitmap?, model: Any?, target: Target<Bitmap>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
//
//                    }
//
//                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Bitmap>?, isFirstResource: Boolean): Boolean {
//
//                    }
//                })
//                .into(notificationTarget)


//        Glide.with(this).asBitmap()
//                .load(eatFoodyImages[3])
//                .listener(object : RequestListener<Bitmap> {
//                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Bitmap>?, isFirstResource: Boolean): Boolean {
//                        return false
//                    }
//
//                    override fun onResourceReady(resource: Bitmap?, model: Any?, target: Target<Bitmap>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
//                        return false
//                    }
//                }
//                ).submit();


    }

//    private val requestListener = object : RequestListener<String, GlideDrawable> {
//        fun onException(e: Exception, model: String, target: Target<GlideDrawable>, isFirstResource: Boolean): Boolean {
//            // todo log exception
//
//            // important to return false so the error placeholder can be placed
//            return false
//        }
//
//        fun onResourceReady(resource: GlideDrawable, model: String, target: Target<GlideDrawable>, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
//            return false
//        }
//    }


    private var notificationTarget: NotificationTarget? = null
    val NOTIFICATION_ID = 0

    private fun notificationTargets() {




        // if it's a custom view class, cast it here
        // then find subviews and do the animations
        // here, we just use the entire view for the fade animation
       // view.setAlpha( 0f );



    }

    private fun loadImageViewTarget() {
//        val customView = findViewById(com.massky.chars_s.R.id.custom_view) as FutureStudioView
//
//        viewTarget = object : ViewTarget<FutureStudioView, GlideDrawable>(customView) {
//            override fun onResourceReady(resource: GlideDrawable, transition: GlideAnimation<in GlideDrawable>?) {
//                this.view.setImage(resource.getCurrent())
//            }
//        }
//
//        Glide
//                .with(getApplicationContext()) // safer!
//                .load(eatFoodyImages[2])
//                .into(viewTarget)


    }





    var eatFoodyImages = arrayOf(
            "http://i.imgur.com/rFLNqWI.jpg",
            "http://i.imgur.com/C9pBVt7.jpg",
            "http://i.imgur.com/rT5vXE1.jpg",
            "http://i.imgur.com/aIy5R2k.jpg",
            "http://i.imgur.com/MoJs9pT.jpg",
            "http://i.imgur.com/S963yEM.jpg",
            "http://i.imgur.com/rLR2cyc.jpg",
            "http://i.imgur.com/SEPdUIx.jpg",
            "http://i.imgur.com/aC9OjaM.jpg",
            "http://i.imgur.com/76Jfv9b.jpg",
            "http://i.imgur.com/fUX7EIB.jpg",
            "http://i.imgur.com/syELajx.jpg",
            "http://i.imgur.com/COzBnru.jpg",
            "http://i.imgur.com/Z3QjilA.jpg")


}
