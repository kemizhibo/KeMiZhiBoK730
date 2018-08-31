package com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview;

import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BaseView;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.ForTeachSearchBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.SearchBean;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe    备授课搜索
 */
public interface ForeTeachSearchIView extends BaseView{
    void onForeTeachSearchSuccess(ForTeachSearchBean foreTeachSearchBean);
    void onForeTeachSearchError(String msg);
}
