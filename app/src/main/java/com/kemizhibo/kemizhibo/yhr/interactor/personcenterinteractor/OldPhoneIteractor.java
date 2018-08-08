package com.kemizhibo.kemizhibo.yhr.interactor.personcenterinteractor;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.personcenterapi.OldPhoneApi;
import com.kemizhibo.kemizhibo.yhr.api.personcenterapi.SendYanZhengMaApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.SendYanZhengMaBean;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe   验证旧手机号
 */
public class OldPhoneIteractor {

    private IGetDataDelegate<SendYanZhengMaBean> mDelegate;

    @Inject
    public OldPhoneIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadOldPhoneData(BaseActivity activity, IGetDataDelegate<SendYanZhengMaBean> mDelegate,String token,String mobile,String code){
        this.mDelegate = mDelegate;
        OldPhoneApi oldPhoneApi = new OldPhoneApi(listener,activity,token,mobile,code);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(oldPhoneApi);
    }

    private HttpOnNextListener<SendYanZhengMaBean> listener = new HttpOnNextListener<SendYanZhengMaBean>() {
        @Override
        public void onNext(SendYanZhengMaBean sendYanZhengMaBean) {
            mDelegate.getDataSuccess(sendYanZhengMaBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
