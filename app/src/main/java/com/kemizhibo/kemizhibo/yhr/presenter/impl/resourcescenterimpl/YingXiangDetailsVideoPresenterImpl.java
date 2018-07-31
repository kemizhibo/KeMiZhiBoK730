package com.kemizhibo.kemizhibo.yhr.presenter.impl.resourcescenterimpl;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenterImpl;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.CollectionBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.CommentBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.CommentDetailBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.DeleteCommentBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.GetLikeBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.ReplyCommentBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.YingXiangDetailsVideoBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.YingXiangDetailsVideoUrlBean;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.CollectionIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.DeleteCommentIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.GetLikeIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.PutCommentIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.ReplyCommentIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.YingXiangDetailsVideoCommentIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.YingXiangDetailsVideoIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.YingXiangDetailsVideoUrlIteractor;
import com.kemizhibo.kemizhibo.yhr.presenter.resourcescenterpresenter.YingXiangDetailsVideoPresenter;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.YingXiangDetailsVideoView;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: on 2018/7/3.
 * Describe:
 */

public class YingXiangDetailsVideoPresenterImpl extends BasePresenterImpl<YingXiangDetailsVideoView> implements YingXiangDetailsVideoPresenter {
    //注意public全局
    @Inject
    public YingXiangDetailsVideoIteractor yingXiangDetailsVideoIteractor ;
    @Inject
    public YingXiangDetailsVideoUrlIteractor yingXiangDetailsVideoUrlIteractor ;
    @Inject
    public YingXiangDetailsVideoCommentIteractor yingXiangDetailsVideoCommentIteractor ;
    @Inject
    public PutCommentIteractor putCommentIteractor ;
    @Inject
    public ReplyCommentIteractor replyCommentIteractor ;
    @Inject
    public CollectionIteractor collectionIteractor ;
    @Inject
    public DeleteCommentIteractor deleteCommentIteractor ;
    @Inject
    public GetLikeIteractor getLikeIteractor ;

    @Inject
    public YingXiangDetailsVideoPresenterImpl() {}

    @Override
    public void getYingXiangDetailsVideoData(BaseActivity activity, String courseId) {
        yingXiangDetailsVideoIteractor.loadYingXiangDetailsVideoData(activity, new IGetDataDelegate<YingXiangDetailsVideoBean>() {
            @Override
            public void getDataSuccess(YingXiangDetailsVideoBean yingXiangDetailsVideoBean) {
                mPresenterView.onYingXiangDetailsVideoSuccess(yingXiangDetailsVideoBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onYingXiangDetailsVideoError(errmsg);
            }
        },courseId);
    }


    @Override
    public void getYingXiangDetailsVideoUrlData(BaseActivity activity, String courseId, String videoType, String encryption, String videoClarity) {
        yingXiangDetailsVideoUrlIteractor.loadYingXiangDetailsVideoUrlData(activity, new IGetDataDelegate<YingXiangDetailsVideoUrlBean>() {
            @Override
            public void getDataSuccess(YingXiangDetailsVideoUrlBean yingXiangDetailsVideoUrlBean) {
                mPresenterView.onYingXiangDetailsVideoUrlSuccess(yingXiangDetailsVideoUrlBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onYingXiangDetailsVideoUrlError(errmsg);
            }
        },courseId,videoType,encryption,videoClarity);
    }

    @Override
    public void getYingXiangDetailsVideoCommentData(BaseActivity activity, String otherId, String page, String size, String type) {
        yingXiangDetailsVideoCommentIteractor.loadYingXiangDetailsVideoCommentData(activity, new IGetDataDelegate<CommentBean>() {
            @Override
            public void getDataSuccess(CommentBean commentBean) {
                mPresenterView.onYingXiangDetailsVideoCommentSuccess(commentBean);
                //LogUtils.e(commentBean.toString()+"000000000000000000000");
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onYingXiangDetailsVideoCommentError(errmsg);
            }
        },otherId,page,size,type);
    }

    @Override
    public void getPutCommentData(BaseActivity activity,String token, String courseId, String content, String pCommentId) {
        putCommentIteractor.loadPutCommentData(activity, new IGetDataDelegate<CommentDetailBean>() {
            @Override
            public void getDataSuccess(CommentDetailBean commentDetailBean) {
                mPresenterView.onPutCommentSuccess(commentDetailBean);

            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onPutCommentError(errmsg);
            }
        },token,courseId,content,pCommentId);
    }

    @Override
    public void getReplyCommentData(BaseActivity activity, String token, String courseId, String content, String pCommentId) {
        replyCommentIteractor.loadReplyCommentData(activity, new IGetDataDelegate<ReplyCommentBean>() {
            @Override
            public void getDataSuccess(ReplyCommentBean replyCommentBean) {
                mPresenterView.onReplyCommentSuccess(replyCommentBean);

            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onReplyCommentError(errmsg);
            }
        },token,courseId,content,pCommentId);
    }

    @Override
    public void getCollectionData(BaseActivity activity, String courseId, String token) {
        collectionIteractor.loadCollectionData(activity, new IGetDataDelegate<CollectionBean>() {
            @Override
            public void getDataSuccess(CollectionBean collectionBean) {
                mPresenterView.onGetCollectionSuccess(collectionBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onGetCollectionError(errmsg);
            }
        },courseId,token);
    }

    @Override
    public void getDeleteCommentData(BaseActivity activity, String token, String commentId, String type) {
        deleteCommentIteractor.loadDeleteCommentData(activity, new IGetDataDelegate<DeleteCommentBean>() {
            @Override
            public void getDataSuccess(DeleteCommentBean deleteCommentBean) {
                mPresenterView.onDeleteCommentSuccess(deleteCommentBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onDeleteCommentError(errmsg);
            }
        },token,commentId,type);
    }

    @Override
    public void getLikeData(BaseActivity activity, String token, String targetId, String type) {
        getLikeIteractor.loadGetLikeData(activity, new IGetDataDelegate<GetLikeBean>() {
            @Override
            public void getDataSuccess(GetLikeBean getLikeBean) {
                mPresenterView.onGetLikeSuccess(getLikeBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onGetLikeError(errmsg);
            }
        },token,targetId,type);
    }
}
