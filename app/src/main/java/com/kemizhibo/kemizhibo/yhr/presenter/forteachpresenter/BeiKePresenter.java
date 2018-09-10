package com.kemizhibo.kemizhibo.yhr.presenter.forteachpresenter;

import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenter;
import com.kemizhibo.kemizhibo.yhr.view.forteachcenter.BeiKeView;
import com.kemizhibo.kemizhibo.yhr.view.homepagerview.HomePageView;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe  备课列表
 */
public interface BeiKePresenter extends BasePresenter<BeiKeView> {

    void getFilterData(BaseActivity activity);
    //首页信息
    void getBeiKeData(BaseActivity activity, String token, String materialId, String gradeId, String semesterId, String requestSource,String currentPage,String pageSize);
}
