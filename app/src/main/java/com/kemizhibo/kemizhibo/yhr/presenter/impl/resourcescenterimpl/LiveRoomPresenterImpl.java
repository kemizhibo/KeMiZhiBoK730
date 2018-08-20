package com.kemizhibo.kemizhibo.yhr.presenter.impl.resourcescenterimpl;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenterImpl;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.FilterBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.LiveRoomBean;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.FilterIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.LiveRoomIteractor;
import com.kemizhibo.kemizhibo.yhr.presenter.resourcescenterpresenter.LiveRoomPresenter;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.LiveRoomView;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe
 */
public class LiveRoomPresenterImpl extends BasePresenterImpl<LiveRoomView> implements LiveRoomPresenter {
    //注意public全局
    @Inject
    public FilterIteractor filterIteractor ;

    @Inject
    public LiveRoomIteractor liveRoomIteractor ;

    @Inject
    public LiveRoomPresenterImpl() {}

    @Override
    public void getFilterData(BaseActivity activity) {
        filterIteractor.loadFilterData(activity, new IGetDataDelegate<FilterBean>() {
            @Override
            public void getDataSuccess(FilterBean filterBean) {
                mPresenterView.onFilterSuccess(filterBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onFilterError(errmsg);
            }
        });
    }

    @Override
    public void getLiveRoomData(BaseActivity activity,String token, String sellType, String currentPage, String pageSize, String materialEdition, String subjectId, String semester, String knowledgeId) {
        liveRoomIteractor.loadLiveRoomData(activity, new IGetDataDelegate<LiveRoomBean>() {
            @Override
            public void getDataSuccess(LiveRoomBean liveRoomBean) {
                mPresenterView.onLiveRoomSuccess(liveRoomBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onLiveRoomError(errmsg);
            }
        },token,sellType,currentPage,pageSize,materialEdition,subjectId,semester,knowledgeId);
    }
}
