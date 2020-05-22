package com.massky.chars_s.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ListView
import com.massky.chars_s.R
import kotlinx.android.synthetic.main.activity_main.view.*

class NewListView : ListView {
    //setViewTypeCount
    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init_flater(context)
    }

    private fun init_flater(context:Context?) {
        var layoutInflater = LayoutInflater.from(context)
        layoutInflater.inflate(R.layout.activity_main,null)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {} //fillActiveViews()
    //getActiveView()
    //addScrapView(),
}