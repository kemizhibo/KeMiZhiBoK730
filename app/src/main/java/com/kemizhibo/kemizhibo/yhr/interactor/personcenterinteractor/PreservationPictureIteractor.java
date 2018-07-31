package com.kemizhibo.kemizhibo.yhr.interactor.personcenterinteractor;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.personcenterapi.GetUserApi;
import com.kemizhibo.kemizhibo.yhr.api.personcenterapi.PreservationPictureApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.GetUserBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.PreservationPictureBean;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe   获取头像
 */
public class PreservationPictureIteractor {

    private IGetDataDelegate<PreservationPictureBean> mDelegate;

    @Inject
    public PreservationPictureIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadPreservationPictureData(BaseActivity activity, IGetDataDelegate<PreservationPictureBean> mDelegate,String token,String picImg){
        this.mDelegate = mDelegate;
        PreservationPictureApi preservationPictureApi = new PreservationPictureApi(listener,activity,token,picImg);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(preservationPictureApi);
    }

    private HttpOnNextListener<PreservationPictureBean> listener = new HttpOnNextListener<PreservationPictureBean>() {
        @Override
        public void onNext(PreservationPictureBean preservationPictureBean) {
            mDelegate.getDataSuccess(preservationPictureBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
