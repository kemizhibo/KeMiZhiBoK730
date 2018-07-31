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

    void getLiveRoomDetailsVideoData(BaseActivity activity, String courseId);

    //获取视频地址
    void getLiveRoomDetailsVideoUrlData(BaseActivity activity,String courseId,String videoType,String encryption,String videoClarity);
}
