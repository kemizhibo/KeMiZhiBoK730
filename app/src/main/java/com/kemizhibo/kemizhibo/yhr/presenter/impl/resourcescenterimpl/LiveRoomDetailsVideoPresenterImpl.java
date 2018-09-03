package com.kemizhibo.kemizhibo.yhr.presenter.impl.resourcescenterimpl;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenterImpl;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.LiveRoomDetailsBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.LiveRoomDetailsVideoUrlBean;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.LiveRoomDetailsVideoIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.LiveRoomDetailsVideoUrlIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.LiveRoomDetailsVideoUrlIteractor2;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.LiveRoomDetailsVideoUrlIteractor3;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.LiveRoomDetailsVideoUrlIteractor4;
import com.kemizhibo.kemizhibo.yhr.presenter.resourcescenterpresenter.LiveRoomDetailsVideoPresenter;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.LiveRoomDetailsView;
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
    public LiveRoomDetailsVideoUrlIteractor2 liveRoomDetailsVideoUrlIteractor2 ;
    @Inject
    public LiveRoomDetailsVideoUrlIteractor3 liveRoomDetailsVideoUrlIteractor3 ;
    @Inject
    public LiveRoomDetailsVideoUrlIteractor4 liveRoomDetailsVideoUrlIteractor4 ;

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
                mPresenterView.onLiveRoomDetailsVideoUrlError(errmsg);
            }
        },token,courseId,videoType,encryption,videoClarity);
    }

    @Override
    public void getLiveRoomDetailsVideoUrlData2(BaseActivity activity, String token, String courseId, String videoType, String encryption, String videoClarity) {
        liveRoomDetailsVideoUrlIteractor2.loadLiveRoomDetailsVideoUrlData(activity, new IGetDataDelegate<LiveRoomDetailsVideoUrlBean>() {
            @Override
            public void getDataSuccess(LiveRoomDetailsVideoUrlBean liveRoomDetailsVideoUrlBean) {
                mPresenterView.onLiveRoomDetailsVideoUrl2Success(liveRoomDetailsVideoUrlBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onLiveRoomDetailsVideoUrl2Error(errmsg);
            }
        },token,courseId,videoType,encryption,videoClarity);
    }

    @Override
    public void getLiveRoomDetailsVideoUrlData3(BaseActivity activity, String token, String courseId, String videoType, String encryption, String videoClarity) {
        liveRoomDetailsVideoUrlIteractor3.loadLiveRoomDetailsVideoUrlData(activity, new IGetDataDelegate<LiveRoomDetailsVideoUrlBean>() {
            @Override
            public void getDataSuccess(LiveRoomDetailsVideoUrlBean liveRoomDetailsVideoUrlBean) {
                mPresenterView.onLiveRoomDetailsVideoUrl3Success(liveRoomDetailsVideoUrlBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onLiveRoomDetailsVideoUrl3Error(errmsg);
            }
        },token,courseId,videoType,encryption,videoClarity);
    }

    @Override
    public void getLiveRoomDetailsVideoUrlData4(BaseActivity activity, String token, String courseId, String videoType, String encryption, String videoClarity) {
        liveRoomDetailsVideoUrlIteractor4.loadLiveRoomDetailsVideoUrlData(activity, new IGetDataDelegate<LiveRoomDetailsVideoUrlBean>() {
            @Override
            public void getDataSuccess(LiveRoomDetailsVideoUrlBean liveRoomDetailsVideoUrlBean) {
                mPresenterView.onLiveRoomDetailsVideoUrl4Success(liveRoomDetailsVideoUrlBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onLiveRoomDetailsVideoUrl4Error(errmsg);
            }
        },token,courseId,videoType,encryption,videoClarity);
    }
}
