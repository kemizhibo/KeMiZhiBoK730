package com.kemizhibo.kemizhibo.yhr.api.tokenapi;

import com.alibaba.fastjson.JSON;
import com.kemizhibo.kemizhibo.yhr.api.httpservice.HttpGetLoginService;
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

public class GetLoginApi extends BaseApi<LoginBean> {
    //声明参数
    private String account;
    private String password;


    public GetLoginApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity, String account, String password) {
        super(listener, rxAppCompatActivity);
        this.account = account ;
        this.password = password ;
    }


    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetLoginService httpGetLoginService = retrofit.create(HttpGetLoginService.class);
        return httpGetLoginService.getTokenData(account,password);
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
