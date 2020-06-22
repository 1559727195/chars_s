package com.massky.chars_s.activity;

import android.app.Activity;
import android.os.Bundle;

import com.massky.chars_s.R;
import com.massky.chars_s.app.MyApplication;

import androidx.annotation.Nullable;

public class LeakActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak);

        LeakThread leakThread = new LeakThread();
        leakThread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        MyApplication.Companion.getRefWatcher().watch(this);
    }


    class LeakThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(6 * 60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}