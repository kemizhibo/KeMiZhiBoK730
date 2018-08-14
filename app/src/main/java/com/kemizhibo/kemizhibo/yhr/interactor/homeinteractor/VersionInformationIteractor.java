package com.kemizhibo.kemizhibo.yhr.interactor.homeinteractor;

import android.content.Context;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.homeapi.HomePageApi;
import com.kemizhibo.kemizhibo.yhr.api.homeapi.VersionInformationApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.homepagerbean.HomePageBean;
import com.kemizhibo.kemizhibo.yhr.bean.homepagerbean.VersionInformationBean;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe   版本信息
 */
public class VersionInformationIteractor {

    private IGetDataDelegate<VersionInformationBean> mDelegate;

    @Inject
    public VersionInformationIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadVersionInformationData(BaseActivity activity, IGetDataDelegate<VersionInformationBean> mDelegate){
        this.mDelegate = mDelegate;
        VersionInformationApi versionInformationApi = new VersionInformationApi(listener,activity);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(versionInformationApi);
    }

    private HttpOnNextListener<VersionInformationBean> listener = new HttpOnNextListener<VersionInformationBean>() {
        @Override
        public void onNext(VersionInformationBean versionInformationBean) {
            mDelegate.getDataSuccess(versionInformationBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
