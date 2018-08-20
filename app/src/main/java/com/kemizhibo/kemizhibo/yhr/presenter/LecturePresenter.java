package com.kemizhibo.kemizhibo.yhr.presenter;

import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenter;
import com.kemizhibo.kemizhibo.yhr.view.LectureView;
import com.kemizhibo.kemizhibo.yhr.view.LoginView;

/**
 * Author: yhr
 * Date: 2018/5/28
 * Describe  完成一键授课
 */
public interface LecturePresenter extends BasePresenter<LectureView> {
    //登录的
    void getLectureData(BaseActivity activity, String token,String moduleId, String kemiVideoPlan);
}
