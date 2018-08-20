package com.kemizhibo.kemizhibo.yhr.presenter.impl.resourcescenterimpl;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenterImpl;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.SearchBean;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.SearchIteractor;
import com.kemizhibo.kemizhibo.yhr.presenter.resourcescenterpresenter.SearchPresenter;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.SearchIView;
import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe
 */
public class SearchPresenterImpl extends BasePresenterImpl<SearchIView> implements SearchPresenter {
    //注意public全局
    @Inject
    public SearchIteractor searchIteractor ;

    @Inject
    public SearchPresenterImpl() {}

    @Override
    public void getSearchData(BaseActivity activity,String token, String sellType, String currentPage, String pageSize, String courseName) {
        searchIteractor.loadSearchData(activity, new IGetDataDelegate<SearchBean>() {
            @Override
            public void getDataSuccess(SearchBean searchBean) {
                mPresenterView.onSearchSuccess(searchBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onSearchError(errmsg);
            }
        },token,sellType,currentPage,pageSize,courseName);
    }
}
