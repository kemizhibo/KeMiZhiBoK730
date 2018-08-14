package com.kemizhibo.kemizhibo.yhr.view.personcenterview;

import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BaseView;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.ClearLiuLanBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.LiuLanBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.SignOutBean;

/**
 * Created by 17600 on 2018/5/18.
 */

public interface LiuLanView extends BaseView {

    //浏览记录
    void onLiuLanSuccess(LiuLanBean liuLanBean);
    void onLiuLanError(String msg);
    //清空浏览记录
    void onClearLiuLanSuccess(ClearLiuLanBean clearLiuLanBean);
    void onClearLiuLanError(String msg);
    //删除一个或者多个浏览记录
    void onClearOneOrMoreLiuLanSuccess(ClearLiuLanBean clearLiuLanBean);
    void onClearOneOrMoreLiuLanError(String msg);
}
