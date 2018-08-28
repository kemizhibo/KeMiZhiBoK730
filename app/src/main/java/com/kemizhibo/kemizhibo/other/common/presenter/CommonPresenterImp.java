package com.kemizhibo.kemizhibo.other.common.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.kemizhibo.kemizhibo.other.common.bean.CommonFilterBean;
import com.kemizhibo.kemizhibo.other.common.bean.CommonTeacherBean;
import com.kemizhibo.kemizhibo.other.common.bean.CommonUserInfoBean;
import com.kemizhibo.kemizhibo.other.common.bean.CommonUserTeachPlanBean;
import com.kemizhibo.kemizhibo.other.common.view.CommonView;
import com.kemizhibo.kemizhibo.other.config.Constants;
import com.kemizhibo.kemizhibo.other.config.OkHttpRequest;
import com.kemizhibo.kemizhibo.other.load.LoadFailUtil;
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
        OkHttpRequest.doGet(commonView.getCommonCustomContext(), Constants.COMMON_FILTER_URL, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                commonView.getCommonFilterError(Constants.REQUEST_ERROR_CODE);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                CommonFilterBean bean = GsonUtils.getBean(response.body().string(), CommonFilterBean.class);
                if(null != bean && 0 == bean.getCode()){
                    commonView.getCommonFilterSuccess(bean);
                }else if(null != bean && 0 != bean.getCode()){
                    LoadFailUtil.initDialogToLogin((Activity) commonView.getCommonCustomContext());
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
                }else if(null != bean && 0 != bean.getCode()){
                    LoadFailUtil.initDialogToLogin((Activity) commonView.getCommonCustomContext());
                }else{
                    commonView.getCommonUserInfoError(Constants.REQUEST_ERROR_CODE);
                }
            }
        });
    }

    @Override
    public void getTeacher() {
        if(!NetUtils.isConnected(commonView.getCommonCustomContext())){
            commonView.getCommonTeacherError(Constants.NET_ERROR_CODE);
            return;
        }
        OkHttpRequest.doGet(commonView.getCommonCustomContext(), Constants.GET_TEACHER, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                commonView.getCommonTeacherError(Constants.REQUEST_ERROR_CODE);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //Log.d("CommonPresenterImp", response.body().string());
                CommonTeacherBean bean = GsonUtils.getBean(response.body().string(), CommonTeacherBean.class);
                if(null != bean && 0 == bean.getCode()){
                    commonView.getCommonTeacherSuccess(bean);
                }else if(null != bean && 0 != bean.getCode()){
                    LoadFailUtil.initDialogToLogin((Activity) commonView.getCommonCustomContext());
                }else{
                    commonView.getCommonTeacherError(Constants.REQUEST_ERROR_CODE);
                }
            }
        });
    }

    @Override
    public void getUserTeachPlan() {
        if(!NetUtils.isConnected(commonView.getCommonCustomContext())){
            commonView.getCommonUserTeachPlanError(Constants.NET_ERROR_CODE);
            return;
        }
        OkHttpRequest.doGet(commonView.getCommonCustomContext(), OkHttpRequest.attachHttpGetParams(Constants.GET_USER_TEACH_PLAN, commonView.getCommonRequestParams()), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                commonView.getCommonUserTeachPlanError(Constants.REQUEST_ERROR_CODE);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                CommonUserTeachPlanBean bean = GsonUtils.getBean(response.body().string(), CommonUserTeachPlanBean.class);
                if(null != bean && 0 == bean.getCode()){
                    commonView.getCommonUserTeachPlanSuccess(bean);
                }else if(null != bean && 0 != bean.getCode()){
                    LoadFailUtil.initDialogToLogin((Activity) commonView.getCommonCustomContext());
                }else{
                    commonView.getCommonUserTeachPlanError(Constants.REQUEST_ERROR_CODE);
                }
            }
        });
    }
}
