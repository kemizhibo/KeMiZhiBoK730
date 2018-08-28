package com.kemizhibo.kemizhibo.yhr.presenter.impl.findpwdimpl;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenterImpl;
import com.kemizhibo.kemizhibo.yhr.bean.findPwdbean.BeforLoginValiDatePhoneBean;
import com.kemizhibo.kemizhibo.yhr.bean.findPwdbean.SendValidateBeforLoginBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.SendYanZhengMaBean;
import com.kemizhibo.kemizhibo.yhr.interactor.findpwdinteractor.BeforLoginValidatePhoneIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.findpwdinteractor.SendValidateBeforLoginIteractor;
import com.kemizhibo.kemizhibo.yhr.presenter.findpwdpresenter.SendValidateBeforLoginPresenter;
import com.kemizhibo.kemizhibo.yhr.view.findpwdview.SendValidateBeforLoginView;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe  登录之前获取验证码
 */
public class SendValidateBeforLoginPresenterImpl extends BasePresenterImpl<SendValidateBeforLoginView> implements SendValidateBeforLoginPresenter {
    //注意public全局
    @Inject
    public SendValidateBeforLoginIteractor sendValidateBeforLoginIteractor ;

    @Inject
    public BeforLoginValidatePhoneIteractor beforLoginValidatePhoneIteractor ;

    @Inject
    public SendValidateBeforLoginPresenterImpl() {}

    @Override
    public void getSendYanZhengMaData(BaseActivity activity, String flag, String mobile) {
        sendValidateBeforLoginIteractor.loadSendValidateBeforLoginData(activity, new IGetDataDelegate<SendValidateBeforLoginBean>() {
            @Override
            public void getDataSuccess(SendValidateBeforLoginBean sendValidateBeforLoginBean) {
                mPresenterView.onSendYanZhengMaSuccess(sendValidateBeforLoginBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onSendYanZhengMaError(errmsg);
            }
        },flag,mobile);
    }

    @Override
    public void getBeforLoginValiDatePhone(BaseActivity activity, String mobile, String code) {
        beforLoginValidatePhoneIteractor.loadBeforLoginValidatePhoneData(activity, new IGetDataDelegate<BeforLoginValiDatePhoneBean>() {
            @Override
            public void getDataSuccess(BeforLoginValiDatePhoneBean beforLoginValiDatePhoneBean) {
                mPresenterView.onBeforLoginValiDatePhoneBeforLoginValiDatePhoneSuccess(beforLoginValiDatePhoneBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onBeforLoginValiDatePhoneError(errmsg);
            }
        },mobile,code);
    }
}
