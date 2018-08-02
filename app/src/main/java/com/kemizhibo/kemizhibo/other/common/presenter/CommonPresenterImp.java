package com.kemizhibo.kemizhibo.other.common.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.kemizhibo.kemizhibo.other.common.bean.CommonFilterBean;
import com.kemizhibo.kemizhibo.other.common.bean.CommonUserInfoBean;
import com.kemizhibo.kemizhibo.other.common.view.CommonView;
import com.kemizhibo.kemizhibo.other.config.Constants;
import com.kemizhibo.kemizhibo.other.config.OkHttpRequest;
import com.kemizhibo.kemizhibo.other.utils.GsonUtils;
import com.kemizhibo.kemizhibo.other.utils.NetUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/7/30.
 */

public class CommonPresenterImp implements CommonPresenter {
    private CommonView commonView;

    public CommonPresenterImp(CommonView commonView) {
        this.commonView = commonView;
    }

    @Override
    public void getCommonFilterData() {
        if(!NetUtils.isConnected(commonView.getCommonCustomContext())){
            commonView.getCommonFilterError(Constants.NET_ERROR_CODE);
            return;
        }
        OkHttpRequest.doGet(Constants.COMMON_FILTER_URL, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                commonView.getCommonFilterError(Constants.REQUEST_ERROR_CODE);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                CommonFilterBean bean = GsonUtils.getBean(response.body().string(), CommonFilterBean.class);
                if(null != bean && 0 == bean.getCode()){
                    commonView.getCommonFilterSuccess(bean);
                }else{
                    commonView.getCommonFilterError(Constants.REQUEST_ERROR_CODE);
                }
            }
        });
    }

    @Override
    public void getUserInfo() {
        if(!NetUtils.isConnected(commonView.getCommonCustomContext())){
            commonView.getCommonUserInfoError(Constants.NET_ERROR_CODE);
            return;
        }
        SharedPreferences sp = commonView.getCommonCustomContext().getSharedPreferences("logintoken", Context.MODE_PRIVATE);
        String token = sp.getString("token", "");
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .get()
                .url(Constants.GET_USER_INFO)
                .addHeader("Authorization", "Bearer" + " " + token)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                commonView.getCommonUserInfoError(Constants.REQUEST_ERROR_CODE);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                CommonUserInfoBean bean = GsonUtils.getBean(response.body().string(), CommonUserInfoBean.class);
                if(null != bean && 0 == bean.getCode()){
                    commonView.getCommonUserInfoSuccess(bean);
                }else{
                    commonView.getCommonUserInfoError(Constants.REQUEST_ERROR_CODE);
                }
            }
        });
    }
}
