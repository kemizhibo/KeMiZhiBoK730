package com.kemizhibo.kemizhibo.yhr.presenter.resourcescenterpresenter;

import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenter;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.ForeTeachSearchIView;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.SearchIView;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe  备授课搜索页面
 */
public interface ForTeachSearchPresenter extends BasePresenter<ForeTeachSearchIView> {
    void getForTeachSearchData(BaseActivity activity, String token, String keyWord, String pageSize,String currentPage);
}
