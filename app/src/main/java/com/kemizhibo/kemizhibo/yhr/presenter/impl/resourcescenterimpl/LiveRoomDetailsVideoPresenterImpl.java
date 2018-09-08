package com.kemizhibo.kemizhibo.yhr.presenter.impl.resourcescenterimpl;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenterImpl;
import com.kemizhibo.kemizhibo.yhr.bean.LectureBean;
import com.kemizhibo.kemizhibo.yhr.bean.forteachbean.ForTeachPlayUrlBean;
import com.kemizhibo.kemizhibo.yhr.bean.forteachbean.InitLectureBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.LiveRoomDetailsBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.LiveRoomDetailsVideoUrlBean;
import com.kemizhibo.kemizhibo.yhr.interactor.LectureIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.forteachinterractor.ForTeachPlayUrlIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.forteachinterractor.ForTeachPlayUrlIteractor2;
import com.kemizhibo.kemizhibo.yhr.interactor.forteachinterractor.ForTeachPlayUrlIteractor3;
import com.kemizhibo.kemizhibo.yhr.interactor.forteachinterractor.ForTeachPlayUrlIteractor4;
import com.kemizhibo.kemizhibo.yhr.interactor.forteachinterractor.InitLectureIteractor;
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
    public LectureIteractor lectureIteractor;
    @Inject
    public InitLectureIteractor initLectureIteractor;
    @Inject
    public ForTeachPlayUrlIteractor forTeachPlayUrlIteractor;
    @Inject
    public ForTeachPlayUrlIteractor2 forTeachPlayUrlIteractor2;
    @Inject
    public ForTeachPlayUrlIteractor3 forTeachPlayUrlIteractor3;
    @Inject
    public ForTeachPlayUrlIteractor4 forTeachPlayUrlIteractor4;

    @Inject
    public LiveRoomDetailsVideoPresenterImpl() {}

    @Override
    public void getLectureData(BaseActivity activity, String token, String moduleId, String kemiVideoPlan) {
        lectureIteractor.loadLectureData(activity, new IGetDataDelegate<LectureBean>() {
            @Override
            public void getDataSuccess(LectureBean lectureBean) {
                mPresenterView.onLectureSuccess(lectureBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onLectureError(errmsg);
            }
        },token,moduleId,kemiVideoPlan);
    }

    @Override
    public void getForTeachPlayUrlData2(BaseActivity activity, String courseId, String videoType, String encryption, String videoClarity, String kpointId) {
        forTeachPlayUrlIteractor2.loadForTeachPlayUrlData(activity, new IGetDataDelegate<ForTeachPlayUrlBean>() {
            @Override
            public void getDataSuccess(ForTeachPlayUrlBean forTeachPlayUrlBean) {
                mPresenterView.onForTeachPlayUrl2Success(forTeachPlayUrlBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onForTeachPlayUrl2Error(errmsg);
            }
        },courseId,videoType,encryption,videoClarity,kpointId);
    }

    @Override
    public void getForTeachPlayUrlData3(BaseActivity activity, String courseId, String videoType, String encryption, String videoClarity, String kpointId) {
        forTeachPlayUrlIteractor3.loadForTeachPlayUrlData(activity, new IGetDataDelegate<ForTeachPlayUrlBean>() {
            @Override
            public void getDataSuccess(ForTeachPlayUrlBean forTeachPlayUrlBean) {
                mPresenterView.onForTeachPlayUrl3Success(forTeachPlayUrlBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onForTeachPlayUrl3Error(errmsg);
            }
        },courseId,videoType,encryption,videoClarity,kpointId);
    }

    @Override
    public void getForTeachPlayUrlData4(BaseActivity activity, String courseId, String videoType, String encryption, String videoClarity, String kpointId) {
        forTeachPlayUrlIteractor4.loadForTeachPlayUrlData(activity, new IGetDataDelegate<ForTeachPlayUrlBean>() {
            @Override
            public void getDataSuccess(ForTeachPlayUrlBean forTeachPlayUrlBean) {
                mPresenterView.onForTeachPlayUrl4Success(forTeachPlayUrlBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onForTeachPlayUrl4Error(errmsg);
            }
        },courseId,videoType,encryption,videoClarity,kpointId);
    }

    @Override
    public void getForTeachPlayUrlData(BaseActivity activity, String courseId, String videoType, String encryption, String videoClarity, String kpointId) {
        forTeachPlayUrlIteractor.loadForTeachPlayUrlData(activity, new IGetDataDelegate<ForTeachPlayUrlBean>() {
            @Override
            public void getDataSuccess(ForTeachPlayUrlBean forTeachPlayUrlBean) {
                mPresenterView.onForTeachPlayUrlSuccess(forTeachPlayUrlBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onForTeachPlayUrlError(errmsg);
            }
        },courseId,videoType,encryption,videoClarity,kpointId);
    }

    @Override
    public void getInitLectureData(BaseActivity activity, String token, String moduleId) {
        initLectureIteractor.loadInitLectureData(activity, new IGetDataDelegate<InitLectureBean>() {
            @Override
            public void getDataSuccess(InitLectureBean initLectureBean) {
                mPresenterView.onInitLectureSuccess(initLectureBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onInitLectureError(errmsg);
            }
        },token,moduleId);
    }
}
