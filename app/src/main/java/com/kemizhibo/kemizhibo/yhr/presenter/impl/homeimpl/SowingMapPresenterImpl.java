package com.kemizhibo.kemizhibo.yhr.presenter.impl.homeimpl;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenterImpl;
import com.kemizhibo.kemizhibo.yhr.bean.homepagerbean.SowingMapBean;
import com.kemizhibo.kemizhibo.yhr.bean.homepagerbean.VersionInformationBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.SearchBean;
import com.kemizhibo.kemizhibo.yhr.interactor.homeinteractor.SowingMapIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.homeinteractor.VersionInformationIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.SearchIteractor;
import com.kemizhibo.kemizhibo.yhr.presenter.homepagerpresenter.SowingMapPresenter;
import com.kemizhibo.kemizhibo.yhr.presenter.resourcescenterpresenter.SearchPresenter;
import com.kemizhibo.kemizhibo.yhr.view.homepagerview.SowingMapView;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.SearchIView;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe  轮播
 */
public class SowingMapPresenterImpl extends BasePresenterImpl<SowingMapView> implements SowingMapPresenter {
    //注意public全局
    @Inject
    public SowingMapIteractor sowingMapIteractor ;



    @Inject
    public SowingMapPresenterImpl() {}

    @Override
    public void getSowingMapData(BaseActivity activity,String token, String device) {
        sowingMapIteractor.loadSowingMapData(activity, new IGetDataDelegate<SowingMapBean>() {
            @Override
            public void getDataSuccess(SowingMapBean sowingMapBean) {
                mPresenterView.onSowingMapSuccess(sowingMapBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onSowingMapError(errmsg);
            }
        },token,device);
    }

}
