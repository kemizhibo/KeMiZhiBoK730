package com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor;

import android.content.Context;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi.CollectionApi;
import com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi.GetLikeApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.CollectionBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.GetLikeBean;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe   收藏和取消收藏
 */
public class GetLikeIteractor {

    private IGetDataDelegate<GetLikeBean> mDelegate;
    private Context context;

    @Inject
    public GetLikeIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadGetLikeData(BaseActivity activity, IGetDataDelegate<GetLikeBean> mDelegate,String token,String targetId,String type){
        this.mDelegate = mDelegate;
        GetLikeApi getLikeApi = new GetLikeApi(listener,activity,token,targetId,type);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(getLikeApi);
    }

    private HttpOnNextListener<GetLikeBean> listener = new HttpOnNextListener<GetLikeBean>() {
        @Override
        public void onNext(GetLikeBean getLikeBean) {
            mDelegate.getDataSuccess(getLikeBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
