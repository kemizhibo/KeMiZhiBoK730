package com.kemizhibo.kemizhibo.yhr.presenter.impl.personcenter;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenterImpl;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.ChangeUserBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.FeedBackBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.GetUserBean;
import com.kemizhibo.kemizhibo.yhr.interactor.personcenterinteractor.FeedBackIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.personcenterinteractor.GetChangeUserIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.personcenterinteractor.GetUserIteractor;
import com.kemizhibo.kemizhibo.yhr.presenter.personcenterpresenter.FeedBackPresenter;
import com.kemizhibo.kemizhibo.yhr.presenter.personcenterpresenter.GetUserPresenter;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.FeedBackView;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.GetUserView;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe  fankui
 */
public class FeedBackPresenterImpl extends BasePresenterImpl<FeedBackView> implements FeedBackPresenter {
    //注意public全局
    @Inject
    public FeedBackIteractor feedBackIteractor ;

    @Inject
    public FeedBackPresenterImpl() {}

    @Override
    public void getFeedBackData(BaseActivity activity, String token,String content,String type) {
        feedBackIteractor.loadFeedBackData(activity, new IGetDataDelegate<FeedBackBean>() {
            @Override
            public void getDataSuccess(FeedBackBean feedBackBean) {
                mPresenterView.onFeedBackSuccess(feedBackBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onFeedBackError(errmsg);
            }
        },token,content,type);
    }
}
