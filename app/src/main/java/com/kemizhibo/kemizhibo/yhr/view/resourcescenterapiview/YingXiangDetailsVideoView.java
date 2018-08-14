package com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview;

import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BaseView;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.LiuLanBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.CollectionBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.CommentDetailBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.DeleteCommentBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.GetLikeBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.OneLookBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.ReplyCommentBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.YingXiangDetailsVideoBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.CommentBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.YingXiangDetailsVideoUrlBean;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe
 */
public interface YingXiangDetailsVideoView extends BaseView{
    void onYingXiangDetailsVideoSuccess(YingXiangDetailsVideoBean yingXiangDetailsVideoBean);
    void onYingXiangDetailsVideoError(String msg);

    //获取视频路径
    void onYingXiangDetailsVideoUrlSuccess(YingXiangDetailsVideoUrlBean yingXiangDetailsVideoUrlBean);
    void onYingXiangDetailsVideoUrlError(String msg);

    //获取视频详情评论列表
    void onYingXiangDetailsVideoCommentSuccess(CommentBean commentBean);
    void onYingXiangDetailsVideoCommentError(String msg);

    //发表评论
    void onPutCommentSuccess(CommentDetailBean commentDetailBean);
    void onPutCommentError(String msg);
    //回复评论
    void onReplyCommentSuccess(ReplyCommentBean replyCommentBean);
    void onReplyCommentError(String msg);

    //删除评论
    void onDeleteCommentSuccess(DeleteCommentBean deleteCommentBean);
    void onDeleteCommentError(String msg);

    //收藏视频
    void onGetCollectionSuccess(CollectionBean collectionBean);
    void onGetCollectionError(String msg);
    //点赞评论
    void onGetLikeSuccess(GetLikeBean getLikeBean);
    void onGetLikeError(String msg);
    //记录第一次播放位置
    void onGetOneLookSuccess(OneLookBean oneLookBean);
    void onGetOneLookError(String msg);

}
