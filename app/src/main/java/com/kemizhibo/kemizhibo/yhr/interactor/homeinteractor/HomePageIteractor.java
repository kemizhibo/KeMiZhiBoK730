package com.kemizhibo.kemizhibo.yhr.interactor.homeinteractor;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.homeapi.HomePageApi;
import com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi.CollectionApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.homepagerbean.HomePageBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.CollectionBean;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe   首页
 */
public class HomePageIteractor {

    private IGetDataDelegate<HomePageBean> mDelegate;

    @Inject
    public HomePageIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadHomePageData(BaseActivity activity, IGetDataDelegate<HomePageBean> mDelegate){
        this.mDelegate = mDelegate;
        HomePageApi homePageApi = new HomePageApi(listener,activity);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(homePageApi);
    }

    private HttpOnNextListener<HomePageBean> listener = new HttpOnNextListener<HomePageBean>() {
        @Override
        public void onNext(HomePageBean homePageBean) {
            mDelegate.getDataSuccess(homePageBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
