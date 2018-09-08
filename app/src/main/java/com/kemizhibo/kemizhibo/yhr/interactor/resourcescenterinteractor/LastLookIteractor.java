package com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.personcenterapi.GetOneLookApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.OneLookBean;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe  播放结束位置
 */
public class LastLookIteractor {

    private IGetDataDelegate<OneLookBean> mDelegate;

    @Inject
    public LastLookIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadLastLookData(BaseActivity activity, IGetDataDelegate<OneLookBean> mDelegate, String token, String playPosition,String keyId, String courseId, String watchTime, String isEnd){
        this.mDelegate = mDelegate;
        GetOneLookApi getOneLookApi = new GetOneLookApi(listener,activity,token,playPosition,keyId,courseId,watchTime,isEnd);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(getOneLookApi);
    }

    private HttpOnNextListener<OneLookBean> listener = new HttpOnNextListener<OneLookBean>() {
        @Override
        public void onNext(OneLookBean oneLookBean) {
            mDelegate.getDataSuccess(oneLookBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
