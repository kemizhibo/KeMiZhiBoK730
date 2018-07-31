package com.kemizhibo.kemizhibo.yhr.model.modules;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.kemizhibo.kemizhibo.yhr.di.scope.ContextLife;
import com.kemizhibo.kemizhibo.yhr.di.scope.PerFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Author: yhr
 * Date: 2018/5/3
 * Describe:
 */

@Module
public class FragmentModule {
    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @PerFragment
    public Fragment provideFragment(){
        return fragment;
    }

    @Provides
    @PerFragment
    public Activity provideFragmentActivity(){
        return fragment.getActivity();
    }

    @Provides
    @PerFragment
    @ContextLife("Activity")
    public Context provideFragmentContext(){
        return fragment.getContext();
    }
}
