package com.kemizhibo.kemizhibo.yhr.interactor.personcenterinteractor;

import android.content.Context;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.personcenterapi.NewPhoneApi;
import com.kemizhibo.kemizhibo.yhr.api.personcenterapi.OldPhoneApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.SendYanZhengMaBean;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe   验证新手机号
 */
public class NewPhoneIteractor {

    private IGetDataDelegate<SendYanZhengMaBean> mDelegate;
    private Context context;

    @Inject
    public NewPhoneIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadNewPhoneData(BaseActivity activity, IGetDataDelegate<SendYanZhengMaBean> mDelegate,String token,String mobile,String code){
        this.mDelegate = mDelegate;
        NewPhoneApi newPhoneApi = new NewPhoneApi(listener,activity,token,mobile,code);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(newPhoneApi);
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
