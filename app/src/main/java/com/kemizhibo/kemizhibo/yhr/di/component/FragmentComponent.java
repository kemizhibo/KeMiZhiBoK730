package com.kemizhibo.kemizhibo.yhr.di.component;

import android.app.Activity;
import android.content.Context;

import com.kemizhibo.kemizhibo.yhr.fragment.LiveRoomFragment;
import com.kemizhibo.kemizhibo.yhr.fragment.PeiXunFragment;
import com.kemizhibo.kemizhibo.yhr.fragment.PersonCenterFragment;
import com.kemizhibo.kemizhibo.yhr.fragment.YingXiangFragment;
import com.kemizhibo.kemizhibo.yhr.fragment.home.MyClassFragment;
import com.kemizhibo.kemizhibo.yhr.fragment.home.MaterialRecommendedFragment;
import com.kemizhibo.kemizhibo.yhr.fragment.home.TrainingCourseRecommendationFragment;
import com.kemizhibo.kemizhibo.yhr.fragment.resourcescenterfragment.TeacherTrainingLookFragment;
import com.kemizhibo.kemizhibo.yhr.model.modules.FragmentModule;
import com.kemizhibo.kemizhibo.yhr.di.scope.ContextLife;
import com.kemizhibo.kemizhibo.yhr.di.scope.PerFragment;
import com.kemizhibo.kemizhibo.yhr.fragment.HomePageFragment;
import com.kemizhibo.kemizhibo.yhr.fragment.ResourceLibraryFragment;
import dagger.Component;

/**
 * Author: yhr
 * Date: 2018/5/3
 * Describe:
 */

@PerFragment
@Component(modules = FragmentModule.class , dependencies = AppComponent.class)
public interface FragmentComponent {

    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();

    void inject(HomePageFragment fragment);
    void inject(PersonCenterFragment fragment);
    void inject(ResourceLibraryFragment fragment);
    void inject(TrainingCourseRecommendationFragment fragment);
    void inject(YingXiangFragment fragment);
    void inject(PeiXunFragment fragment);
    void inject(TeacherTrainingLookFragment fragment);
    void inject(LiveRoomFragment fragment);
    void inject(MyClassFragment fragment);
    void inject(MaterialRecommendedFragment fragment);
}

