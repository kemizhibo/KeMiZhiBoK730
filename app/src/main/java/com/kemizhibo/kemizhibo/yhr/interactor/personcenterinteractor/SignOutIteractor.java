package com.kemizhibo.kemizhibo.yhr.interactor.personcenterinteractor;

import android.content.Context;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.personcenterapi.SendYanZhengMaApi;
import com.kemizhibo.kemizhibo.yhr.api.personcenterapi.SignOutApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.SendYanZhengMaBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.SignOutBean;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe   退出登录
 */
public class SignOutIteractor {

    private IGetDataDelegate<SignOutBean> mDelegate;
    private Context context;

    @Inject
    public SignOutIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadSignOutData(BaseActivity activity, IGetDataDelegate<SignOutBean> mDelegate,String token){
        this.mDelegate = mDelegate;
        SignOutApi signOutApi = new SignOutApi(listener,activity,token);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(signOutApi);
    }

    private HttpOnNextListener<SignOutBean> listener = new HttpOnNextListener<SignOutBean>() {
        @Override
        public void onNext(SignOutBean signOutBean) {
            mDelegate.getDataSuccess(signOutBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
