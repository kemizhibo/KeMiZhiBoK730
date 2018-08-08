package com.kemizhibo.kemizhibo.yhr.interactor.homeinteractor;

import android.content.Context;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.homeapi.SowingMapApi;
import com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi.LoginApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.LoginBean;
import com.kemizhibo.kemizhibo.yhr.bean.homepagerbean.SowingMapBean;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe   轮播
 */
public class SowingMapIteractor {

    private IGetDataDelegate<SowingMapBean> mDelegate;
    private Context context;

    @Inject
    public SowingMapIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadSowingMapData(BaseActivity activity, IGetDataDelegate<SowingMapBean> mDelegate,String device){
        this.mDelegate = mDelegate;
        SowingMapApi sowingMapApi = new SowingMapApi(listener,activity,device);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(sowingMapApi);
    }

    private HttpOnNextListener<SowingMapBean> listener = new HttpOnNextListener<SowingMapBean>() {
        @Override
        public void onNext(SowingMapBean sowingMapBean) {
            mDelegate.getDataSuccess(sowingMapBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
