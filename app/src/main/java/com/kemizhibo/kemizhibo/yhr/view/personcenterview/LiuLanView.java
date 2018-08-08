package com.kemizhibo.kemizhibo.yhr.view.personcenterview;

import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BaseView;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.LiuLanBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.SignOutBean;

/**
 * Created by 17600 on 2018/5/18.
 */

public interface LiuLanView extends BaseView {

    //浏览记录
    void onLiuLanSuccess(LiuLanBean liuLanBean);
    void onLiuLanError(String msg);
}
