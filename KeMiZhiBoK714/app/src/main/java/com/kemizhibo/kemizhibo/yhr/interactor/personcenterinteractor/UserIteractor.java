package com.kemizhibo.kemizhibo.yhr.interactor.personcenterinteractor;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.personcenterapi.GetUserApi;
import com.kemizhibo.kemizhibo.yhr.api.personcenterapi.UserApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.GetUserBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.UserBean;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe   获取用户信息
 */
public class UserIteractor {

    private IGetDataDelegate<UserBean> mDelegate;

    @Inject
    public UserIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadUserData(BaseActivity activity, IGetDataDelegate<UserBean> mDelegate,String token){
        this.mDelegate = mDelegate;
        UserApi userApi = new UserApi(listener,activity,token);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(userApi);
    }

    private HttpOnNextListener<UserBean> listener = new HttpOnNextListener<UserBean>() {
        @Override
        public void onNext(UserBean userBean) {
            mDelegate.getDataSuccess(userBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
