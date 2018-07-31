package com.kemizhibo.kemizhibo.yhr.presenter;

import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenter;
import com.kemizhibo.kemizhibo.yhr.view.LoginView;

/**
 * Author: yhr
 * Date: 2018/5/28
 * Describe  获取token
 */
public interface GetLoginPresenter extends BasePresenter<LoginView> {
    //登录的
    void getLoginData(BaseActivity activity, String account, String password);
}
