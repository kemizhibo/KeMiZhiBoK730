package com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor;

import android.content.Context;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi.LiveRoomDetailsApi;
import com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi.PictureApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.LiveRoomDetailsBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.PictureBean;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe
 */
public class PictureIteractor {

    private IGetDataDelegate<PictureBean> mDelegate;
    private Context context;

    @Inject
    public PictureIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadPictureData(BaseActivity activity, IGetDataDelegate<PictureBean> mDelegate,String token,String courseId){
        this.mDelegate = mDelegate;
        PictureApi pictureApi = new PictureApi(listener,activity,token,courseId);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(pictureApi);
    }

    private HttpOnNextListener<PictureBean> listener = new HttpOnNextListener<PictureBean>() {
        @Override
        public void onNext(PictureBean pictureBean) {
            mDelegate.getDataSuccess(pictureBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
