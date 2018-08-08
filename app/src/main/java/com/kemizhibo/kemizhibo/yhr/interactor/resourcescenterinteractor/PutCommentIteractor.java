package com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor;

import android.content.Context;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi.PutCommentApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.CommentDetailBean;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe
 */
public class PutCommentIteractor {

    private IGetDataDelegate<CommentDetailBean> mDelegate;
    private Context context;

    @Inject
    public PutCommentIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadPutCommentData(BaseActivity activity, IGetDataDelegate<CommentDetailBean> mDelegate,String token, String courseId, String content, String pCommentId){
        this.mDelegate = mDelegate;
        PutCommentApi putCommentApi = new PutCommentApi(listener,activity,token,courseId,content,pCommentId);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(putCommentApi);
    }

    private HttpOnNextListener<CommentDetailBean> listener = new HttpOnNextListener<CommentDetailBean>() {
        @Override
        public void onNext(CommentDetailBean commentDetailBean) {
            mDelegate.getDataSuccess(commentDetailBean);
            LogUtils.e(commentDetailBean.toString());
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
