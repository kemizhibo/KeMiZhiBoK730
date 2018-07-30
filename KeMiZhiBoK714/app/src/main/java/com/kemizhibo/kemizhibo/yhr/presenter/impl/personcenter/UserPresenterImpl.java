package com.kemizhibo.kemizhibo.yhr.presenter.impl.personcenter;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenterImpl;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.ChangeUserBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.GetUserBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.UserBean;
import com.kemizhibo.kemizhibo.yhr.interactor.personcenterinteractor.GetChangeUserIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.personcenterinteractor.GetUserIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.personcenterinteractor.UserIteractor;
import com.kemizhibo.kemizhibo.yhr.presenter.personcenterpresenter.GetUserPresenter;
import com.kemizhibo.kemizhibo.yhr.presenter.personcenterpresenter.UserPresenter;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.GetUserView;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.UserView;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe  获取用户信息
 */
public class UserPresenterImpl extends BasePresenterImpl<UserView> implements UserPresenter {
    //注意public全局
    @Inject
    public UserIteractor userIteractor ;

    @Inject
    public UserPresenterImpl() {}

    @Override
    public void userData(BaseActivity activity, String token) {
        userIteractor.loadUserData(activity, new IGetDataDelegate<UserBean>() {
            @Override
            public void getDataSuccess(UserBean userBean) {
                mPresenterView.onUserSuccess(userBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onUserError(errmsg);
            }
        },token);
    }

}
