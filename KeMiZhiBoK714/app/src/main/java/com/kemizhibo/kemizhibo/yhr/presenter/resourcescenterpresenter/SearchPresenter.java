package com.kemizhibo.kemizhibo.yhr.presenter.resourcescenterpresenter;

import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenter;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.FilterView;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.SearchIView;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe  资源中心搜索页面
 */
public interface SearchPresenter extends BasePresenter<SearchIView> {

    void getSearchData(BaseActivity activity, String sellType, String currentPage, String pageSize, String courseName);
}
