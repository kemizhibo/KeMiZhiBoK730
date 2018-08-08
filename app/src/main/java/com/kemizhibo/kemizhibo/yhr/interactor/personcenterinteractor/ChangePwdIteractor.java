package com.kemizhibo.kemizhibo.yhr.interactor.personcenterinteractor;

import android.content.Context;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.personcenterapi.ChangePwdApi;
import com.kemizhibo.kemizhibo.yhr.api.personcenterapi.NewPhoneApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.ChangePwdBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.SendYanZhengMaBean;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe   修改密码
 */
public class ChangePwdIteractor {

    private IGetDataDelegate<ChangePwdBean> mDelegate;

    @Inject
    public ChangePwdIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadNewPhoneData(BaseActivity activity, IGetDataDelegate<ChangePwdBean> mDelegate,String token,String oldPassword,String newPassword){
        this.mDelegate = mDelegate;
        ChangePwdApi changePwdApi = new ChangePwdApi(listener,activity,token,oldPassword,newPassword);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(changePwdApi);
    }

    private HttpOnNextListener<ChangePwdBean> listener = new HttpOnNextListener<ChangePwdBean>() {
        @Override
        public void onNext(ChangePwdBean changePwdBean) {
            mDelegate.getDataSuccess(changePwdBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
