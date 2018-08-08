package com.kemizhibo.kemizhibo.yhr.interactor.personcenterinteractor;

import android.content.Context;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.personcenterapi.LiuLanApi;
import com.kemizhibo.kemizhibo.yhr.api.personcenterapi.NewPhoneApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.LiuLanBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.SendYanZhengMaBean;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe   浏览记录
 */
public class LiuLanIteractor {

    private IGetDataDelegate<LiuLanBean> mDelegate;

    @Inject
    public LiuLanIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadLiuLanData(BaseActivity activity, IGetDataDelegate<LiuLanBean> mDelegate,String token,String page,String size){
        this.mDelegate = mDelegate;
        LiuLanApi liuLanApi = new LiuLanApi(listener,activity,token,page,size);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(liuLanApi);
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
