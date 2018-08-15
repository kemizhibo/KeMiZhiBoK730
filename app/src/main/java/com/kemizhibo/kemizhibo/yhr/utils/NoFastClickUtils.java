package com.kemizhibo.kemizhibo.yhr.utils;

/**
 * Author: yhr
 * Date: on 2018/7/9.
 * Describe:防止快速点击
 */

public class NoFastClickUtils {
    private  static long lastClickTime=0;//上次点击的时间

    private  static int spaceTime = 500;//时间间隔

    public  static boolean isFastClick() {

        long currentTime = System.currentTimeMillis();//当前系统时间

        boolean isAllowClick;//是否允许点击

        if (currentTime - lastClickTime > spaceTime) {

            isAllowClick= false;

        } else {

            isAllowClick = true;

        }

        lastClickTime = currentTime;

        return isAllowClick;

    }

}
