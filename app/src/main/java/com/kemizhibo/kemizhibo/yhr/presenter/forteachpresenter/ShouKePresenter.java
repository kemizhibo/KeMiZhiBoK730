package com.kemizhibo.kemizhibo.yhr.presenter.forteachpresenter;

import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenter;
import com.kemizhibo.kemizhibo.yhr.view.forteachcenter.BeiKeView;
import com.kemizhibo.kemizhibo.yhr.view.forteachcenter.ShouKeView;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe  授课列表
 */
public interface ShouKePresenter extends BasePresenter<ShouKeView> {

    void getFilterData(BaseActivity activity);
    //首页信息
    void getShouKeData(BaseActivity activity, String token, String materialId, String gradeId, String semesterId, String pageSize,String currentPage, String type);
    //获取方案
    void getUserPlanData(BaseActivity activity, String token, String courseId, String lastPlan);

}
