package com.kemizhibo.kemizhibo.yhr.view.personcenterview;

import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BaseView;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.ChangeUserBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.GetUserBean;

/**
 * Created by 17600 on 2018/5/18.
 */

public interface GetUserView extends BaseView {
    //获取用户信息
    void onUserSuccess(GetUserBean getUserBean);
    void onUserError(String msg);

    //更改用户信息
    void onChangeUserSuccess(ChangeUserBean changeUserBean);
    void onChangeUserError(String msg);
}
