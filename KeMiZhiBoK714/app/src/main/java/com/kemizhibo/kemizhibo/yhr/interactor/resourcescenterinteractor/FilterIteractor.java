package com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi.FilterApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.FilterBean;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe
 */
public class FilterIteractor {

    private IGetDataDelegate<FilterBean> mDelegate;

    @Inject
    public FilterIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadFilterData(BaseActivity activity, IGetDataDelegate<FilterBean> mDelegate){
        this.mDelegate = mDelegate;
        FilterApi filterApi = new FilterApi(listener,activity);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(filterApi);
    }

    private HttpOnNextListener<FilterBean> listener = new HttpOnNextListener<FilterBean>() {
        @Override
        public void onNext(FilterBean filterBean) {
            mDelegate.getDataSuccess(filterBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
