package com.kemizhibo.kemizhibo.yhr.model.modules;

import android.content.Context;

import com.kemizhibo.kemizhibo.yhr.MyApplication;
import com.kemizhibo.kemizhibo.yhr.di.scope.ContextLife;
import com.kemizhibo.kemizhibo.yhr.di.scope.PerApp;

import dagger.Module;
import dagger.Provides;

/**
 * Author: yhr
 * Date: 2018/5/3
 * Describe:
 */

@Module
public class AppModule {

    private MyApplication myApplication;

    public AppModule(MyApplication myApplication) {
        this.myApplication = myApplication;
    }

    @Provides
    @PerApp
    @ContextLife("Application")
    public Context provideAppContext(){
        return myApplication.getApplicationContext();
    }
}
