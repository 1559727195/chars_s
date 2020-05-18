package com.massky.chars_s.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.massky.chars_s.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PhotoWallAdapter extends ArrayAdapter<String> implements AbsListView.OnScrollListener {
    public PhotoWallAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // 仅当GridView静止时才去下载图片，GridView滑动时取消所有正在下载的任务
        if (scrollState == SCROLL_STATE_IDLE) {
            //loadBitmaps(mFirstVisibleItem, mVisibleItemCount);
        } else {
            //cancelAllTasks();
        }

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = null;

        ImageView photo = view.findViewById(R.id.TextView1);
        // 给ImageView设置一个Tag，保证异步加载图片时不会乱序
        Object url = null;
        photo.setTag(url);


        GridView gridView = null;
        ImageView imageView = gridView.findViewWithTag(url);


        return super.getView(position, convertView, parent);
    }

    /**
     * 第一张可见图片的下标
     */
    private int mFirstVisibleItem;

    /**
     * 一屏有多少张图片可见
     */
    private int mVisibleItemCount;


    /**
     * 记录是否刚打开程序，用于解决进入程序不滚动屏幕，不会下载图片的问题。
     */
    private boolean isFirstEnter = true;

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        mFirstVisibleItem = firstVisibleItem;
        mVisibleItemCount = visibleItemCount;
        // 下载的任务应该由onScrollStateChanged里调用，但首次进入程序时onScrollStateChanged并不会调用，
        // 因此在这里为首次进入程序开启下载任务。
        if (isFirstEnter && visibleItemCount > 0) {
            // loadBitmaps(firstVisibleItem, visibleItemCount);
            isFirstEnter = false;
        }


    }
}
