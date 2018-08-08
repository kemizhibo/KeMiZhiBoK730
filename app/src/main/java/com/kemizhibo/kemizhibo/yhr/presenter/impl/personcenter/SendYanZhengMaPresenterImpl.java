package com.kemizhibo.kemizhibo.yhr.presenter.impl.personcenter;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenterImpl;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.FeedBackBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.SendYanZhengMaBean;
import com.kemizhibo.kemizhibo.yhr.interactor.personcenterinteractor.FeedBackIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.personcenterinteractor.NewPhoneIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.personcenterinteractor.OldPhoneIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.personcenterinteractor.SendYanZhengMaIteractor;
import com.kemizhibo.kemizhibo.yhr.presenter.personcenterpresenter.FeedBackPresenter;
import com.kemizhibo.kemizhibo.yhr.presenter.personcenterpresenter.SendYanZhengMaPresenter;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.FeedBackView;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.SendYanZhengMaView;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe  获取验证码
 */
public class SendYanZhengMaPresenterImpl extends BasePresenterImpl<SendYanZhengMaView> implements SendYanZhengMaPresenter {
    //注意public全局
    @Inject
    public SendYanZhengMaIteractor sendYanZhengMaIteractor ;
    @Inject
    public OldPhoneIteractor oldPhoneIteractor ;
    @Inject
    public NewPhoneIteractor newPhoneIteractor ;

    @Inject
    public SendYanZhengMaPresenterImpl() {}

    @Override
    public void getSendYanZhengMaData(BaseActivity activity,String flag, String token,String mobile) {
        sendYanZhengMaIteractor.loadSendYanZhengMaData(activity, new IGetDataDelegate<SendYanZhengMaBean>() {
            @Override
            public void getDataSuccess(SendYanZhengMaBean sendYanZhengMaBean) {
                mPresenterView.onSendYanZhengMaSuccess(sendYanZhengMaBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onSendYanZhengMaError(errmsg);
            }
        },flag,token,mobile);
    }

    @Override
    public void getOldPhoneData(BaseActivity activity, String token, String mobile, String code) {
        oldPhoneIteractor.loadOldPhoneData(activity, new IGetDataDelegate<SendYanZhengMaBean>() {
            @Override
            public void getDataSuccess(SendYanZhengMaBean sendYanZhengMaBean) {
                mPresenterView.onOldPhoneSuccess(sendYanZhengMaBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onOldPhoneError(errmsg);
            }
        },token,mobile,code);
    }

    @Override
    public void getNewPhoneData(BaseActivity activity, String token, String mobile, String code) {
        newPhoneIteractor.loadNewPhoneData(activity, new IGetDataDelegate<SendYanZhengMaBean>() {
            @Override
            public void getDataSuccess(SendYanZhengMaBean sendYanZhengMaBean) {
                mPresenterView.onNewPhoneSuccess(sendYanZhengMaBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onNewPhoneError(errmsg);
            }
        },token,mobile,code);
    }
}
