package com.kemizhibo.kemizhibo.yhr.di.component;

import android.content.Context;

import com.kemizhibo.kemizhibo.yhr.model.modules.AppModule;
import com.kemizhibo.kemizhibo.yhr.di.scope.ContextLife;
import com.kemizhibo.kemizhibo.yhr.di.scope.PerApp;

import dagger.Component;

/**
 * Author: yhr
 * Date: 2018/5/3
 * Describe: 提供全局单例的Context对象
 */
@PerApp
@Component(modules = AppModule.class)
public interface AppComponent {

    @ContextLife("Application")
    Context getApplicationContext();
}
