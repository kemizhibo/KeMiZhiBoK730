package com.kemizhibo.kemizhibo.yhr.presenter.impl.personcenter;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenterImpl;
import com.kemizhibo.kemizhibo.yhr.bean.homepagerbean.SowingMapBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.ChangeUserBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.GetUserBean;
import com.kemizhibo.kemizhibo.yhr.interactor.homeinteractor.SowingMapIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.personcenterinteractor.GetChangeUserIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.personcenterinteractor.GetUserIteractor;
import com.kemizhibo.kemizhibo.yhr.presenter.homepagerpresenter.SowingMapPresenter;
import com.kemizhibo.kemizhibo.yhr.presenter.personcenterpresenter.GetUserPresenter;
import com.kemizhibo.kemizhibo.yhr.view.homepagerview.SowingMapView;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.GetUserView;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe  获取用户信息
 */
public class GetUserPresenterImpl extends BasePresenterImpl<GetUserView> implements GetUserPresenter {
    //注意public全局
    @Inject
    public GetUserIteractor getUserIteractor ;
    @Inject
    public GetChangeUserIteractor getChangeUserIteractor ;

    @Inject
    public GetUserPresenterImpl() {}

    @Override
    public void getUserData(BaseActivity activity, String token) {
        getUserIteractor.loadGetUserData(activity, new IGetDataDelegate<GetUserBean>() {
            @Override
            public void getDataSuccess(GetUserBean getUserBean) {
                mPresenterView.onUserSuccess(getUserBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onUserError(errmsg);
            }
        },token);
    }

    @Override
    public void getChangeUserData(BaseActivity activity, String token, String school, String realName, String grade, String subject, String idCardNo, String email, String address) {
        getChangeUserIteractor.loadGetChangeUserData(activity, new IGetDataDelegate<ChangeUserBean>() {
            @Override
            public void getDataSuccess(ChangeUserBean changeUserBean) {
                mPresenterView.onChangeUserSuccess(changeUserBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onChangeUserError(errmsg);
            }
        },token,school,realName,grade,subject,idCardNo,email,address);
    }
}
