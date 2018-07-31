package com.kemizhibo.kemizhibo.yhr.presenter.homepagerpresenter;

import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenter;
import com.kemizhibo.kemizhibo.yhr.view.homepagerview.SowingMapView;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.LiveRoomView;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe  轮播
 */
public interface SowingMapPresenter extends BasePresenter<SowingMapView> {
    void getSowingMapData(BaseActivity activity, String device);
}
