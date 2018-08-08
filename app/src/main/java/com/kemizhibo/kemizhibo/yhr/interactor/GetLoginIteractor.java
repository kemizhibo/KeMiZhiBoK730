package com.kemizhibo.kemizhibo.yhr.interactor;

import android.content.Context;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.tokenapi.GetLoginApi;
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
public class GetLoginIteractor {

    private IGetDataDelegate<LoginBean> mDelegate;
    private Context context;

    @Inject
    public GetLoginIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadGetLoginData(BaseActivity activity, IGetDataDelegate<LoginBean> mDelegate,String account,String password){
        this.mDelegate = mDelegate;
        GetLoginApi getLoginApi = new GetLoginApi(listener,activity,account,password);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(getLoginApi);
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
