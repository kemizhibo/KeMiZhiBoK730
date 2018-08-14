package com.kemizhibo.kemizhibo.yhr.presenter.homepagerpresenter;

import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenter;
import com.kemizhibo.kemizhibo.yhr.view.homepagerview.HomePageView;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.FilterView;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe  首页
 */
public interface HomePagePresenter extends BasePresenter<HomePageView> {
    //首页信息
    void getHomePageData(BaseActivity activity,String token);

}
