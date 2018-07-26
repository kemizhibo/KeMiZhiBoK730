package com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview;

import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BaseView;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.FilterBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.SearchBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.YingXiangFragmentBean;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe    搜索
 */
public interface SearchIView extends BaseView{
    void onSearchSuccess(SearchBean searchBean);
    void onSearchError(String msg);
}
