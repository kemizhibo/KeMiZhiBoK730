package com.kemizhibo.kemizhibo.yhr.utils;

import android.os.Build;
import android.widget.LinearLayout;

import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;


/**
 * Created by dell on 2017/9/18.
 * 设置沉浸式状态栏
 */

public class SetStatus {
    public static void SetStatus(BaseActivity activity, final LinearLayout statusBar){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            final int statusHeight = activity.getStatusBarHeight();
            statusBar.post(new Runnable() {
                @Override
                public void run() {
                    int titleHeight = statusBar.getHeight();
                    android.widget.LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams) statusBar.getLayoutParams();
                    params.height = statusHeight + titleHeight;
                    statusBar.setLayoutParams(params);
                }
            });
        }
    }
}
