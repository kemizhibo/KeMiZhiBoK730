package com.kemizhibo.kemizhibo.yhr;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import com.kemizhibo.kemizhibo.yhr.di.component.AppComponent;
import com.kemizhibo.kemizhibo.yhr.di.component.DaggerAppComponent;
import com.kemizhibo.kemizhibo.yhr.model.modules.AppModule;
import com.tencent.bugly.crashreport.CrashReport;

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
        //CrashReport.initCrashReport(getApplicationContext(), "f7e4244a8d", true);
        CrashReport.initCrashReport(getApplicationContext(), "", true);
        /*//版本鞥更新
        XUpdate.get()
                .isWifiOnly(true)     //默认设置只在wifi下检查版本更新
                .isGet(true)          //默认设置使用get请求检查版本
                .isAutoMode(false)    //默认设置非自动模式，可根据具体使用配置
                .param("VersionCode", UpdateUtils.getVersionCode(this)) //设置默认公共请求参数
                .param("AppKey", getPackageName())
//                .debug(true)
                .setOnUpdateFailureListener(new OnUpdateFailureListener() { //设置版本更新出错的监听
                    @Override
                    public void onFailure(UpdateError error) {
                        ToastUtils.showToast(error.toString());
                    }
                })
                .setIUpdateHttpService((IUpdateHttpService) new OkHttpRequest()) //这个必须设置！实现网络请求功能。
                .init(this);   //这个必须初始化*/
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
