package com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi.CollectionApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.CollectionBean;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe   收藏和取消收藏
 */
public class CollectionIteractor {

    private IGetDataDelegate<CollectionBean> mDelegate;

    @Inject
    public CollectionIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadCollectionData(BaseActivity activity, IGetDataDelegate<CollectionBean> mDelegate,String courseId,String token){
        this.mDelegate = mDelegate;
        CollectionApi collectionApi = new CollectionApi(listener,activity,courseId,token);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(collectionApi);
    }

    private HttpOnNextListener<CollectionBean> listener = new HttpOnNextListener<CollectionBean>() {
        @Override
        public void onNext(CollectionBean collectionBean) {
            mDelegate.getDataSuccess(collectionBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
