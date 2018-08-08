package com.kemizhibo.kemizhibo.yhr.presenter.impl.personcenter;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenterImpl;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.ChangePwdBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.SendYanZhengMaBean;
import com.kemizhibo.kemizhibo.yhr.interactor.personcenterinteractor.ChangePwdIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.personcenterinteractor.NewPhoneIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.personcenterinteractor.OldPhoneIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.personcenterinteractor.SendYanZhengMaIteractor;
import com.kemizhibo.kemizhibo.yhr.presenter.personcenterpresenter.ChangePwdPresenter;
import com.kemizhibo.kemizhibo.yhr.presenter.personcenterpresenter.SendYanZhengMaPresenter;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.ChangePwdView;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.SendYanZhengMaView;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe  修改密码
 */
public class ChangePwdPresenterImpl extends BasePresenterImpl<ChangePwdView> implements ChangePwdPresenter {
    //注意public全局
    @Inject
    public ChangePwdIteractor changePwdIteractor ;

    @Inject
    public ChangePwdPresenterImpl() {}


    @Override
    public void getChangePwdData(BaseActivity activity, String token, String oldPassword, String newPassword) {
        changePwdIteractor.loadNewPhoneData(activity, new IGetDataDelegate<ChangePwdBean>() {
            @Override
            public void getDataSuccess(ChangePwdBean changePwdBean) {
                mPresenterView.onChangePwdSuccess(changePwdBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onChangePwdError(errmsg);
            }
        },token,oldPassword,newPassword);
    }
}
