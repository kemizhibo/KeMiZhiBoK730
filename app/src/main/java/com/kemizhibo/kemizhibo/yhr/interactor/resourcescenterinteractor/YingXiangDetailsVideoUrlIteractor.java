package com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor;

import android.content.Context;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi.YingXiangDetailsVideoApi;
import com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi.YingXiangDetailsVideoUrlApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.YingXiangDetailsVideoBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.YingXiangDetailsVideoUrlBean;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe
 */
public class YingXiangDetailsVideoUrlIteractor {

    private IGetDataDelegate<YingXiangDetailsVideoUrlBean> mDelegate;
    private Context context;

    @Inject
    public YingXiangDetailsVideoUrlIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadYingXiangDetailsVideoUrlData(BaseActivity activity, IGetDataDelegate<YingXiangDetailsVideoUrlBean> mDelegate,String token,String courseId,String videoType,String encryption,String videoClarity){
        this.mDelegate = mDelegate;
        YingXiangDetailsVideoUrlApi yingXiangDetailsVideoUrlApi = new YingXiangDetailsVideoUrlApi(listener,activity,token,courseId,videoType,encryption,videoClarity);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(yingXiangDetailsVideoUrlApi);
    }

    private HttpOnNextListener<YingXiangDetailsVideoUrlBean> listener = new HttpOnNextListener<YingXiangDetailsVideoUrlBean>() {
        @Override
        public void onNext(YingXiangDetailsVideoUrlBean yingXiangDetailsVideoUrlBean) {
            mDelegate.getDataSuccess(yingXiangDetailsVideoUrlBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
