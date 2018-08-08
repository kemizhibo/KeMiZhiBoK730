package com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor;

import android.content.Context;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi.SearchApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.SearchBean;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;
import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe   搜索
 */
public class SearchIteractor {

    private IGetDataDelegate<SearchBean> mDelegate;
    private Context context;

    @Inject
    public SearchIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadSearchData(BaseActivity activity, IGetDataDelegate<SearchBean> mDelegate,String sellType,String currentPage,String pageSize,String courseName){
        this.mDelegate = mDelegate;
        SearchApi searchApi = new SearchApi(listener,activity,sellType,currentPage,pageSize,courseName);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(searchApi);
    }

    private HttpOnNextListener<SearchBean> listener = new HttpOnNextListener<SearchBean>() {
        @Override
        public void onNext(SearchBean searchBean) {
            mDelegate.getDataSuccess(searchBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
