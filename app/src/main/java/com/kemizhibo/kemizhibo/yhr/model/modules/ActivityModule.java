package com.kemizhibo.kemizhibo.yhr.model.modules;

import android.app.Activity;
import android.content.Context;

import com.kemizhibo.kemizhibo.yhr.di.scope.ContextLife;
import com.kemizhibo.kemizhibo.yhr.di.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Author: yhr
 * Date: 2018/5/3
 * Describe:
 */

@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    public Activity provideActivity(){
        return activity;
    }

    @Provides
    @PerActivity
    @ContextLife("Activity")
    public Context provideActivityContext(){
        return activity;
    }
}
