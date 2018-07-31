package com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview;

import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BaseView;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.FilterBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.YingXiangFragmentBean;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe    筛选
 */
public interface FilterView extends BaseView{
    void onFilterSuccess(FilterBean filterBean);
    void onFilterError(String msg);


    void onYingXiangFragmentSuccess(YingXiangFragmentBean yingXiangFragmentBean);
    void onYingXiangFragmentError(String msg);
}
