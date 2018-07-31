package com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi.YingXiangDetailsVideoApi;
import com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi.YingXiangFragmentApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.YingXiangDetailsVideoBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.YingXiangFragmentBean;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe
 */
public class YingXiangDetailsVideoIteractor {

    private IGetDataDelegate<YingXiangDetailsVideoBean> mDelegate;

    @Inject
    public YingXiangDetailsVideoIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadYingXiangDetailsVideoData(BaseActivity activity, IGetDataDelegate<YingXiangDetailsVideoBean> mDelegate,String token,String courseId){
        this.mDelegate = mDelegate;
        YingXiangDetailsVideoApi yingXiangDetailsVideoApi = new YingXiangDetailsVideoApi(listener,activity,token,courseId);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(yingXiangDetailsVideoApi);
    }

    private HttpOnNextListener<YingXiangDetailsVideoBean> listener = new HttpOnNextListener<YingXiangDetailsVideoBean>() {
        @Override
        public void onNext(YingXiangDetailsVideoBean yingXiangDetailsVideoBean) {
            mDelegate.getDataSuccess(yingXiangDetailsVideoBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
