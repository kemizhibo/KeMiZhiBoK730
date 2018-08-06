package com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview;


import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BaseView;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.LiveRoomBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.LiveRoomDetailsBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.LiveRoomDetailsVideoUrlBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.TeacherTrainingDetailsVideoUrlBean;

/**
 * Author: yhr
 * Date: on 2018/7/9.
 * Describe:科学观察室详情页
 */

public interface LiveRoomDetailsView extends BaseView {
    //获取详情信息
    void onLiveRoomDetailsSuccess(LiveRoomDetailsBean liveRoomDetailsBean);
    void onLiveRoomDetailsError(String msg);

    //获取视频地址
    void onLiveRoomDetailsVideoUrlSuccess(LiveRoomDetailsVideoUrlBean liveRoomDetailsVideoUrlBean);
    void onLiveRoomDetailsVideoUrlError(String msg);
}