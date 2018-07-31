package com.kemizhibo.kemizhibo.yhr.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;

/**
 * Author: yhr
 * Date: 2018/5/16
 * Describe
 */
public class OrientationUtils {

    public static int getCurrentOriention(Context context){
        int mCurrentOrientation = context.getResources().getConfiguration().orientation;
        int orientation = 1;
        if ( mCurrentOrientation == Configuration.ORIENTATION_PORTRAIT ) {

            // If current screen is portrait

            Log.i("info", "portrait"); // 竖屏
            orientation = 1;

        } else if ( mCurrentOrientation == Configuration.ORIENTATION_LANDSCAPE ) {

            //If current screen is landscape

            Log.i("info", "landscape"); // 横屏
            orientation = 2;
        }
        return orientation;
    }

}
