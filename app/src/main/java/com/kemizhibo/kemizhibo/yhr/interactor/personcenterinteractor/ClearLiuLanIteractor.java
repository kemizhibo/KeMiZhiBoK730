package com.kemizhibo.kemizhibo.yhr.interactor.personcenterinteractor;

import android.content.Context;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.personcenterapi.ClearLiuLanApi;
import com.kemizhibo.kemizhibo.yhr.api.personcenterapi.SignOutApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.ClearLiuLanBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.SignOutBean;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe   清空浏览记录
 */
public class ClearLiuLanIteractor {

    private IGetDataDelegate<ClearLiuLanBean> mDelegate;

    @Inject
    public ClearLiuLanIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadClearLiuLanData(BaseActivity activity, IGetDataDelegate<ClearLiuLanBean> mDelegate,String token){
        this.mDelegate = mDelegate;
        ClearLiuLanApi clearLiuLanApi = new ClearLiuLanApi(listener,activity,token);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(clearLiuLanApi);
    }

    private HttpOnNextListener<ClearLiuLanBean> listener = new HttpOnNextListener<ClearLiuLanBean>() {
        @Override
        public void onNext(ClearLiuLanBean clearLiuLanBean) {
            mDelegate.getDataSuccess(clearLiuLanBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}