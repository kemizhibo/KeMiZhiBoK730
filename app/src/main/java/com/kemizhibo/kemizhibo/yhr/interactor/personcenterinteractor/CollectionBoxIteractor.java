package com.kemizhibo.kemizhibo.yhr.interactor.personcenterinteractor;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.personcenterapi.CollectionBoxApi;
import com.kemizhibo.kemizhibo.yhr.api.personcenterapi.GetUserApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.CollectionBoxBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.GetUserBean;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe   获取收藏夹
 */
public class CollectionBoxIteractor {

    private IGetDataDelegate<CollectionBoxBean> mDelegate;

    @Inject
    public CollectionBoxIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadCollectionBoxData(BaseActivity activity, IGetDataDelegate<CollectionBoxBean> mDelegate,String token,String page,String size){
        this.mDelegate = mDelegate;
        CollectionBoxApi collectionBoxApi = new CollectionBoxApi(listener,activity,token,page,size);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(collectionBoxApi);
    }

    private HttpOnNextListener<CollectionBoxBean> listener = new HttpOnNextListener<CollectionBoxBean>() {
        @Override
        public void onNext(CollectionBoxBean collectionBoxBean) {
            mDelegate.getDataSuccess(collectionBoxBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
