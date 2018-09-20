package com.kemizhibo.kemizhibo.yhr.view;

import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BaseView;
import com.kemizhibo.kemizhibo.yhr.bean.LoginBean;
import com.kemizhibo.kemizhibo.yhr.bean.TokenBean;
import com.kemizhibo.kemizhibo.yhr.bean.homepagerbean.VersionInformationBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.GetUserBean;


/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe   获取token
 */
public interface LoginView extends BaseView{
    //是否登录成功
    void onLoginSuccess(LoginBean loginBean);
    void onLoginError(String msg);

    //获取用户信息
    void onUserSuccess(GetUserBean getUserBean);
    void onUserError(String msg);

    //获取版本信息
    void onVersionInformationSuccess( VersionInformationBean versionInformationBean);
    void onVersionInformationError(String msg);

    /*//获取下载路劲
    void onApkSuccess( ApkBean apkBean);
    void onApkError(String msg);*/
}
