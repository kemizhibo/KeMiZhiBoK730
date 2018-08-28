package com.kemizhibo.kemizhibo.yhr.interactor.findpwdinteractor;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.findpwdapi.SendValidateBeforLoginApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.findPwdbean.SendValidateBeforLoginBean;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe   登录之前发送验证码
 */
public class SendValidateBeforLoginIteractor {

    private IGetDataDelegate<SendValidateBeforLoginBean> mDelegate;

    @Inject
    public SendValidateBeforLoginIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadSendValidateBeforLoginData(BaseActivity activity, IGetDataDelegate<SendValidateBeforLoginBean> mDelegate,String flag,String mobile){
        this.mDelegate = mDelegate;
        SendValidateBeforLoginApi sendValidateBeforLoginApi = new SendValidateBeforLoginApi(listener,activity,flag,mobile);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(sendValidateBeforLoginApi);
    }

    private HttpOnNextListener<SendValidateBeforLoginBean> listener = new HttpOnNextListener<SendValidateBeforLoginBean>() {
        @Override
        public void onNext(SendValidateBeforLoginBean sendValidateBeforLoginBean) {
            mDelegate.getDataSuccess(sendValidateBeforLoginBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
