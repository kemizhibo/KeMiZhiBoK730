package com.kemizhibo.kemizhibo.other.preparing_package_detail.presenter;

import android.util.Log;

import com.kemizhibo.kemizhibo.other.config.Constants;
import com.kemizhibo.kemizhibo.other.config.OkHttpRequest;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.PreparingPackageDetailBean;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.view.PreparingPackageDetailView;
import com.kemizhibo.kemizhibo.other.utils.GsonUtils;
import com.kemizhibo.kemizhibo.other.utils.NetUtils;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/7/31.
 */

public class PreparingPackageDetailPresenterImp implements PreparingPackageDetailPresenter {
    PreparingPackageDetailView detailView;

    public PreparingPackageDetailPresenterImp(PreparingPackageDetailView detailView) {
        this.detailView = detailView;
    }

    @Override
    public void getPreparingPackageDetailData() {
        if(!NetUtils.isConnected(detailView.getCustomContext())){
            return;
        }
        Map map = new HashMap();
        map.put(Constants.COURSE_ID, detailView.getCourseId());
        map.put("dataFrom", "app");
        OkHttpRequest.doGet(detailView.getCustomContext(), OkHttpRequest.attachHttpGetParams(Constants.PREPARING_PACKAGE_DETAIL_URL, map), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                PreparingPackageDetailBean bean = GsonUtils.getBean(response.body().string(), PreparingPackageDetailBean.class);
                if(null != bean && 0 == bean.getCode()){
                    detailView.getPreparingPackageDetailDataSuccess(bean);
                }else{

                }
            }
        });
    }
}
