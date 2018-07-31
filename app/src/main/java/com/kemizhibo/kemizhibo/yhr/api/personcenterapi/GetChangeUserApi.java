package com.kemizhibo.kemizhibo.yhr.api.personcenterapi;

import com.alibaba.fastjson.JSON;
import com.kemizhibo.kemizhibo.yhr.api.httpservice.HttpChangeUserService;
import com.kemizhibo.kemizhibo.yhr.api.httpservice.HttpGetUserService;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.ChangeUserBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.GetUserBean;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
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

public class GetChangeUserApi extends BaseApi<ChangeUserBean> {

    String token;
    String school;
    String realName;
    String grade;
    String subject;
    String idCardNo;
    String email;
    String address;

    public GetChangeUserApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity, String token,String school,String realName,String grade,String subject,String idCardNo,String email,String address) {
        super(listener, rxAppCompatActivity);
        this.token = token;
        this.school = school;
        this.realName = realName;
        this.grade = grade;
        this.subject = subject;
        this.idCardNo = idCardNo;
        this.email = email;
        this.address = address;
    }


    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpChangeUserService httpChangeUserService = retrofit.create(HttpChangeUserService.class);
        return httpChangeUserService.getChangeUserData(token,school,realName,grade,subject,idCardNo,email,address);
    }

    @Override
    public ChangeUserBean call(ResponseBody responseBody) {
        //解析
        String string = "";
        try {
            string = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSON.parseObject(string, ChangeUserBean.class);
    }
}
