package com.massky.chars_s.activity

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.massky.chars_s.R

class SlideActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.slide_menu)
    }


    override fun onClick(v: View?) {

    }


    /**
     * 滚动显示和隐藏menu时，手指滑动需要达到的速度。
     */

       private var   SNAP_VELOCITY:Int ? = 200

        /**
         * 屏幕宽度值。
         */
        private var  screenWidth :Int ? = 0

        /**
         * menu最多可以滑动到的左边缘。值由menu布局的宽度来定，marginLeft到达此值之后，不能再减少。
         */
        private var leftEdge =0;

        /**
         * menu最多可以滑动到的右边缘。值恒为0，即marginLeft到达0之后，不能增加。
         */
        private var rightEdge = 0;

        /**
         * menu完全显示时，留给content的宽度值。
         */
        private var menuPadding = 80;

        /**
         * 主内容的布局。
         */
        private var content:View? = null;

        /**
         * menu的布局。
         */
        private var menu:View? = null;

        /**
         * menu布局的参数，通过此参数来更改leftMargin的值。
         */
        private var menuParams: LinearLayout.LayoutParams ? = null;

        /**
         * 记录手指按下时的横坐标。
         */
        private var xDown = 0;

        /**
         * 记录手指移动时的横坐标。
         */
        private var xMove = 0;

        /**
         * 记录手机抬起时的横坐标。
         */
        private var xUp = 0;

        /**
         * menu当前是显示还是隐藏。只有完全显示或隐藏menu时才会更改此值，滑动过程中此值无效。
         */
        private var isMenuVisible:Boolean ? = false;

        /**
         * 用于计算手指滑动的速度。
         */
        private var mVelocityTracker: VelocityTracker? = null


    /**
     * 初始化一些关键性数据。包括获取屏幕的宽度，给content布局重新设置宽度，给menu布局重新设置宽度和偏移距离等。
     */
    private fun initValues() {
        val window = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        screenWidth = window.defaultDisplay.width
        content = findViewById(R.id.content)
        menu = findViewById(R.id.menu)
      //  menuParams = menu!!.getLayoutParams() as LinearLayout.LayoutParams
        // 将menu的宽度设置为屏幕宽度减去menuPadding
        menuParams!!.width = screenWidth!! - menuPadding
        // 左边缘的值赋值为menu宽度的负数
        leftEdge = -menuParams!!.width
        // menu的leftMargin设置为左边缘的值，这样初始化时menu就变为不可见
        menuParams!!.leftMargin = leftEdge
        // 将content的宽度设置为屏幕宽度
        content!!.getLayoutParams().width = screenWidth as Int



    }

    fun onTouch(v: View, event: MotionEvent): Boolean {
        createVelocityTracker(event)
        when (event.action) {
           // MotionEvent.ACTION_DOWN ->
            MotionEvent.ACTION_DOWN->
                // 手指按下时，记录按下时的横坐标
                xDown = event.rawX.toInt()
          //  MotionEvent.ACTION_MOVE -> {
            MotionEvent.ACTION_MOVE->{
                // 手指移动时，对比按下时的横坐标，计算出移动的距离，来调整menu的leftMargin值，从而显示和隐藏menu
                xMove = event.rawX.toInt()
                val distanceX = xMove - xDown
                if (isMenuVisible!!) {
                    menuParams!!.leftMargin = distanceX //进去
                } else {
                    menuParams!!.leftMargin = leftEdge + distanceX //出来
                }

                if (menuParams!!.leftMargin < leftEdge) {
                    menuParams!!.leftMargin = leftEdge
                } else if (menuParams!!.leftMargin > rightEdge) {
                    menuParams!!.leftMargin = rightEdge
                }
              //  menu!!.setLayoutParams(menuParams)
                menu!!.setLayoutParams(menuParams)
            }
          //  MotionEvent.ACTION_UP -> {
            MotionEvent.ACTION_UP-> {
                // 手指抬起时，进行判断当前手势的意图，从而决定是滚动到menu界面，还是滚动到content界面
                xUp = event.rawX.toInt()
                if (wantToShowMenu()) {
                    if (shouldScrollToMenu()) {
                        scrollToMenu()
                    } else {
                        scrollToContent()
                    }
                } else if (wantToShowContent()) {
                    if (shouldScrollToContent()) {
                        scrollToContent()
                    } else {
                        scrollToMenu()
                    }
                }
                recycleVelocityTracker()
            }
        }
        return true
    }


    /**
     * 判断当前手势的意图是不是想显示content。如果手指移动的距离是负数，且当前menu是可见的，则认为当前手势是想要显示content。
     *
     * @return 当前手势想显示content返回true，否则返回false。
     */
    private fun wantToShowContent(): Boolean {
        return xUp - xDown < 0 && isMenuVisible!!
    }

    /**
     * 判断当前手势的意图是不是想显示menu。如果手指移动的距离是正数，且当前menu是不可见的，则认为当前手势是想要显示menu。
     *
     * @return 当前手势想显示menu返回true，否则返回false。
     */
    private fun wantToShowMenu(): Boolean {
        return xUp - xDown > 0 && !isMenuVisible!!
    }

    /**
     * 判断是否应该滚动将menu展示出来。如果手指移动距离大于屏幕的1/2，或者手指移动速度大于SNAP_VELOCITY，
     * 就认为应该滚动将menu展示出来。
     *
     * @return 如果应该滚动将menu展示出来返回true，否则返回false。
     */
    private fun shouldScrollToMenu(): Boolean {
        return xUp - xDown > screenWidth!!  /  2 || getScrollVelocity() > SNAP_VELOCITY!!
    }

    /**
     * 判断是否应该滚动将content展示出来。如果手指移动距离加上menuPadding大于屏幕的1/2，
     * 或者手指移动速度大于SNAP_VELOCITY， 就认为应该滚动将content展示出来。
     *
     * @return 如果应该滚动将content展示出来返回true，否则返回false。
     */
    private fun shouldScrollToContent(): Boolean {
        return xDown - xUp + menuPadding > screenWidth!! / 2 || getScrollVelocity() > SNAP_VELOCITY!!
    }

    /**
     * 将屏幕滚动到menu界面，滚动速度设定为30.
     */
    private fun scrollToMenu() {
        ScrollTask().execute(30)
    }

    /**
     * 将屏幕滚动到content界面，滚动速度设定为-30.
     */
    private fun scrollToContent() {
        ScrollTask().execute(-30)
    }

    /**
     * 创建VelocityTracker对象，并将触摸content界面的滑动事件加入到VelocityTracker当中。
     *
     * @param event
     * content界面的滑动事件
     */
    private fun createVelocityTracker(event: MotionEvent) {
        if (mVelocityTracker == null) {
           // mVelocityTracker = VelocityTracker.obtain()
            mVelocityTracker = VelocityTracker.obtain()
        }
        mVelocityTracker!!.addMovement(event)
        //mVelocityTracker!!.addMovement(event)
    }

    /**
     * 获取手指在content界面滑动的速度。
     *
     * @return 滑动速度，以每秒钟移动了多少像素值为单位。
     */
    private fun getScrollVelocity(): Int {
        mVelocityTracker!!.computeCurrentVelocity(1000)
        //val velocity = mVelocityTracker!!.getXVelocity() as Int
        val  velocity = mVelocityTracker!!.getXVelocity() as Int
        return Math.abs(velocity)
    }

    /**
     * 回收VelocityTracker对象。
     */
    private fun recycleVelocityTracker() {
        mVelocityTracker?.recycle()
        mVelocityTracker = null
    }

    internal inner class ScrollTask : AsyncTask<Int, Int, Int>() {

        override fun doInBackground(vararg speed: Int?): Int {
            var leftMargin = menuParams!!.leftMargin
            // 根据传入的速度来滚动界面，当滚动到达左边界或右边界时，跳出循环。
            while (true) {
                leftMargin = leftMargin + speed[0]!!
                if (leftMargin > rightEdge) {
                    leftMargin = rightEdge
                    break
                }
                if (leftMargin < leftEdge) {
                    leftMargin = leftEdge
                    break
                }
                publishProgress(leftMargin)
                // 为了要有滚动效果产生，每次循环使线程睡眠20毫秒，这样肉眼才能够看到滚动动画。
                sleep(20)
            }
            if (speed[0]!! > 0) {
                isMenuVisible = true
            } else {
                isMenuVisible = false
            }
            return leftMargin
        }

        override fun onProgressUpdate(vararg leftMargin: Int?) {
            menuParams!!.leftMargin = leftMargin[0]!!
            menu!!.setLayoutParams(menuParams)
        }


        override fun onPostExecute(leftMargin: Int?) {

            menuParams!!.leftMargin = leftMargin!!
            menu!!.setLayoutParams(menuParams)

        }



    }

    /**
     * 使当前线程睡眠指定的毫秒数。
     *
     * @param millis
     * 指定当前线程睡眠多久，以毫秒为单位
     */
    private fun sleep(millis: Long) {
        try {
            Thread.sleep(millis)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

    }


}