package com.kemizhibo.kemizhibo.yhr.presenter.impl.resourcescenterimpl;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenterImpl;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.CollectionBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.CommentBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.CommentDetailBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.DeleteCommentBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.GetLikeBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.LiveRoomDetailsVideoUrlBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.OneLookBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.TeacherTrainingDetailsVideoBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.TeacherTrainingDetailsVideoUrlBean;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.CollectionIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.DeleteCommentIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.GetLikeIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.LastLookIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.LiveRoomDetailsVideoUrlIteractor2;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.LiveRoomDetailsVideoUrlIteractor3;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.LiveRoomDetailsVideoUrlIteractor4;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.MoreLookIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.OneLookIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.PutCommentIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.ReplyCommentIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.TeacherTrainingDetailsVideoIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.TeacherTrainingDetailsVideoUrlIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.YingXiangDetailsVideoCommentIteractor;
import com.kemizhibo.kemizhibo.yhr.presenter.resourcescenterpresenter.TeacherTrainingDetailsVideoPresenter;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.TeacherTrainingDetailsVideoView;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: on 2018/7/3.
 * Describe:教师培训详情
 */

public class TeacherTrainingDetailsVideoPresenterImpl extends BasePresenterImpl<TeacherTrainingDetailsVideoView> implements TeacherTrainingDetailsVideoPresenter {
    //注意public全局
    @Inject
    public TeacherTrainingDetailsVideoIteractor teacherTrainingDetailsVideoIteractor ;
    @Inject
    public TeacherTrainingDetailsVideoUrlIteractor teacherTrainingDetailsVideoUrlIteractor ;
    @Inject
    public CollectionIteractor collectionIteractor ;

    @Inject
    public YingXiangDetailsVideoCommentIteractor yingXiangDetailsVideoCommentIteractor ;
    @Inject
    public PutCommentIteractor putCommentIteractor ;
    @Inject
    public DeleteCommentIteractor deleteCommentIteractor ;
    @Inject
    public GetLikeIteractor getLikeIteractor ;
    @Inject
    public OneLookIteractor oneLookIteractor ;
    @Inject
    public MoreLookIteractor moreLookIteractor ;

    @Inject
    public LastLookIteractor lastLookIteractor ;

    @Inject
    public LiveRoomDetailsVideoUrlIteractor2 liveRoomDetailsVideoUrlIteractor2 ;
    @Inject
    public LiveRoomDetailsVideoUrlIteractor3 liveRoomDetailsVideoUrlIteractor3 ;
    @Inject
    public LiveRoomDetailsVideoUrlIteractor4 liveRoomDetailsVideoUrlIteractor4 ;


    @Inject
    public TeacherTrainingDetailsVideoPresenterImpl() {}


    @Override
    public void getTeacherTrainingDetailsVideoData(BaseActivity activity,String token, String courseId) {
        teacherTrainingDetailsVideoIteractor.loadTeacherTrainingDetailsVideoData(activity, new IGetDataDelegate<TeacherTrainingDetailsVideoBean>() {
            @Override
            public void getDataSuccess(TeacherTrainingDetailsVideoBean teacherTrainingDetailsVideoBean) {
                mPresenterView.onTeacherTrainingDetailsVideoSuccess(teacherTrainingDetailsVideoBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onTeacherTrainingDetailsVideoError(errmsg);
            }
        },token,courseId);
    }

    @Override
    public void getTeacherTrainingDetailsVideoUrlData(BaseActivity activity, String token,String courseId, String videoType, String encryption, String videoClarity) {
        teacherTrainingDetailsVideoUrlIteractor.loadYingXiangDetailsVideoUrlData(activity, new IGetDataDelegate<TeacherTrainingDetailsVideoUrlBean>() {
            @Override
            public void getDataSuccess(TeacherTrainingDetailsVideoUrlBean teacherTrainingDetailsVideoUrlBean) {
                mPresenterView.onTeacherTrainingDetailsVideoUrlSuccess(teacherTrainingDetailsVideoUrlBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onTeacherTrainingDetailsVideoUrlError(errmsg);
            }
        },token,courseId,videoType,encryption,videoClarity);
    }

    @Override
    public void getCollectionData(BaseActivity activity, String token, String courseId) {
        collectionIteractor.loadCollectionData(activity, new IGetDataDelegate<CollectionBean>() {
            @Override
            public void getDataSuccess(CollectionBean collectionBean) {
                mPresenterView.onGetCollectionSuccess(collectionBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onGetCollectionError(errmsg);
            }
        },token,courseId);
    }

    @Override
    public void getYingXiangDetailsVideoCommentData(BaseActivity activity, String token, String otherId, String page, String size, String type) {
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
        },token,otherId,page,size,type);
    }

    @Override
    public void getPutCommentData(BaseActivity activity, String token, String courseId, String content, String pCommentId) {
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

    @Override
    public void getOneLookData(BaseActivity activity, String token, String playPosition, String keyId, String courseId, String watchTime, String isEnd) {
        oneLookIteractor.loadOneLookData(activity, new IGetDataDelegate<OneLookBean>() {
            @Override
            public void getDataSuccess(OneLookBean oneLookBean) {
                mPresenterView.onGetOneLookSuccess(oneLookBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onGetOneLookError(errmsg);
            }
        },token,playPosition,keyId,courseId,watchTime,isEnd);
    }

    @Override
    public void getMoreLookData(BaseActivity activity, String token, String playPosition, String keyId, String courseId, String watchTime, String isEnd) {
        moreLookIteractor.loadMoreLookData(activity, new IGetDataDelegate<OneLookBean>() {
            @Override
            public void getDataSuccess(OneLookBean oneLookBean) {
                mPresenterView.onGetMoreLookSuccess(oneLookBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onGetMoreLookError(errmsg);
            }
        },token,playPosition,keyId,courseId,watchTime,isEnd);
    }

    @Override
    public void getLiveRoomDetailsVideoUrlData2(BaseActivity activity, String token, String courseId, String videoType, String encryption, String videoClarity) {
        liveRoomDetailsVideoUrlIteractor2.loadLiveRoomDetailsVideoUrlData(activity, new IGetDataDelegate<LiveRoomDetailsVideoUrlBean>() {
            @Override
            public void getDataSuccess(LiveRoomDetailsVideoUrlBean liveRoomDetailsVideoUrlBean) {
                mPresenterView.onLiveRoomDetailsVideoUrl2Success(liveRoomDetailsVideoUrlBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onLiveRoomDetailsVideoUrl2Error(errmsg);
            }
        },token,courseId,videoType,encryption,videoClarity);
    }

    @Override
    public void getLiveRoomDetailsVideoUrlData3(BaseActivity activity, String token, String courseId, String videoType, String encryption, String videoClarity) {
        liveRoomDetailsVideoUrlIteractor3.loadLiveRoomDetailsVideoUrlData(activity, new IGetDataDelegate<LiveRoomDetailsVideoUrlBean>() {
            @Override
            public void getDataSuccess(LiveRoomDetailsVideoUrlBean liveRoomDetailsVideoUrlBean) {
                mPresenterView.onLiveRoomDetailsVideoUrl3Success(liveRoomDetailsVideoUrlBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onLiveRoomDetailsVideoUrl3Error(errmsg);
            }
        },token,courseId,videoType,encryption,videoClarity);
    }

    @Override
    public void getLiveRoomDetailsVideoUrlData4(BaseActivity activity, String token, String courseId, String videoType, String encryption, String videoClarity) {
        liveRoomDetailsVideoUrlIteractor4.loadLiveRoomDetailsVideoUrlData(activity, new IGetDataDelegate<LiveRoomDetailsVideoUrlBean>() {
            @Override
            public void getDataSuccess(LiveRoomDetailsVideoUrlBean liveRoomDetailsVideoUrlBean) {
                mPresenterView.onLiveRoomDetailsVideoUrl4Success(liveRoomDetailsVideoUrlBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onLiveRoomDetailsVideoUrl4Error(errmsg);
            }
        },token,courseId,videoType,encryption,videoClarity);
    }

    @Override
    public void getLastLookData(BaseActivity activity, String token, String playPosition, String keyId, String courseId, String watchTime, String isEnd) {
        lastLookIteractor.loadLastLookData(activity, new IGetDataDelegate<OneLookBean>() {
            @Override
            public void getDataSuccess(OneLookBean oneLookBean) {
                mPresenterView.onGetLastLookSuccess(oneLookBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onGetLastLookError(errmsg);
            }
        },token,playPosition,keyId,courseId,watchTime,isEnd);
    }
}
