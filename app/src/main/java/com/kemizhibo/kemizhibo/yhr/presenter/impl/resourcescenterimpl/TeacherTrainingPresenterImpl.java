package com.kemizhibo.kemizhibo.yhr.presenter.impl.resourcescenterimpl;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi.TeacherTrainingApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenterImpl;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.CommentBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.CommentDetailBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.FilterBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.TeacherTrainingBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.YingXiangDetailsVideoBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.YingXiangDetailsVideoUrlBean;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.FilterIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.PutCommentIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.TeacherTrainingIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.YingXiangDetailsVideoCommentIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.YingXiangDetailsVideoIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.YingXiangDetailsVideoUrlIteractor;
import com.kemizhibo.kemizhibo.yhr.presenter.resourcescenterpresenter.TeacherTrainingPresenter;
import com.kemizhibo.kemizhibo.yhr.presenter.resourcescenterpresenter.YingXiangDetailsVideoPresenter;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.TeacherTrainingView;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.YingXiangDetailsVideoView;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: on 2018/7/3.
 * Describe: 教师培训的筛选和数据
 */

public class TeacherTrainingPresenterImpl extends BasePresenterImpl<TeacherTrainingView> implements TeacherTrainingPresenter {
    //注意public全局
    @Inject
    public FilterIteractor filterIteractor ;

    @Inject
    public TeacherTrainingIteractor teacherTrainingIteractor ;

    @Inject
    public TeacherTrainingPresenterImpl() {}


    @Override
    public void getFilterData(BaseActivity activity) {
        filterIteractor.loadFilterData(activity, new IGetDataDelegate<FilterBean>() {
            @Override
            public void getDataSuccess(FilterBean filterBean) {
                mPresenterView.onFilterSuccess(filterBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onFilterError(errmsg);
            }
        });
    }

    @Override
    public void getTeacherTrainingData(BaseActivity activity, String sellType, String currentPage, String pageSize, String materialEdition, String subjectId, String semester, String courseType, String knowledgeId) {
        teacherTrainingIteractor.loadTeacherTrainingData(activity, new IGetDataDelegate<TeacherTrainingBean>() {
            @Override
            public void getDataSuccess(TeacherTrainingBean teacherTrainingBean) {
                mPresenterView.onTeacherTrainingSuccess(teacherTrainingBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onTeacherTrainingError(errmsg);
            }
        },sellType,currentPage,pageSize,materialEdition,subjectId,semester,courseType,knowledgeId);
    }
}
