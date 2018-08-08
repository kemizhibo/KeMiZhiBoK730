package com.kemizhibo.kemizhibo.yhr.presenter.impl.personcenter;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenterImpl;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.ChangePwdBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.SignOutBean;
import com.kemizhibo.kemizhibo.yhr.interactor.personcenterinteractor.ChangePwdIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.personcenterinteractor.SignOutIteractor;
import com.kemizhibo.kemizhibo.yhr.presenter.personcenterpresenter.ChangePwdPresenter;
import com.kemizhibo.kemizhibo.yhr.presenter.personcenterpresenter.SignOutPresenter;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.ChangePwdView;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.SignOutView;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe  退出登录
 */
public class SignOutPresenterImpl extends BasePresenterImpl<SignOutView> implements SignOutPresenter {
    //注意public全局
    @Inject
    public SignOutIteractor signOutIteractor ;

    @Inject
    public SignOutPresenterImpl() {}

    @Override
    public void getSignOutData(BaseActivity activity, String token) {
        signOutIteractor.loadSignOutData(activity, new IGetDataDelegate<SignOutBean>() {
            @Override
            public void getDataSuccess(SignOutBean signOutBean) {
                mPresenterView.onSignOutSuccess(signOutBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onSignOutError(errmsg);
            }
        },token);
    }
}
