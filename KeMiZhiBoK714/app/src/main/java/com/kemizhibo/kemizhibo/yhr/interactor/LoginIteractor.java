package com.kemizhibo.kemizhibo.yhr.interactor;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi.LoginApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.LoginBean;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe   获取token
 */
public class LoginIteractor {

    private IGetDataDelegate<LoginBean> mDelegate;

    @Inject
    public LoginIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadLoginData(BaseActivity activity, IGetDataDelegate<LoginBean> mDelegate,String account,String password){
        this.mDelegate = mDelegate;
        LoginApi loginApi = new LoginApi(listener,activity,account,password);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(loginApi);
    }

    private HttpOnNextListener<LoginBean> listener = new HttpOnNextListener<LoginBean>() {
        @Override
        public void onNext(LoginBean loginBean) {
            mDelegate.getDataSuccess(loginBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}