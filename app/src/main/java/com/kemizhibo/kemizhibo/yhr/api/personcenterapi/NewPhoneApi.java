package com.kemizhibo.kemizhibo.yhr.api.personcenterapi;

import com.alibaba.fastjson.JSON;
import com.kemizhibo.kemizhibo.yhr.api.httpservice.HttpGetService;
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

public class NewPhoneApi extends BaseApi<SendYanZhengMaBean> {
    String token;
    String mobile;
    String code;
    public NewPhoneApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity, String token, String mobile, String code) {
        super(listener, rxAppCompatActivity);
        this.token = token;
        this.mobile = mobile;
        this.code = code;
    }


    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getNewPhoneData(token,mobile,code);
    }

    @Override
    public SendYanZhengMaBean call(ResponseBody responseBody) {
        //解析
        String string = "";
        try {
            string = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSON.parseObject(string, SendYanZhengMaBean.class);
    }
}
