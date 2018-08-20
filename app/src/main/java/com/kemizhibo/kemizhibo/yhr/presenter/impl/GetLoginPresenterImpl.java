package com.kemizhibo.kemizhibo.yhr.presenter.impl;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenterImpl;
import com.kemizhibo.kemizhibo.yhr.bean.LoginBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.GetUserBean;
import com.kemizhibo.kemizhibo.yhr.interactor.GetLoginIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.LoginIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.personcenterinteractor.GetUserIteractor;
import com.kemizhibo.kemizhibo.yhr.presenter.GetLoginPresenter;
import com.kemizhibo.kemizhibo.yhr.view.LoginView;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: on 2018/7/3.
 * Describe:token
 */

public class GetLoginPresenterImpl extends BasePresenterImpl<LoginView> implements GetLoginPresenter {
    //注意public全局
    @Inject
    public GetLoginIteractor getLoginIteractor;
    @Inject
    public GetUserIteractor getUserIteractor ;

    @Inject
    public GetLoginPresenterImpl() {}


    @Override
    public void getLoginData(BaseActivity activity, String account, String password) {
        getLoginIteractor.loadGetLoginData(activity, new IGetDataDelegate<LoginBean>() {
            @Override
            public void getDataSuccess(LoginBean loginBean) {
                mPresenterView.onLoginSuccess(loginBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onLoginError(errmsg);
            }
        },account,password);
    }

    @Override
    public void getUserData(BaseActivity activity, String token) {
        getUserIteractor.loadGetUserData(activity, new IGetDataDelegate<GetUserBean>() {
            @Override
            public void getDataSuccess(GetUserBean getUserBean) {
                mPresenterView.onUserSuccess(getUserBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onUserError(errmsg);
            }
        },token);
    }

}
