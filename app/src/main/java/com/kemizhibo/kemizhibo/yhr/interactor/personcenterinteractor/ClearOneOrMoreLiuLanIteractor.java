package com.kemizhibo.kemizhibo.yhr.interactor.personcenterinteractor;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.personcenterapi.ClearLiuLanApi;
import com.kemizhibo.kemizhibo.yhr.api.personcenterapi.ClearOneOrMoreLiuLanApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.ClearLiuLanBean;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import java.util.List;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe   删除一个或者多个浏览记录
 */
public class ClearOneOrMoreLiuLanIteractor {

    private IGetDataDelegate<ClearLiuLanBean> mDelegate;

    @Inject
    public ClearOneOrMoreLiuLanIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadClearOneOrMoreLiuLanData(BaseActivity activity, IGetDataDelegate<ClearLiuLanBean> mDelegate, String token, List ids){
        this.mDelegate = mDelegate;
        ClearOneOrMoreLiuLanApi clearOneOrMoreLiuLanApi = new ClearOneOrMoreLiuLanApi(listener,activity,token,ids);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(clearOneOrMoreLiuLanApi);
    }

    private HttpOnNextListener<ClearLiuLanBean> listener = new HttpOnNextListener<ClearLiuLanBean>() {
        @Override
        public void onNext(ClearLiuLanBean clearLiuLanBean) {
            mDelegate.getDataSuccess(clearLiuLanBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
