package com.kemizhibo.kemizhibo.yhr.presenter.resourcescenterpresenter;

import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenter;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.LiveRoomDetailsView;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.TeacherTrainingDetailsVideoView;

/**
 * Author: yhr
 * Date: on 2018/7/3.
 * Describe:科学观察室详情页
 */

public interface LiveRoomDetailsVideoPresenter extends BasePresenter<LiveRoomDetailsView> {

    //完成一键授课
    void getLectureData(BaseActivity activity, String token,String moduleId, String kemiVideoPlan);
    //预览一件授课
    void getInitLectureData(BaseActivity activity, String token,String moduleId);
    //获取一键授课播放地址i
    void getForTeachPlayUrlData(BaseActivity activity, String courseId,String videoType, String encryption,String videoClarity,String kpointId);
    void getForTeachPlayUrlData2(BaseActivity activity, String courseId,String videoType, String encryption,String videoClarity,String kpointId);
    void getForTeachPlayUrlData3(BaseActivity activity, String courseId,String videoType, String encryption,String videoClarity,String kpointId);
    void getForTeachPlayUrlData4(BaseActivity activity, String courseId,String videoType, String encryption,String videoClarity,String kpointId);
}
