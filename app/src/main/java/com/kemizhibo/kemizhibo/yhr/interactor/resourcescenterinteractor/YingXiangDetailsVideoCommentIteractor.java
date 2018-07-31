package com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi.YingXiangDetailsVideoCommentApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.CommentBean;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe
 */
public class YingXiangDetailsVideoCommentIteractor {

    private IGetDataDelegate<CommentBean> mDelegate;

    @Inject
    public YingXiangDetailsVideoCommentIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadYingXiangDetailsVideoCommentData(BaseActivity activity, IGetDataDelegate<CommentBean> mDelegate, String otherId, String page, String size, String type){
        this.mDelegate = mDelegate;
        YingXiangDetailsVideoCommentApi yingXiangDetailsVideoCommentApi = new YingXiangDetailsVideoCommentApi(listener,activity,otherId,page,size,type);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(yingXiangDetailsVideoCommentApi);
    }

    private HttpOnNextListener<CommentBean> listener = new HttpOnNextListener<CommentBean>() {
        @Override
        public void onNext(CommentBean commentBean) {
            mDelegate.getDataSuccess(commentBean);
            LogUtils.e(commentBean.toString());
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
