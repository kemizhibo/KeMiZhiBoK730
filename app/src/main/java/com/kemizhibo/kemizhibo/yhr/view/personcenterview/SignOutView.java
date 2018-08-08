package com.kemizhibo.kemizhibo.yhr.view.personcenterview;

import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BaseView;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.SendYanZhengMaBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.SignOutBean;

/**
 * Created by 17600 on 2018/5/18.
 */

public interface SignOutView extends BaseView {
    //退出登录
    void onSignOutSuccess(SignOutBean signOutBean);
    void onSignOutError(String msg);

}
