package com.kemizhibo.kemizhibo.yhr.presenter.impl.forteachimpl;

import com.kemizhibo.kemizhibo.other.preparing_center.bean.PreparingCenterBean;
import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenterImpl;
import com.kemizhibo.kemizhibo.yhr.bean.homepagerbean.HomePageBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.FilterBean;
import com.kemizhibo.kemizhibo.yhr.interactor.forteachinterractor.BeiKeIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.homeinteractor.HomePageIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.FilterIteractor;
import com.kemizhibo.kemizhibo.yhr.presenter.forteachpresenter.BeiKePresenter;
import com.kemizhibo.kemizhibo.yhr.presenter.homepagerpresenter.HomePagePresenter;
import com.kemizhibo.kemizhibo.yhr.view.forteachcenter.BeiKeView;
import com.kemizhibo.kemizhibo.yhr.view.homepagerview.HomePageView;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe   备课列表
 */
public class BeiKePresenterImpl extends BasePresenterImpl<BeiKeView> implements BeiKePresenter {
    //注意public全局
    @Inject
    public BeiKeIteractor beiKeIteractor ;

    @Inject
    public FilterIteractor filterIteractor ;

    @Inject
    public BeiKePresenterImpl() {}

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
    public void getBeiKeData(BaseActivity activity, String token, String materialId, String gradeId, String semesterId, String requestSource, String currentPage, String pageSize) {
        beiKeIteractor.loadBeiKeData(activity, new IGetDataDelegate<PreparingCenterBean>() {
            @Override
            public void getDataSuccess(PreparingCenterBean preparingCenterBean) {
                mPresenterView.onBeiKeSuccess(preparingCenterBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onBeiKeError(errmsg);
            }
        },token,materialId,gradeId,semesterId,requestSource,currentPage,pageSize);
    }
}
