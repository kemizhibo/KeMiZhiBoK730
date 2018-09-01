package com.kemizhibo.kemizhibo.yhr;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;

import com.kemizhibo.kemizhibo.yhr.di.component.AppComponent;
import com.kemizhibo.kemizhibo.yhr.di.component.DaggerAppComponent;
import com.kemizhibo.kemizhibo.yhr.model.modules.AppModule;
import com.tencent.bugly.crashreport.CrashReport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Author: yhr
 * Date: 2018/4/27
 * Describe:
 */

public class MyApplication extends App {
    private static int mMainThreadId;
    private static Handler mHandler;
    private AppComponent appComponent;
    public static int YINGXIANG_TO_PICK_req = 100;
    public static int YINGXIANG_TO_PICK_res = 101;

    @Override
    public void onCreate() {
        super.onCreate();
        initApplicationComponent();
        mHandler = new Handler();
        CrashReport.initCrashReport(getApplicationContext(), "f7e4244a8d", true);
    }

    /**
     * Dagger2
     */
    private void initApplicationComponent() {
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    /**
     * 返回主线程的pid
     *
     * @return
     */
    public static int getMainThreadId() {
        return mMainThreadId;
    }

    /**
     * 返回Handler
     *
     * @return
     */
    public static Handler getHandler() {
        return mHandler;
    }

    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};


    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);
        }
    }

}
