package com.kemizhibo.kemizhibo.yhr.presenter.personcenterpresenter;

import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenter;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.CollectionBoxView;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.GetUserView;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe  获取收藏夹
 */
public interface CollectionBoxPresenter extends BasePresenter<CollectionBoxView> {

    void getCollectionBoxData(BaseActivity activity, String token,String page,String size);
}
