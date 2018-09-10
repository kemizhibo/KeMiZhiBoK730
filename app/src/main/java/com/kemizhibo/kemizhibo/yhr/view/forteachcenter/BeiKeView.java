package com.kemizhibo.kemizhibo.yhr.view.forteachcenter;

import com.kemizhibo.kemizhibo.other.preparing_center.bean.PreparingCenterBean;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BaseView;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.FilterBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.ForTeachSearchBean;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe    备课中心
 */
public interface BeiKeView extends BaseView{
    void onBeiKeSuccess(PreparingCenterBean preparingCenterBean);
    void onBeiKeError(String msg);

    //筛选
    void onFilterSuccess(FilterBean filterBean);
    void onFilterError(String msg);
}
