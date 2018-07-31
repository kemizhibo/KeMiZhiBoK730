package com.kemizhibo.kemizhibo.yhr.view.homepagerview;

import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BaseView;
import com.kemizhibo.kemizhibo.yhr.bean.homepagerbean.HomePageBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.SearchBean;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe    首页
 */
public interface HomePageView extends BaseView{
    void onHomePageSuccess(HomePageBean searchBean);
    void onHomePageError(String msg);
}
