package com.kemizhibo.kemizhibo.yhr;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.tencent.bugly.crashreport.CrashReport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Author: yhr
 * Date: 2018/4/27
 * Describe: token 全局
 */

public class App extends Application{
    private static App context ;
    private String token;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this ;
        //配置
        Fresco.initialize(this);
        SharedPreferences sp = getSharedPreferences("loginToken", 0);
        token = sp.getString("token","");
    }
    public static App getContext(){
        return context;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
