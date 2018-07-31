package com.kemizhibo.kemizhibo.yhr.presenter.impl.resourcescenterimpl;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenterImpl;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.TeacherTrainingBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.TeacherTrainingDetailsVideoUrlBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.TeacherTrainingLookBean;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.TeacherTrainingDetailsVideoUrlIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.TeacherTrainingIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.TeacherTrainingLookFragmentIteractor;
import com.kemizhibo.kemizhibo.yhr.presenter.resourcescenterpresenter.TeacherTrainingLookFragmentPresenter;
import com.kemizhibo.kemizhibo.yhr.presenter.resourcescenterpresenter.TeacherTrainingPresenter;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.TeacherTrainingLookFragmentView;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.TeacherTrainingView;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: on 2018/7/3.
 * Describe:
 */

public class TeacherTrainingPresenterLookFragmentImpl extends BasePresenterImpl<TeacherTrainingLookFragmentView> implements TeacherTrainingLookFragmentPresenter {
    //注意public全局
    @Inject
    public TeacherTrainingLookFragmentIteractor teacherTrainingLookFragmentIteractor ;

    @Inject
    public TeacherTrainingDetailsVideoUrlIteractor teacherTrainingDetailsVideoUrlIteractor ;

    @Inject
    public TeacherTrainingPresenterLookFragmentImpl() {}

    @Override
    public void getTeacherTrainingLookFragmentData(BaseActivity activity, String sellType, String subjectId, String currentPage, String pageSize) {
        teacherTrainingLookFragmentIteractor.loadTeacherTrainingLookFragmentData(activity, new IGetDataDelegate<TeacherTrainingLookBean>() {
            @Override
            public void getDataSuccess(TeacherTrainingLookBean teacherTrainingLookBean) {
                mPresenterView.onTeacherTrainingLookFragmentSuccess(teacherTrainingLookBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onTeacherTrainingLookFragmentError(errmsg);
            }
        },sellType,subjectId,currentPage,pageSize);
    }


    @Override
    public void getTeacherTrainingDetailsVideoUrlData(BaseActivity activity,String token, String courseId, String videoType, String encryption, String videoClarity) {
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
}
