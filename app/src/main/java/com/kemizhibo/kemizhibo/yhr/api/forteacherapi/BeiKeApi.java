package com.kemizhibo.kemizhibo.yhr.api.forteacherapi;


import com.alibaba.fastjson.JSON;
import com.kemizhibo.kemizhibo.other.preparing_center.bean.PreparingCenterBean;
import com.kemizhibo.kemizhibo.yhr.api.httpservice.HttpGetService;
import com.kemizhibo.kemizhibo.yhr.bean.forteachbean.ForTeachPlayUrlBean;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhxu.library.api.BaseApi;
import com.zhxu.library.listener.HttpOnNextListener;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by yhr on 2018/7/1.
 */

public class BeiKeApi extends BaseApi<PreparingCenterBean> {
    String token;
    String materialId;
    String gradeId;
    String semesterId;
    String requestSource;
    String currentPage;
    String pageSize;
    public BeiKeApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity, String token, String materialId, String gradeId, String semesterId, String requestSource,String currentPage,String pageSize) {
        super(listener, rxAppCompatActivity);
        this.token = token;
        this.materialId = materialId;
        this.gradeId = gradeId;
        this.semesterId = semesterId;
        this.requestSource = requestSource;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }


    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getBeiKeData(token,materialId,gradeId,semesterId,requestSource,currentPage,pageSize);
    }

    @Override
    public PreparingCenterBean call(ResponseBody responseBody) {
        //解析
        String string = "";
        try {
            string = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSON.parseObject(string, PreparingCenterBean.class);
    }
}
