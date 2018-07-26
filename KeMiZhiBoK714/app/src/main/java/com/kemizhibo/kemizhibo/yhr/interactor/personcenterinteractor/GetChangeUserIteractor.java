package com.kemizhibo.kemizhibo.yhr.interactor.personcenterinteractor;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.personcenterapi.GetChangeUserApi;
import com.kemizhibo.kemizhibo.yhr.api.personcenterapi.GetUserApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.ChangeUserBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.GetUserBean;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe   更改用户信息
 */
public class GetChangeUserIteractor {

    private IGetDataDelegate<ChangeUserBean> mDelegate;

    @Inject
    public GetChangeUserIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadGetChangeUserData(BaseActivity activity, IGetDataDelegate<ChangeUserBean> mDelegate,String token,String school,String realName,String grade,String subject,String idCardNo,String email,String address){
        this.mDelegate = mDelegate;
        GetChangeUserApi getChangeUserApi = new GetChangeUserApi(listener,activity,token,school,realName,grade,subject,idCardNo,email,address);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(getChangeUserApi);
    }

    private HttpOnNextListener<ChangeUserBean> listener = new HttpOnNextListener<ChangeUserBean>() {
        @Override
        public void onNext(ChangeUserBean changeUserBean) {
            mDelegate.getDataSuccess(changeUserBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
