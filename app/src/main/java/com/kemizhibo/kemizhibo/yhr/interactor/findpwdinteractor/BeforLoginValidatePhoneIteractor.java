package com.kemizhibo.kemizhibo.yhr.interactor.findpwdinteractor;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.findpwdapi.BeforLoginDataPhoneApi;
import com.kemizhibo.kemizhibo.yhr.api.personcenterapi.OldPhoneApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.findPwdbean.BeforLoginValiDatePhoneBean;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;
import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe   验证手机号
 */
public class BeforLoginValidatePhoneIteractor {
    private IGetDataDelegate<BeforLoginValiDatePhoneBean> mDelegate;
    @Inject
    public BeforLoginValidatePhoneIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadBeforLoginValidatePhoneData(BaseActivity activity, IGetDataDelegate<BeforLoginValiDatePhoneBean> mDelegate,String mobile,String code){
        this.mDelegate = mDelegate;
        BeforLoginDataPhoneApi beforLoginDataPhoneApi = new BeforLoginDataPhoneApi(listener,activity,mobile,code);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(beforLoginDataPhoneApi);
    }

    private HttpOnNextListener<BeforLoginValiDatePhoneBean> listener = new HttpOnNextListener<BeforLoginValiDatePhoneBean>() {
        @Override
        public void onNext(BeforLoginValiDatePhoneBean beforLoginValiDatePhoneBean) {
            mDelegate.getDataSuccess(beforLoginValiDatePhoneBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
