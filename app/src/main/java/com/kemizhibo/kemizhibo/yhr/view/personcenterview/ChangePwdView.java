package com.kemizhibo.kemizhibo.yhr.view.personcenterview;

import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BaseView;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.ChangePwdBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.SendYanZhengMaBean;

/**
 * Created by 17600 on 2018/5/18.
 */

public interface ChangePwdView extends BaseView {

    void onChangePwdSuccess(ChangePwdBean changePwdBean);
    void onChangePwdError(String msg);

}
