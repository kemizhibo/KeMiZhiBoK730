package com.kemizhibo.kemizhibo.yhr.presenter.resourcescenterpresenter;

import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenter;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.FilterView;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe  筛选
 */
public interface FilterPresenter extends BasePresenter<FilterView> {

    void getFilterData(BaseActivity activity);


    void getYingXiangFragmentData(BaseActivity activity,String token,String sellType,String currentPage,String pageSize,String materialEdition,String subjectId,String semester,String knowledgeId);
}
