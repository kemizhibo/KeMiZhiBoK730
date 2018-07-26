package com.kemizhibo.kemizhibo.yhr.view;

import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BaseView;
import com.kemizhibo.kemizhibo.yhr.bean.LoginBean;
import com.kemizhibo.kemizhibo.yhr.bean.TokenBean;


/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe   获取token
 */
public interface LoginView extends BaseView{
    //是否登录成功
    void onLoginSuccess(LoginBean loginBean);
    void onLoginError(String msg);
}
