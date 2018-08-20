package com.kemizhibo.kemizhibo.yhr.presenter.impl;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenterImpl;
import com.kemizhibo.kemizhibo.yhr.bean.LectureBean;
import com.kemizhibo.kemizhibo.yhr.interactor.LectureIteractor;
import com.kemizhibo.kemizhibo.yhr.presenter.LecturePresenter;
import com.kemizhibo.kemizhibo.yhr.view.LectureView;
import javax.inject.Inject;

/**
 * Author: yhr
 * Date: on 2018/7/3.
 * Describe:完成授课
 */

public class LecturePresenterImpl extends BasePresenterImpl<LectureView> implements LecturePresenter {
    //注意public全局
    @Inject
    public LectureIteractor lectureIteractor;


    @Inject
    public LecturePresenterImpl() {}


    @Override
    public void getLectureData(BaseActivity activity, String token, String moduleId, String kemiVideoPlan) {
        lectureIteractor.loadLectureData(activity, new IGetDataDelegate<LectureBean>() {
            @Override
            public void getDataSuccess(LectureBean lectureBean) {
                mPresenterView.onLectureSuccess(lectureBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onLectureError(errmsg);
            }
        },token,moduleId,kemiVideoPlan);
    }
}
