package com.kemizhibo.kemizhibo.yhr.presenter.impl.resourcescenterimpl;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenterImpl;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.FilterBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.YingXiangFragmentBean;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.FilterIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.YingXiangFragmentIteractor;
import com.kemizhibo.kemizhibo.yhr.presenter.resourcescenterpresenter.FilterPresenter;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.FilterView;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe
 */
public class FilterPresenterImpl extends BasePresenterImpl<FilterView> implements FilterPresenter {
    //注意public全局
    @Inject
    public FilterIteractor filterIteractor ;

    @Inject
    public YingXiangFragmentIteractor yingXiangFragmentIteractor ;

    @Inject
    public FilterPresenterImpl() {}



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
    public void getYingXiangFragmentData(BaseActivity activity,String sellType, String currentPage,String pageSize, String materialEdition,String subjectId, String semester,String knowledgeId) {
        yingXiangFragmentIteractor.loadYingXiangFragmentData(activity, new IGetDataDelegate<YingXiangFragmentBean>() {
            @Override
            public void getDataSuccess(YingXiangFragmentBean yingXiangFragmentBean) {
                mPresenterView.onYingXiangFragmentSuccess(yingXiangFragmentBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onYingXiangFragmentError(errmsg);
            }
        },sellType,currentPage,pageSize,materialEdition,subjectId,semester,knowledgeId);
    }

}
