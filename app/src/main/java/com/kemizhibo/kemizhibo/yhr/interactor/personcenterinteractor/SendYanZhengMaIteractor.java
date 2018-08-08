package com.kemizhibo.kemizhibo.yhr.interactor.personcenterinteractor;

import android.content.Context;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.personcenterapi.FeedBackApi;
import com.kemizhibo.kemizhibo.yhr.api.personcenterapi.SendYanZhengMaApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.FeedBackBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.SendYanZhengMaBean;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe   发送验证码
 */
public class SendYanZhengMaIteractor {

    private IGetDataDelegate<SendYanZhengMaBean> mDelegate;
    private Context context;

    @Inject
    public SendYanZhengMaIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadSendYanZhengMaData(BaseActivity activity, IGetDataDelegate<SendYanZhengMaBean> mDelegate,String flag,String token,String mobile){
        this.mDelegate = mDelegate;
        SendYanZhengMaApi sendYanZhengMaApi = new SendYanZhengMaApi(listener,activity,flag,token,mobile);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(sendYanZhengMaApi);
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
