package com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi.DeleteCommentApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.DeleteCommentBean;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe   删除评论
 */
public class DeleteCommentIteractor {

    private IGetDataDelegate<DeleteCommentBean> mDelegate;

    @Inject
    public DeleteCommentIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadDeleteCommentData(BaseActivity activity, IGetDataDelegate<DeleteCommentBean> mDelegate,String token,String commentId,String type){
        this.mDelegate = mDelegate;
        DeleteCommentApi deleteCommentApi = new DeleteCommentApi(listener,activity,token,commentId,type);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(deleteCommentApi);
    }

    private HttpOnNextListener<DeleteCommentBean> listener = new HttpOnNextListener<DeleteCommentBean>() {
        @Override
        public void onNext(DeleteCommentBean deleteCommentBean) {
            mDelegate.getDataSuccess(deleteCommentBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
