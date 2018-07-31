package com.kemizhibo.kemizhibo.yhr.presenter.impl.resourcescenterimpl;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenterImpl;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.CollectionBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.TeacherTrainingDetailsVideoBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.TeacherTrainingDetailsVideoUrlBean;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.CollectionIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.TeacherTrainingDetailsVideoIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.TeacherTrainingDetailsVideoUrlIteractor;
import com.kemizhibo.kemizhibo.yhr.presenter.resourcescenterpresenter.TeacherTrainingDetailsVideoPresenter;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.TeacherTrainingDetailsVideoView;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: on 2018/7/3.
 * Describe:教师培训详情
 */

public class TeacherTrainingDetailsVideoPresenterImpl extends BasePresenterImpl<TeacherTrainingDetailsVideoView> implements TeacherTrainingDetailsVideoPresenter {
    //注意public全局
    @Inject
    public TeacherTrainingDetailsVideoIteractor teacherTrainingDetailsVideoIteractor ;
    @Inject
    public TeacherTrainingDetailsVideoUrlIteractor teacherTrainingDetailsVideoUrlIteractor ;
    @Inject
    public CollectionIteractor collectionIteractor ;

    @Inject
    public TeacherTrainingDetailsVideoPresenterImpl() {}


    @Override
    public void getTeacherTrainingDetailsVideoData(BaseActivity activity,String token, String courseId) {
        teacherTrainingDetailsVideoIteractor.loadTeacherTrainingDetailsVideoData(activity, new IGetDataDelegate<TeacherTrainingDetailsVideoBean>() {
            @Override
            public void getDataSuccess(TeacherTrainingDetailsVideoBean teacherTrainingDetailsVideoBean) {
                mPresenterView.onTeacherTrainingDetailsVideoSuccess(teacherTrainingDetailsVideoBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onTeacherTrainingDetailsVideoError(errmsg);
            }
        },token,courseId);
    }

    @Override
    public void getTeacherTrainingDetailsVideoUrlData(BaseActivity activity, String token,String courseId, String videoType, String encryption, String videoClarity) {
        teacherTrainingDetailsVideoUrlIteractor.loadYingXiangDetailsVideoUrlData(activity, new IGetDataDelegate<TeacherTrainingDetailsVideoUrlBean>() {
            @Override
            public void getDataSuccess(TeacherTrainingDetailsVideoUrlBean teacherTrainingDetailsVideoUrlBean) {
                mPresenterView.onTeacherTrainingDetailsVideoUrlSuccess(teacherTrainingDetailsVideoUrlBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onTeacherTrainingDetailsVideoUrlError(errmsg);
            }
        },token,courseId,videoType,encryption,videoClarity);
    }

    @Override
    public void getCollectionData(BaseActivity activity, String token, String courseId) {
        collectionIteractor.loadCollectionData(activity, new IGetDataDelegate<CollectionBean>() {
            @Override
            public void getDataSuccess(CollectionBean collectionBean) {
                mPresenterView.onGetCollectionSuccess(collectionBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onGetCollectionError(errmsg);
            }
        },token,courseId);
    }
}
