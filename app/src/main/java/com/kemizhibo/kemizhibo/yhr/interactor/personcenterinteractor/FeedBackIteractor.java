package com.kemizhibo.kemizhibo.yhr.interactor.personcenterinteractor;

import android.content.Context;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.personcenterapi.FeedBackApi;
import com.kemizhibo.kemizhibo.yhr.api.personcenterapi.GetUserApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.FeedBackBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.GetUserBean;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe   反馈
 */
public class FeedBackIteractor {

    private IGetDataDelegate<FeedBackBean> mDelegate;
    private Context context;

    @Inject
    public FeedBackIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadFeedBackData(BaseActivity activity, IGetDataDelegate<FeedBackBean> mDelegate,String token,String content,String type){
        this.mDelegate = mDelegate;
        FeedBackApi feedBackApi = new FeedBackApi(listener,activity,token,content,type);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(feedBackApi);
    }

    private HttpOnNextListener<FeedBackBean> listener = new HttpOnNextListener<FeedBackBean>() {
        @Override
        public void onNext(FeedBackBean feedBackBean) {
            mDelegate.getDataSuccess(feedBackBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
