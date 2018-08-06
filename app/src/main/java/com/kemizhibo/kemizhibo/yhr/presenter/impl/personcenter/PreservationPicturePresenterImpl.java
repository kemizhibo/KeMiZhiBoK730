package com.kemizhibo.kemizhibo.yhr.presenter.impl.personcenter;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenterImpl;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.ChangeUserBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.GetUserBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.PreservationPictureBean;
import com.kemizhibo.kemizhibo.yhr.interactor.personcenterinteractor.GetChangeUserIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.personcenterinteractor.GetUserIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.personcenterinteractor.PreservationPictureIteractor;
import com.kemizhibo.kemizhibo.yhr.presenter.personcenterpresenter.GetUserPresenter;
import com.kemizhibo.kemizhibo.yhr.presenter.personcenterpresenter.PreservationPicturePresenter;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.GetUserView;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.PreservationPictureView;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe  保存头像
 */
public class PreservationPicturePresenterImpl extends BasePresenterImpl<PreservationPictureView> implements PreservationPicturePresenter {
    //注意public全局
    @Inject
    public PreservationPictureIteractor preservationPictureIteractor ;

    @Inject
    public PreservationPicturePresenterImpl() {}

    @Override
    public void getPreservationPictureData(BaseActivity activity, String token, String picImg) {
        preservationPictureIteractor.loadPreservationPictureData(activity, new IGetDataDelegate<PreservationPictureBean>() {
            @Override
            public void getDataSuccess(PreservationPictureBean preservationPictureBean) {
                mPresenterView.onPreservationPictureSuccess(preservationPictureBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onPreservationPictureError(errmsg);
            }
        },token,picImg);
    }
}