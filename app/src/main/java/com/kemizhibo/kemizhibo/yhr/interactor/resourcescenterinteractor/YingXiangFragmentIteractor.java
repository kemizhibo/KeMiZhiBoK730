package com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi.YingXiangFragmentApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.YingXiangFragmentBean;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe
 */
public class YingXiangFragmentIteractor {

    private IGetDataDelegate<YingXiangFragmentBean> mDelegate;

    @Inject
    public YingXiangFragmentIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadYingXiangFragmentData(BaseActivity activity, IGetDataDelegate<YingXiangFragmentBean> mDelegate,String sellType,String currentPage,String pageSize,String materialEdition,String subjectId,String semester,String knowledgeId){
        this.mDelegate = mDelegate;
        YingXiangFragmentApi yingXiangFragmentApi = new YingXiangFragmentApi(listener,activity,sellType,currentPage,pageSize,materialEdition,subjectId,semester,knowledgeId);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(yingXiangFragmentApi);
    }

    private HttpOnNextListener<YingXiangFragmentBean> listener = new HttpOnNextListener<YingXiangFragmentBean>() {
        @Override
        public void onNext(YingXiangFragmentBean yingXiangFragmentBean) {
            mDelegate.getDataSuccess(yingXiangFragmentBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
