package com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor;

import android.content.Context;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi.PutCommentApi;
import com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi.ReplyCommentApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.CommentDetailBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.ReplyCommentBean;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe
 */
public class ReplyCommentIteractor {

    private IGetDataDelegate<ReplyCommentBean> mDelegate;

    @Inject
    public ReplyCommentIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadReplyCommentData(BaseActivity activity, IGetDataDelegate<ReplyCommentBean> mDelegate,String token, String courseId, String content, String pCommentId){
        this.mDelegate = mDelegate;
        ReplyCommentApi replyCommentApi = new ReplyCommentApi(listener,activity,token,courseId,content,pCommentId);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(replyCommentApi);
    }

    private HttpOnNextListener<ReplyCommentBean> listener = new HttpOnNextListener<ReplyCommentBean>() {
        @Override
        public void onNext(ReplyCommentBean replyCommentBean) {
            mDelegate.getDataSuccess(replyCommentBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
