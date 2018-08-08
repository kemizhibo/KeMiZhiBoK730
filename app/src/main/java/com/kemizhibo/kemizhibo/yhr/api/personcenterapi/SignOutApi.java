package com.kemizhibo.kemizhibo.yhr.api.personcenterapi;

import com.alibaba.fastjson.JSON;
import com.kemizhibo.kemizhibo.yhr.api.httpservice.HttpGetPreservationPictureService;
import com.kemizhibo.kemizhibo.yhr.api.httpservice.HttpGetService;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.SendYanZhengMaBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.TakePhotoBean;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhxu.library.api.BaseApi;
import com.zhxu.library.listener.HttpOnNextListener;

import java.io.File;
import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by yhr on 2018/7/1.
 */

public class SendYanZhengMaApi extends BaseApi<SendYanZhengMaBean> {
    String flag;
    String token;
    String mobile;
    public SendYanZhengMaApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity, String flag,String token, String mobile) {
        super(listener, rxAppCompatActivity);
        this.flag = flag;
        this.token = token;
        this.mobile = mobile;
    }


    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getSendYanZhengMaData(flag,token,mobile);
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
