package com.kemizhibo.kemizhibo.yhr.presenter.resourcescenterpresenter;

import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenter;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.YingXiangDetailsVideoView;

/**
 * Author: yhr
 * Date: on 2018/7/3.
 * Describe:影响素材
 */

public interface YingXiangDetailsVideoPresenter extends BasePresenter<YingXiangDetailsVideoView> {
    void getYingXiangDetailsVideoData(BaseActivity activity,String token,String courseId);

    void getYingXiangDetailsVideoUrlData(BaseActivity activity,String token,String courseId,String videoType,String encryption,String videoClarity);
    //收藏
    void getCollectionData(BaseActivity activity, String courseId,String token);

    void getYingXiangDetailsVideoCommentData(BaseActivity activity,String token,String otherId,String page,String size,String type);
    //发表评论
    void getPutCommentData(BaseActivity activity,String token,String courseId,String content,String pCommentId);

    //删除评论
    void getDeleteCommentData(BaseActivity activity,String token, String commentId,String type);
    //点赞评论
    void getLikeData(BaseActivity activity,String token, String targetId,String type);
    //记录第一次播放位置
    void getOneLookData(BaseActivity activity,String token, String playPosition,String keyId,String courseId,String watchTime,String isEnd);
}
