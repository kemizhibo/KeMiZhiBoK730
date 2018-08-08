package com.kemizhibo.kemizhibo.yhr.interactor.personcenterinteractor;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.personcenterapi.PreservationPictureApi;
import com.kemizhibo.kemizhibo.yhr.api.personcenterapi.TakePhotoApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.PreservationPictureBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.TakePhotoBean;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import java.io.File;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe   上传头像
 */
public class TakePhotoIteractor {

    private IGetDataDelegate<TakePhotoBean> mDelegate;

    @Inject
    public TakePhotoIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadTakePhotoData(BaseActivity activity, IGetDataDelegate<TakePhotoBean> mDelegate, String token, File uploadfile,String param){
        this.mDelegate = mDelegate;
        TakePhotoApi takePhotoApi = new TakePhotoApi(listener,activity,token,uploadfile,param);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(takePhotoApi);
    }

    private HttpOnNextListener<TakePhotoBean> listener = new HttpOnNextListener<TakePhotoBean>() {
        @Override
        public void onNext(TakePhotoBean takePhotoBean) {
            mDelegate.getDataSuccess(takePhotoBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
