package com.kemizhibo.kemizhibo.yhr;

import android.app.Application;
import android.content.SharedPreferences;

import com.facebook.drawee.backends.pipeline.Fresco;

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
