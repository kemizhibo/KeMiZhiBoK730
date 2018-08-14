package com.kemizhibo.kemizhibo.yhr.presenter.impl.homeimpl;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenterImpl;
import com.kemizhibo.kemizhibo.yhr.bean.homepagerbean.HomePageBean;
import com.kemizhibo.kemizhibo.yhr.bean.homepagerbean.VersionInformationBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.FilterBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.YingXiangFragmentBean;
import com.kemizhibo.kemizhibo.yhr.interactor.homeinteractor.HomePageIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.homeinteractor.VersionInformationIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.FilterIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.YingXiangFragmentIteractor;
import com.kemizhibo.kemizhibo.yhr.presenter.homepagerpresenter.HomePagePresenter;
import com.kemizhibo.kemizhibo.yhr.presenter.resourcescenterpresenter.FilterPresenter;
import com.kemizhibo.kemizhibo.yhr.view.homepagerview.HomePageView;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.FilterView;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe   首页
 */
public class HomePagePresenterImpl extends BasePresenterImpl<HomePageView> implements HomePagePresenter {
    //注意public全局
    @Inject
    public HomePageIteractor homePageIteractor ;


    @Inject
    public HomePagePresenterImpl() {}

    @Override
    public void getHomePageData(BaseActivity activity,String token) {
        homePageIteractor.loadHomePageData(activity, new IGetDataDelegate<HomePageBean>() {
            @Override
            public void getDataSuccess(HomePageBean homePageBean) {
                mPresenterView.onHomePageSuccess(homePageBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onHomePageError(errmsg);
            }
        },token);
    }
}
