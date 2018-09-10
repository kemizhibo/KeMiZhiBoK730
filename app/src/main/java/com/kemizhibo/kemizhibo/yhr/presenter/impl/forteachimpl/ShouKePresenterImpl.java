package com.kemizhibo.kemizhibo.yhr.presenter.impl.forteachimpl;

import com.kemizhibo.kemizhibo.other.common.bean.CommonUserTeachPlanBean;
import com.kemizhibo.kemizhibo.other.preparing_center.bean.PreparingCenterBean;
import com.kemizhibo.kemizhibo.other.preparing_online.bean.PreparingOnlineBean;
import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.preparing_lessons.bean.PreparingLessonsBean;
import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.teaching_lessons.bean.TeachingLessonsBean;
import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenterImpl;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.FilterBean;
import com.kemizhibo.kemizhibo.yhr.interactor.forteachinterractor.BeiKeIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.forteachinterractor.ShouKeIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.forteachinterractor.UserPlanIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.FilterIteractor;
import com.kemizhibo.kemizhibo.yhr.presenter.forteachpresenter.BeiKePresenter;
import com.kemizhibo.kemizhibo.yhr.presenter.forteachpresenter.ShouKePresenter;
import com.kemizhibo.kemizhibo.yhr.view.forteachcenter.BeiKeView;
import com.kemizhibo.kemizhibo.yhr.view.forteachcenter.ShouKeView;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe   授课列表
 */
public class ShouKePresenterImpl extends BasePresenterImpl<ShouKeView> implements ShouKePresenter {
    //注意public全局
    @Inject
    public ShouKeIteractor shouKeIteractor ;

    @Inject
    public FilterIteractor filterIteractor ;

    @Inject
    public UserPlanIteractor userPlanIteractor ;

    @Inject
    public ShouKePresenterImpl() {}

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
    public void getShouKeData(BaseActivity activity, String token, String materialId, String gradeId, String semesterId, String pageSize, String currentPage, String type) {
        shouKeIteractor.loadShouKeData(activity, new IGetDataDelegate<TeachingLessonsBean>() {
            @Override
            public void getDataSuccess(TeachingLessonsBean teachingLessonsBean) {
                mPresenterView.onShouKeSuccess(teachingLessonsBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onShouKeError(errmsg);
            }
        },token,materialId,gradeId,semesterId,pageSize,currentPage,type);
    }

    @Override
    public void getUserPlanData(BaseActivity activity, String token, String courseId, String lastPlan) {
        userPlanIteractor.loadUserPlanData(activity, new IGetDataDelegate<CommonUserTeachPlanBean>() {
            @Override
            public void getDataSuccess(CommonUserTeachPlanBean commonUserTeachPlanBean) {
                mPresenterView.onUserPlanSuccess(commonUserTeachPlanBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onUserPlanError(errmsg);
            }
        },token,courseId,lastPlan);
    }

}
