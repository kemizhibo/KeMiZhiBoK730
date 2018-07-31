package com.kemizhibo.kemizhibo.yhr.presenter.impl.resourcescenterimpl;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenterImpl;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.CollectionBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.FilterBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.PictureBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.TeacherTrainingBean;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.CollectionIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.FilterIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.PictureIteractor;
import com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor.TeacherTrainingIteractor;
import com.kemizhibo.kemizhibo.yhr.presenter.resourcescenterpresenter.PicturePresenter;
import com.kemizhibo.kemizhibo.yhr.presenter.resourcescenterpresenter.TeacherTrainingPresenter;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.PictureView;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.TeacherTrainingView;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: on 2018/7/3.
 * Describe: 图文
 */

public class PicturePresenterImpl extends BasePresenterImpl<PictureView> implements PicturePresenter {
    //注意public全局
    @Inject
    public PictureIteractor pictureIteractor ;

    @Inject
    public CollectionIteractor collectionIteractor ;

    @Inject
    public PicturePresenterImpl() {}

    @Override
    public void getPictureData(BaseActivity activity, String token,String courseId) {
        pictureIteractor.loadPictureData(activity, new IGetDataDelegate<PictureBean>() {
            @Override
            public void getDataSuccess(PictureBean pictureBean) {
                mPresenterView.onPictureSuccess(pictureBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onPictureError(errmsg);
            }
        },token,courseId);
    }

    @Override
    public void getCollectionData(BaseActivity activity, String token, String courseId) {
        collectionIteractor.loadCollectionData(activity, new IGetDataDelegate<CollectionBean>() {
            @Override
            public void getDataSuccess(CollectionBean collectionBean) {
                mPresenterView.onGetCollectionSuccess(collectionBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onGetCollectionError(errmsg);
            }
        },token,courseId);
    }
}
