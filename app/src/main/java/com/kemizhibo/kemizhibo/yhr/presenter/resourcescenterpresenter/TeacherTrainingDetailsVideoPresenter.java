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
}
