package com.kemizhibo.kemizhibo.yhr.interactor.findpwdinteractor;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.findpwdapi.BeforLoginDataPhoneApi;
import com.kemizhibo.kemizhibo.yhr.api.findpwdapi.ResetPwdApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.findPwdbean.BeforLoginValiDatePhoneBean;
import com.kemizhibo.kemizhibo.yhr.bean.findPwdbean.ResetPwdBean;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe   重置密码
 */
public class ResetPwdIteractor {
    private IGetDataDelegate<ResetPwdBean> mDelegate;
    @Inject
    public ResetPwdIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadResetPwdData(BaseActivity activity, IGetDataDelegate<ResetPwdBean> mDelegate,String token,String password){
        this.mDelegate = mDelegate;
        ResetPwdApi resetPwdApi = new ResetPwdApi(listener,activity,token,password);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(resetPwdApi);
    }

    private HttpOnNextListener<ResetPwdBean> listener = new HttpOnNextListener<ResetPwdBean>() {
        @Override
        public void onNext(ResetPwdBean resetPwdBean) {
            mDelegate.getDataSuccess(resetPwdBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
