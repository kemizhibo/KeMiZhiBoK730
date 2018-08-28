package com.kemizhibo.kemizhibo.yhr.api.findpwdapi;

import com.alibaba.fastjson.JSON;
import com.kemizhibo.kemizhibo.yhr.api.httpservice.HttpGetService;
import com.kemizhibo.kemizhibo.yhr.bean.findPwdbean.SendValidateBeforLoginBean;
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

public class SendValidateBeforLoginApi extends BaseApi<SendValidateBeforLoginBean> {
    String flag;
    String mobile;
    public SendValidateBeforLoginApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity, String flag, String mobile) {
        super(listener, rxAppCompatActivity);
        this.flag = flag;
        this.mobile = mobile;
    }


    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getSendValidateBeforLogin(flag,mobile);
    }

    @Override
    public SendValidateBeforLoginBean call(ResponseBody responseBody) {
        //解析
        String string = "";
        try {
            string = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSON.parseObject(string, SendValidateBeforLoginBean.class);
    }
}
