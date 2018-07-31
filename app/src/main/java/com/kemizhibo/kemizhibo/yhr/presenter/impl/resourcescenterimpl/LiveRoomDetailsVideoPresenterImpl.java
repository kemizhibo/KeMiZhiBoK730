package com.kemizhibo.kemizhibo.yhr.presenter.impl.resourcescenterimpl;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenterImpl;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.LiveRoomDetailsBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.LiveRoomDetailsVideoUrlBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.TeacherTrainingDetailsVideoBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.TeacherTrainingDetailsVideoUrlBean;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.LiveRoomDetailsVideoIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.LiveRoomDetailsVideoUrlIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.TeacherTrainingDetailsVideoIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.TeacherTrainingDetailsVideoUrlIteractor;
import com.kemizhibo.kemizhibo.yhr.presenter.resourcescenterpresenter.LiveRoomDetailsVideoPresenter;
import com.kemizhibo.kemizhibo.yhr.presenter.resourcescenterpresenter.TeacherTrainingDetailsVideoPresenter;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.LiveRoomDetailsView;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.TeacherTrainingDetailsVideoView;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: on 2018/7/3.
 * Describe:科学观察室详情
 */

public class LiveRoomDetailsVideoPresenterImpl extends BasePresenterImpl<LiveRoomDetailsView> implements LiveRoomDetailsVideoPresenter {
    //注意public全局
    @Inject
    public LiveRoomDetailsVideoIteractor liveRoomDetailsVideoIteractor ;
    @Inject
    public LiveRoomDetailsVideoUrlIteractor liveRoomDetailsVideoUrlIteractor ;


    @Inject
    public LiveRoomDetailsVideoPresenterImpl() {}


    @Override
    public void getLiveRoomDetailsVideoData(BaseActivity activity,String token, String courseId) {
        liveRoomDetailsVideoIteractor.loadLiveRoomDetailsVideoData(activity, new IGetDataDelegate<LiveRoomDetailsBean>() {
            @Override
            public void getDataSuccess(LiveRoomDetailsBean liveRoomDetailsBean) {
                mPresenterView.onLiveRoomDetailsSuccess(liveRoomDetailsBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onLiveRoomDetailsError(errmsg);
            }
        },token,courseId);
    }


    @Override
    public void getLiveRoomDetailsVideoUrlData(BaseActivity activity, String token,String courseId, String videoType, String encryption, String videoClarity) {
        liveRoomDetailsVideoUrlIteractor.loadLiveRoomDetailsVideoUrlData(activity, new IGetDataDelegate<LiveRoomDetailsVideoUrlBean>() {
            @Override
            public void getDataSuccess(LiveRoomDetailsVideoUrlBean liveRoomDetailsVideoUrlBean) {
                mPresenterView.onLiveRoomDetailsVideoUrlSuccess(liveRoomDetailsVideoUrlBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onLiveRoomDetailsError(errmsg);
            }
        },token,courseId,videoType,encryption,videoClarity);
    }
}
