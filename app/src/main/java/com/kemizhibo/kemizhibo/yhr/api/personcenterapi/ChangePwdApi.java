package com.kemizhibo.kemizhibo.yhr.api.personcenterapi;

import com.alibaba.fastjson.JSON;
import com.kemizhibo.kemizhibo.yhr.api.httpservice.HttpGetService;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.ChangePwdBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.SendYanZhengMaBean;
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

public class ChangePwdApi extends BaseApi<ChangePwdBean> {
    String token;
    String oldPassword;
    String newPassword;
    public ChangePwdApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity, String token, String oldPassword, String newPassword) {
        super(listener, rxAppCompatActivity);
        this.token = token;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }


    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getChangePwdData(token,oldPassword,newPassword);
    }

    @Override
    public ChangePwdBean call(ResponseBody responseBody) {
        //解析
        String string = "";
        try {
            string = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSON.parseObject(string, ChangePwdBean.class);
    }
}
