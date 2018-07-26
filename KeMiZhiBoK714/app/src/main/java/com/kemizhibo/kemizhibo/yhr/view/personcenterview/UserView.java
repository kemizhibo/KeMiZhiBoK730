package com.kemizhibo.kemizhibo.yhr.view.personcenterview;

import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BaseView;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.ChangeUserBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.GetUserBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.UserBean;

/**
 * Created by 17600 on 2018/5/18.
 */

public interface UserView extends BaseView {
    //获取用户信息
    void onUserSuccess(UserBean userBean);
    void onUserError(String msg);

}
