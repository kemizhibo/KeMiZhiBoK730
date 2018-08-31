package com.kemizhibo.kemizhibo.yhr.api.findpwdapi;

import com.alibaba.fastjson.JSON;
import com.kemizhibo.kemizhibo.yhr.api.httpservice.HttpGetService;
import com.kemizhibo.kemizhibo.yhr.bean.findPwdbean.ResetPwdBean;
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

public class ResetPwdApi extends BaseApi<ResetPwdBean> {
    String token;
    String password;
    public ResetPwdApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity, String token, String password) {
        super(listener, rxAppCompatActivity);
        this.token = token;
        this.password = password;
    }


    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getResetPwdData(token,password);
    }

    @Override
    public ResetPwdBean call(ResponseBody responseBody) {
        //解析
        String string = "";
        try {
            string = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSON.parseObject(string, ResetPwdBean.class);
    }
}
