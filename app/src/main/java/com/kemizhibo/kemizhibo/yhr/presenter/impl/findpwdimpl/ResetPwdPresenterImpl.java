package com.kemizhibo.kemizhibo.yhr.presenter.impl.findpwdimpl;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenterImpl;
import com.kemizhibo.kemizhibo.yhr.bean.findPwdbean.BeforLoginValiDatePhoneBean;
import com.kemizhibo.kemizhibo.yhr.bean.findPwdbean.ResetPwdBean;
import com.kemizhibo.kemizhibo.yhr.bean.findPwdbean.SendValidateBeforLoginBean;
import com.kemizhibo.kemizhibo.yhr.interactor.findpwdinteractor.BeforLoginValidatePhoneIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.findpwdinteractor.ResetPwdIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.findpwdinteractor.SendValidateBeforLoginIteractor;
import com.kemizhibo.kemizhibo.yhr.presenter.findpwdpresenter.ResetPwdPresenter;
import com.kemizhibo.kemizhibo.yhr.presenter.findpwdpresenter.SendValidateBeforLoginPresenter;
import com.kemizhibo.kemizhibo.yhr.view.findpwdview.ResetPwdView;
import com.kemizhibo.kemizhibo.yhr.view.findpwdview.SendValidateBeforLoginView;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe  重置密码
 */
public class ResetPwdPresenterImpl extends BasePresenterImpl<ResetPwdView> implements ResetPwdPresenter {
    //注意public全局
    @Inject
    public ResetPwdIteractor resetPwdIteractor ;

    @Inject
    public ResetPwdPresenterImpl() {}


    @Override
    public void getResetPwdData(BaseActivity activity, String token, String password) {
        resetPwdIteractor.loadResetPwdData(activity, new IGetDataDelegate<ResetPwdBean>() {
            @Override
            public void getDataSuccess(ResetPwdBean resetPwdBean) {
                mPresenterView.onResetPwdSuccess(resetPwdBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onResetPwdError(errmsg);
            }
        },token,password);
    }
}
