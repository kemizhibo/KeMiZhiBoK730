package com.kemizhibo.kemizhibo.yhr;

import android.os.Handler;

import com.kemizhibo.kemizhibo.yhr.di.component.AppComponent;
import com.kemizhibo.kemizhibo.yhr.di.component.DaggerAppComponent;
import com.kemizhibo.kemizhibo.yhr.model.modules.AppModule;

/**
 * Author: yhr
 * Date: 2018/4/27
 * Describe:
 */

public class MyApplication extends App{
    private static int mMainThreadId;
    private static Handler mHandler;
    private AppComponent appComponent;

    public static int YINGXIANG_TO_PICK_req=100;
    public static int YINGXIANG_TO_PICK_res=101;

    @Override
    public void onCreate() {
        super.onCreate();
        initApplicationComponent();
        mHandler=new Handler();

    }

    /**
     * Dagger2
     */
    private void initApplicationComponent(){
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }
    /**
     * 返回主线程的pid
     * @return
     */
    public static int getMainThreadId() {
        return mMainThreadId;
    }
    /**
     * 返回Handler
     * @return
     */
    public static Handler getHandler() {
        return mHandler;
    }

}
