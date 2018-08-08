package com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor;

import android.content.Context;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.personcenterapi.GetOneLookApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.LiuLanBean;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe  第一次播放位置
 */
public class OneLookIteractor {

    private IGetDataDelegate<LiuLanBean> mDelegate;
    private Context context;

    @Inject
    public OneLookIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadOneLookData(BaseActivity activity, IGetDataDelegate<LiuLanBean> mDelegate, String token, String playPosition, String courseId, String watchTime, String isEnd){
        this.mDelegate = mDelegate;
        GetOneLookApi getOneLookApi = new GetOneLookApi(listener,activity,token,playPosition,courseId,watchTime,isEnd);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(getOneLookApi);
    }

    private HttpOnNextListener<LiuLanBean> listener = new HttpOnNextListener<LiuLanBean>() {
        @Override
        public void onNext(LiuLanBean liuLanBean) {
            mDelegate.getDataSuccess(liuLanBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
