package com.kemizhibo.kemizhibo.yhr.presenter.impl.personcenter;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenterImpl;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.ChangeUserBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.CollectionBoxBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.GetUserBean;
import com.kemizhibo.kemizhibo.yhr.interactor.personcenterinteractor.CollectionBoxIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.personcenterinteractor.GetChangeUserIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.personcenterinteractor.GetUserIteractor;
import com.kemizhibo.kemizhibo.yhr.presenter.personcenterpresenter.CollectionBoxPresenter;
import com.kemizhibo.kemizhibo.yhr.presenter.personcenterpresenter.GetUserPresenter;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.CollectionBoxView;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.GetUserView;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe  获取书藏家
 */
public class CollectionBoxPresenterImpl extends BasePresenterImpl<CollectionBoxView> implements CollectionBoxPresenter {
    //注意public全局
    @Inject
    public CollectionBoxIteractor collectionBoxIteractor ;

    @Inject
    public CollectionBoxPresenterImpl() {}

    @Override
    public void getCollectionBoxData(BaseActivity activity, String token,String page,String size) {
        collectionBoxIteractor.loadCollectionBoxData(activity, new IGetDataDelegate<CollectionBoxBean>() {
            @Override
            public void getDataSuccess(CollectionBoxBean collectionBoxBean) {
                mPresenterView.onCollectionBoxSuccess(collectionBoxBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onCollectionBoxError(errmsg);
            }
        },token,page,size);
    }
}
