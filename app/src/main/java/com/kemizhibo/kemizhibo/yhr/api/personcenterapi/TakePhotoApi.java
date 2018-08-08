package com.kemizhibo.kemizhibo.yhr.api.personcenterapi;

import com.alibaba.fastjson.JSON;
import com.kemizhibo.kemizhibo.yhr.api.httpservice.HttpGetPreservationPictureService;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.PreservationPictureBean;
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

public class TakePhotoApi extends BaseApi<TakePhotoBean> {

    String token;
    File uploadfile;
    String param;
    public TakePhotoApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity, String token, File uploadfile,String param) {
        super(listener, rxAppCompatActivity);
        this.token = token;
        this.uploadfile = uploadfile;
        this.param = param;
    }


    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetPreservationPictureService httpGetPreservationPictureService = retrofit.create(HttpGetPreservationPictureService.class);
        return httpGetPreservationPictureService.getTakePhotoData(token,uploadfile,param);
    }

    @Override
    public TakePhotoBean call(ResponseBody responseBody) {
        //解析
        String string = "";
        try {
            string = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LogUtils.e("00000000000000000000000000",string);
        return JSON.parseObject(string, TakePhotoBean.class);
    }
}
