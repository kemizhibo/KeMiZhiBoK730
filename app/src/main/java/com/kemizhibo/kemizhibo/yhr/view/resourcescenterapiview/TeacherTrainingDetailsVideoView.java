package com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview;

import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BaseView;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.CollectionBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.CommentBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.CommentDetailBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.DeleteCommentBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.GetLikeBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.LiveRoomDetailsVideoUrlBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.OneLookBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.ReplyCommentBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.TeacherTrainingDetailsVideoBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.TeacherTrainingDetailsVideoUrlBean;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe  整个直播详情页面
 */
public interface TeacherTrainingDetailsVideoView extends BaseView{

    void onTeacherTrainingDetailsVideoSuccess(TeacherTrainingDetailsVideoBean teacherTrainingDetailsVideoBean);
    void onTeacherTrainingDetailsVideoError(String msg);

    //获取视频地址
    void onTeacherTrainingDetailsVideoUrlSuccess(TeacherTrainingDetailsVideoUrlBean teacherTrainingDetailsVideoUrlBean);
    void onTeacherTrainingDetailsVideoUrlError(String msg);

    //收藏视频
    void onGetCollectionSuccess(CollectionBean collectionBean);
    void onGetCollectionError(String msg);

    //获取视频详情评论列表
    void onYingXiangDetailsVideoCommentSuccess(CommentBean commentBean);
    void onYingXiangDetailsVideoCommentError(String msg);

    //发表评论
    void onPutCommentSuccess(CommentDetailBean commentDetailBean);
    void onPutCommentError(String msg);


    //删除评论
    void onDeleteCommentSuccess(DeleteCommentBean deleteCommentBean);
    void onDeleteCommentError(String msg);

    //点赞评论
    void onGetLikeSuccess(GetLikeBean getLikeBean);
    void onGetLikeError(String msg);
    //记录第一次播放位置
    void onGetOneLookSuccess(OneLookBean oneLookBean);
    void onGetOneLookError(String msg);

    //记录第n次播放位置
    void onGetMoreLookSuccess(OneLookBean oneLookBean);
    void onGetMoreLookError(String msg);

    //获取视频地址2
    void onLiveRoomDetailsVideoUrl2Success(LiveRoomDetailsVideoUrlBean liveRoomDetailsVideoUrlBean);
    void onLiveRoomDetailsVideoUrl2Error(String msg);
    //获取视频地址3
    void onLiveRoomDetailsVideoUrl3Success(LiveRoomDetailsVideoUrlBean liveRoomDetailsVideoUrlBean);
    void onLiveRoomDetailsVideoUrl3Error(String msg);
    //获取视频地址4
    void onLiveRoomDetailsVideoUrl4Success(LiveRoomDetailsVideoUrlBean liveRoomDetailsVideoUrlBean);
    void onLiveRoomDetailsVideoUrl4Error(String msg);

}
