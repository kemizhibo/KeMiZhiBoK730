package com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi;

import com.alibaba.fastjson.JSON;
import com.kemizhibo.kemizhibo.yhr.api.httpservice.HttpGetService;
import com.kemizhibo.kemizhibo.yhr.bean.LoginBean;
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

public class LoginApi extends BaseApi<LoginBean> {
    //声明参数
    private String account;
    private String password;


    public LoginApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity, String account, String password) {
        super(listener, rxAppCompatActivity);
        this.account = account ;
        this.password = password ;
    }


    @Override
    public Observable getObservable(Retrofit retrofit) {
        /*HttpGetLoginService httpGetTokenService = retrofit.create(HttpGetLoginService.class);
        return httpGetTokenService.getTokenData(account,password);*/
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getLoginData(account,password);
    }

    @Override
    public LoginBean call(ResponseBody responseBody) {
        //解析
        String string = "";
        try {
            string = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //LogUtils.e(string);
        return JSON.parseObject(string, LoginBean.class);
    }
}
