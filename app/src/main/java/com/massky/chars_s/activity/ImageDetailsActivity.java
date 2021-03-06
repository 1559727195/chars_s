package com.massky.chars_s.activity;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;

import com.massky.chars_s.MainActivity;
import com.massky.chars_s.MyAIDLService;
import com.massky.chars_s.R;
import com.massky.chars_s.service.MyService;
import com.massky.chars_s.service.MyService1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ImageDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        String path =null;
        BitmapFactory.decodeFile(path);

        Intent intent = new Intent(this, MyService1.class);
        startService(intent);






    }






    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(this, MyService1.class);
        stopService(intent);
    }





    /**
     * 判断绑定滑动事件的View是不是一个基础layout，不支持自定义layout，只支持四种基本layout,
     * AbsoluteLayout已被弃用。
     *
     * @return 如果绑定滑动事件的View是LinearLayout,RelativeLayout,FrameLayout,
     *         TableLayout之一就返回true，否则返回false。
     */

    private View mBindView;

    private boolean isBindBasicLayout() {
        if (mBindView == null) {
            return false;
        }
        String viewName = mBindView.getClass().getName();
        return viewName.equals(LinearLayout.class.getName())
                || viewName.equals(RelativeLayout.class.getName())
                || viewName.equals(FrameLayout.class.getName())
                || viewName.equals(TableLayout.class.getName());
    }


    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        switch (level) {
            case TRIM_MEMORY_UI_HIDDEN:
                // 进行资源释放操作
                break;
        }

    }
}
