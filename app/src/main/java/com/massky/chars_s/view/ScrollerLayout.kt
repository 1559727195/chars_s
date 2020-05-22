package com.massky.chars_s.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewConfiguration
import android.view.ViewGroup
import android.widget.Scroller
import androidx.core.view.ViewConfigurationCompat

class ScrollerLayout(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {
    /**
     * 用于完成滚动操作的实例
     */
    private val mScroller: Scroller

    /**
     * 判定为拖动的最小移动像素数
     */
    private val mTouchSlop: Int

    /**
     * 手机按下时的屏幕坐标
     */
    private var mXDown = 0f

    /**
     * 手机当时所处的屏幕坐标
     */
    private var mXMove = 0f

    /**
     * 上次触发ACTION_MOVE事件时的屏幕坐标
     */
    private var mXLastMove = 0f

    /**
     * 界面可滚动的左边界
     */
    private var leftBorder = 0

    /**
     * 界面可滚动的右边界
     */
    private var rightBorder = 0
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val childCount = childCount
        for (i in 0 until childCount) {
            val childView = getChildAt(i)
            // 为ScrollerLayout中的每一个子控件测量大小
            measureChild(childView, widthMeasureSpec, heightMeasureSpec)
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        if (changed) {
            val childCount = childCount
            for (i in 0 until childCount) {
                val childView = getChildAt(i)
                // 为ScrollerLayout中的每一个子控件在水平方向上进行布局
                childView.layout(i * childView.measuredWidth, 0, (i + 1) * childView.measuredWidth, childView.measuredHeight)
            }
            // 初始化左右边界值
            leftBorder = getChildAt(0).left
            rightBorder = getChildAt(getChildCount() - 1).right
        }
    }

    //   override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {

        when (ev!!.action) {
            // MotionEvent.ACTION_DOWN -> {
            MotionEvent.ACTION_DOWN -> {
                mXDown = ev.rawX
                mXLastMove = mXDown
            }
            //   MotionEvent.ACTION_MOVE -> {
            MotionEvent.ACTION_MOVE -> {
                mXMove = ev.rawX
                val diff = Math.abs(mXMove - mXDown)
                mXLastMove = mXMove
                // 当手指拖动值大于TouchSlop值时，认为应该进行滚动，拦截子控件的事件
                if (diff > mTouchSlop) {
                    return true
                }
            }
        }
        //    return super.onInterceptTouchEvent(ev)
        return super.onInterceptTouchEvent(ev)
    }

    //  override fun onTouchEvent(event: MotionEvent): Boolean {


    //getScrollX() = view的左边缘在坐标系中的位置,,getCurrX() = 距离原点X方向的绝对值
    override fun onTouchEvent(event: MotionEvent?): Boolean {

        when (event!!.action) {
            //  MotionEvent.ACTION_MOVE -> {
            MotionEvent.ACTION_MOVE -> {
                mXMove = event.rawX
                val scrolledX = (mXLastMove - mXMove).toInt()
                if (scrollX + scrolledX < leftBorder) {
                    scrollTo(leftBorder, 0)
                    return true
                } else if (scrollX + width + scrolledX > rightBorder) {
                    scrollTo(rightBorder - width, 0)
                    return true
                }
                scrollBy(scrolledX, 0)
                mXLastMove = mXMove
            }
            //   MotionEvent.ACTION_UP -> {
            MotionEvent.ACTION_UP -> {
                // 当手指抬起时，根据当前的滚动值来判定应该滚动到哪个子控件的界面
                val targetIndex = (scrollX + width / 2) / width
                val dx = targetIndex * width - scrollX
                // 第二步，调用startScroll()方法来初始化滚动数据并刷新界面
                mScroller.startScroll(scrollX, 0, dx, 0)
                invalidate()
            }
        }
        //    return super.onTouchEvent(event)
        return super.onTouchEvent(event)
    }

    override fun computeScroll() {
        // 第三步，重写computeScroll()方法，并在其内部完成平滑滚动的逻辑
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.currX, mScroller.currY)
            invalidate()
        }
    }

    init {
        // 第一步，创建Scroller的实例
        mScroller = Scroller(context)
        val configuration = ViewConfiguration.get(context)
        // 获取TouchSlop值
        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(configuration)
    }
}