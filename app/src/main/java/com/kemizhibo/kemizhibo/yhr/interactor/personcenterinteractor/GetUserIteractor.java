package com.kemizhibo.kemizhibo.yhr.interactor.personcenterinteractor;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.homeapi.SowingMapApi;
import com.kemizhibo.kemizhibo.yhr.api.personcenterapi.GetUserApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.homepagerbean.SowingMapBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.GetUserBean;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe   获取用户信息
 */
public class GetUserIteractor {

    private IGetDataDelegate<GetUserBean> mDelegate;

    @Inject
    public GetUserIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadGetUserData(BaseActivity activity, IGetDataDelegate<GetUserBean> mDelegate,String device){
        this.mDelegate = mDelegate;
        GetUserApi getUserApi = new GetUserApi(listener,activity,device);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(getUserApi);
    }

    private HttpOnNextListener<GetUserBean> listener = new HttpOnNextListener<GetUserBean>() {
        @Override
        public void onNext(GetUserBean getUserBean) {
            mDelegate.getDataSuccess(getUserBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
