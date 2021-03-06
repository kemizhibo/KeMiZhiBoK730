package com.kemizhibo.kemizhibo.yhr.presenter.resourcescenterpresenter;

import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenter;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.TeacherTrainingDetailsVideoView;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.YingXiangDetailsVideoView;

/**
 * Author: yhr
 * Date: on 2018/7/3.
 * Describe:教师培训详情页
 */

public interface TeacherTrainingDetailsVideoPresenter extends BasePresenter<TeacherTrainingDetailsVideoView> {
    void getTeacherTrainingDetailsVideoData(BaseActivity activity, String token,String courseId);
    //获取视频地址
    void getTeacherTrainingDetailsVideoUrlData(BaseActivity activity,String token,String courseId,String videoType,String encryption,String videoClarity);
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
    //记录第n次播放位置
    void getMoreLookData(BaseActivity activity,String token, String playPosition,String keyId,String courseId,String watchTime,String isEnd);
    //记录播放完毕播放位置
    void getLastLookData(BaseActivity activity,String token, String playPosition,String keyId,String courseId,String watchTime,String isEnd);
    void getLiveRoomDetailsVideoUrlData2(BaseActivity activity,String token,String courseId,String videoType,String encryption,String videoClarity);
    void getLiveRoomDetailsVideoUrlData3(BaseActivity activity,String token,String courseId,String videoType,String encryption,String videoClarity);
    void getLiveRoomDetailsVideoUrlData4(BaseActivity activity,String token,String courseId,String videoType,String encryption,String videoClarity);
}
