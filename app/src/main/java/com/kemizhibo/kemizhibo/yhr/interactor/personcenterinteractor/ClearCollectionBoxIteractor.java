package com.kemizhibo.kemizhibo.yhr.interactor.personcenterinteractor;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.personcenterapi.ClearCollectionBoxApi;
import com.kemizhibo.kemizhibo.yhr.api.personcenterapi.ClearLiuLanApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.ClearCollectionBoxBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.ClearLiuLanBean;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe   清空浏览记录
 */
public class ClearCollectionBoxIteractor {

    private IGetDataDelegate<ClearCollectionBoxBean> mDelegate;

    @Inject
    public ClearCollectionBoxIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadClearCollectionBoxData(BaseActivity activity, IGetDataDelegate<ClearCollectionBoxBean> mDelegate,String token){
        this.mDelegate = mDelegate;
        ClearCollectionBoxApi clearCollectionBoxApi = new ClearCollectionBoxApi(listener,activity,token);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(clearCollectionBoxApi);
    }

    private HttpOnNextListener<ClearCollectionBoxBean> listener = new HttpOnNextListener<ClearCollectionBoxBean>() {
        @Override
        public void onNext(ClearCollectionBoxBean clearCollectionBoxBean) {
            mDelegate.getDataSuccess(clearCollectionBoxBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
