package com.kemizhibo.kemizhibo.yhr.presenter.impl.resourcescenterimpl;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenterImpl;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.ForTeachSearchBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.SearchBean;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.ForTeachSearchIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.SearchIteractor;
import com.kemizhibo.kemizhibo.yhr.presenter.resourcescenterpresenter.ForTeachSearchPresenter;
import com.kemizhibo.kemizhibo.yhr.presenter.resourcescenterpresenter.SearchPresenter;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.ForeTeachSearchIView;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.SearchIView;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe  备授课搜索
 */
public class ForTeachSearchPresenterImpl extends BasePresenterImpl<ForeTeachSearchIView> implements ForTeachSearchPresenter {
    //注意public全局
    @Inject
    public ForTeachSearchIteractor forTeachSearchIteractor ;

    @Inject
    public ForTeachSearchPresenterImpl() {}


    @Override
    public void getForTeachSearchData(BaseActivity activity, String token, String keyWord, String pageSize, String currentPage) {
        forTeachSearchIteractor.loadForTeachSearchData(activity, new IGetDataDelegate<ForTeachSearchBean>() {
            @Override
            public void getDataSuccess(ForTeachSearchBean forTeachSearchBean) {
                mPresenterView.onForeTeachSearchSuccess(forTeachSearchBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onForeTeachSearchError(errmsg);
            }
        },token,keyWord,pageSize,currentPage);
    }
}
