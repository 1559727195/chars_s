package com.massky.chars_s.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import com.massky.chars_s.R

class TitleView(context: Context?, attrs: AttributeSet?) : FrameLayout(context!!, attrs) {
    private val leftButton: Button? = null
    private val titleText: TextView? = null
    fun setTitleText(text: String?) {
        titleText!!.text = text
    }

    fun setLeftButtonText(text: String?) {
        leftButton!!.text = text
    }

    fun setLeftButtonListener(l: OnClickListener?) {
        leftButton!!.setOnClickListener(l)
    }

    init {
        //LayoutInflater.from(context).inflate(R.layout.activity_grade, this)

        LayoutInflater.from(context).inflate(R.layout.activity_grade,this)

        //        titleText = (TextView) findViewById(R.id.title_text);
//        leftButton = (Button) findViewById(R.id.button_left);
//        leftButton.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ((Activity) getContext()).finish();
//            }
//        });
    }
}