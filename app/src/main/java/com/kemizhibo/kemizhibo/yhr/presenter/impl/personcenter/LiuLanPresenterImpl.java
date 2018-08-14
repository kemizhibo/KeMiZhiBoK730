package com.kemizhibo.kemizhibo.yhr.presenter.impl.personcenter;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenterImpl;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.ChangePwdBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.ClearLiuLanBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.LiuLanBean;
import com.kemizhibo.kemizhibo.yhr.interactor.personcenterinteractor.ChangePwdIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.personcenterinteractor.ClearLiuLanIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.personcenterinteractor.ClearOneOrMoreLiuLanIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.personcenterinteractor.LiuLanIteractor;
import com.kemizhibo.kemizhibo.yhr.presenter.personcenterpresenter.ChangePwdPresenter;
import com.kemizhibo.kemizhibo.yhr.presenter.personcenterpresenter.LiuLanPresenter;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.ChangePwdView;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.LiuLanView;

import java.util.List;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe  浏览记录
 */
public class LiuLanPresenterImpl extends BasePresenterImpl<LiuLanView> implements LiuLanPresenter {
    //注意public全局
    @Inject
    public LiuLanIteractor liuLanIteractor ;
    @Inject
    public ClearLiuLanIteractor clearLiuLanIteractor ;
    @Inject
    public ClearOneOrMoreLiuLanIteractor clearOneOrMoreLiuLanIteractor ;

    @Inject
    public LiuLanPresenterImpl() {}


    @Override
    public void getLiuLanData(BaseActivity activity, String token, String page, String size) {
        liuLanIteractor.loadLiuLanData(activity, new IGetDataDelegate<LiuLanBean>() {
            @Override
            public void getDataSuccess(LiuLanBean liuLanBean) {
                mPresenterView.onLiuLanSuccess(liuLanBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onLiuLanError(errmsg);
            }
        },token,page,size);
    }

    @Override
    public void getClearLiuLanData(BaseActivity activity, String token) {
        clearLiuLanIteractor.loadClearLiuLanData(activity, new IGetDataDelegate<ClearLiuLanBean>() {
            @Override
            public void getDataSuccess(ClearLiuLanBean clearLiuLanBean) {
                mPresenterView.onClearLiuLanSuccess(clearLiuLanBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onClearLiuLanError(errmsg);
            }
        },token);
    }

    @Override
    public void getClearOneOrMoreLiuLanData(BaseActivity activity, String token, List ids) {
        clearOneOrMoreLiuLanIteractor.loadClearOneOrMoreLiuLanData(activity, new IGetDataDelegate<ClearLiuLanBean>() {
            @Override
            public void getDataSuccess(ClearLiuLanBean clearLiuLanBean) {
                mPresenterView.onClearOneOrMoreLiuLanSuccess(clearLiuLanBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onClearOneOrMoreLiuLanError(errmsg);
            }
        },token,ids);
    }
}
