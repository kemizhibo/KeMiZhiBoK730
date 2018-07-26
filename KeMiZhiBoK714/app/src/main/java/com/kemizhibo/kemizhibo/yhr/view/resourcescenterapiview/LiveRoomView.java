package com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview;


import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BaseView;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.FilterBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.LiveRoomBean;

/**
 * Author: yhr
 * Date: on 2018/7/9.
 * Describe:科科学观察室的筛选和列表
 */

public interface LiveRoomView extends BaseView {
    //筛选
    void onFilterSuccess(FilterBean filterBean);
    void onFilterError(String msg);

    void onLiveRoomSuccess(LiveRoomBean liveRoomBean);
    void onLiveRoomError(String msg);
}
