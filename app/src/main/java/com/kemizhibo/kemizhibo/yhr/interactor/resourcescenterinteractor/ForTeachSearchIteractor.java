package com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor;


import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi.ForTeachSearchApi;
import com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi.SearchApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.ForTeachSearchBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.SearchBean;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe   备授课搜索
 */
public class ForTeachSearchIteractor {

    private IGetDataDelegate<ForTeachSearchBean> mDelegate;

    @Inject
    public ForTeachSearchIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadForTeachSearchData(BaseActivity activity, IGetDataDelegate<ForTeachSearchBean> mDelegate,String token,String keyWord,String pageSize,String currentPage){
        this.mDelegate = mDelegate;
        ForTeachSearchApi forTeachSearchApi = new ForTeachSearchApi(listener,activity,token,keyWord,pageSize,currentPage);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(forTeachSearchApi);
    }

    private HttpOnNextListener<ForTeachSearchBean> listener = new HttpOnNextListener<ForTeachSearchBean>() {
        @Override
        public void onNext(ForTeachSearchBean forTeachSearchBean) {
            mDelegate.getDataSuccess(forTeachSearchBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
