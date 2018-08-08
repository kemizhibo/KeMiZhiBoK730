package com.kemizhibo.kemizhibo.yhr.presenter.personcenterpresenter;

import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenter;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.ChangePwdView;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.SendYanZhengMaView;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe  修改密码
 */
public interface ChangePwdPresenter extends BasePresenter<ChangePwdView> {

    void getChangePwdData(BaseActivity activity, String token,String oldPassword, String newPassword);

}
