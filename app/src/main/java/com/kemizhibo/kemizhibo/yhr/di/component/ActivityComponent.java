package com.kemizhibo.kemizhibo.yhr.di.component;

import android.app.Activity;
import android.content.Context;

import com.kemizhibo.kemizhibo.yhr.activity.SplashActivity;
import com.kemizhibo.kemizhibo.yhr.activity.personcenters.ChangePhoneActivity;
import com.kemizhibo.kemizhibo.yhr.activity.personcenters.ChangePwdActivity;
import com.kemizhibo.kemizhibo.yhr.activity.personcenters.LectureActivity;
import com.kemizhibo.kemizhibo.yhr.activity.personcenters.PersonCenterBianJiActivity;
import com.kemizhibo.kemizhibo.yhr.activity.personcenters.PersonCenterFanKuiActivity;
import com.kemizhibo.kemizhibo.yhr.activity.personcenters.PersonCenterLiuLanActivity;
import com.kemizhibo.kemizhibo.yhr.activity.personcenters.PersonCenterSheZhiActivity;
import com.kemizhibo.kemizhibo.yhr.activity.personcenters.PersonCenterShouCangActivity;
import com.kemizhibo.kemizhibo.yhr.activity.personcenters.SetNewPhoneActivity;
import com.kemizhibo.kemizhibo.yhr.activity.personcenters.TakePhotoActivity;
import com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity.PictrueDetailsActivity;
import com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity.SearchActivity;
import com.kemizhibo.kemizhibo.yhr.activity.logins.LoginActivity;
import com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity.LiveRoomDetailsActivity;
import com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity.TeacherTrainingDetailsActivity;
import com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity.YingXinagVideoDetailsActivity;
import com.kemizhibo.kemizhibo.yhr.model.modules.ActivityModule;
import com.kemizhibo.kemizhibo.yhr.di.scope.ContextLife;
import com.kemizhibo.kemizhibo.yhr.di.scope.PerActivity;
import com.kemizhibo.kemizhibo.yhr.activity.MainActivity;
import dagger.Component;

/**
 * Author: yhr
 * Date: 2018/5/3
 * Describe:
 */

@PerActivity
@Component(modules = ActivityModule.class , dependencies = AppComponent.class)
public interface ActivityComponent {

    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();

    void inject(MainActivity activity);
    void inject(YingXinagVideoDetailsActivity activity);
    void inject(TeacherTrainingDetailsActivity activity);
    void inject(LiveRoomDetailsActivity activity);
    void inject(LoginActivity activity);
    void inject(SearchActivity activity);
    void inject(SplashActivity activity);
    void inject(PictrueDetailsActivity activity);
    void inject(PersonCenterBianJiActivity activity);
    void inject(TakePhotoActivity activity);
    void inject(PersonCenterShouCangActivity activity);
    void inject(PersonCenterFanKuiActivity activity);
    void inject(ChangePhoneActivity activity);
    void inject(SetNewPhoneActivity activity);
    void inject(ChangePwdActivity activity);
    void inject(PersonCenterSheZhiActivity activity);
    void inject(PersonCenterLiuLanActivity activity);
    void inject(LectureActivity activity);
}
