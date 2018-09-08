package com.kemizhibo.kemizhibo.yhr.api.findpwdapi;


import com.alibaba.fastjson.JSON;
import com.kemizhibo.kemizhibo.yhr.api.httpservice.HttpGetService;
import com.kemizhibo.kemizhibo.yhr.bean.findPwdbean.BeforLoginValiDatePhoneBean;
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

public class BeforLoginDataPhoneApi extends BaseApi<BeforLoginValiDatePhoneBean> {
    String mobile;
    String code;
    public BeforLoginDataPhoneApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity, String mobile, String code) {
        super(listener, rxAppCompatActivity);
        this.mobile = mobile;
        this.code = code;
    }


    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getBeforLoginValiDatePhone(mobile,code);
    }

    @Override
    public BeforLoginValiDatePhoneBean call(ResponseBody responseBody) {
        //解析
        String string = "";
        try {
            string = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSON.parseObject(string, BeforLoginValiDatePhoneBean.class);
    }
}
