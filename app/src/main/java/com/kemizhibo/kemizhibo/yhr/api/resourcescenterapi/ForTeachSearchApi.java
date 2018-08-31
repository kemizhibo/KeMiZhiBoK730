package com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi;

import com.alibaba.fastjson.JSON;
import com.kemizhibo.kemizhibo.yhr.api.httpservice.HttpGetService;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.ForTeachSearchBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.SearchBean;
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

public class ForTeachSearchApi extends BaseApi<ForTeachSearchBean> {
    //声明参数
    private String token;
    private String keyWord;
    private String pageSize;
    private String currentPage;


    public ForTeachSearchApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity, String token,String keyWord,String pageSize,String currentPage) {
        super(listener, rxAppCompatActivity);
        this.token = token ;
        this.keyWord = keyWord ;
        this.pageSize = pageSize ;
        this.currentPage = currentPage ;
    }


    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getForTeachSearchData(token,keyWord,pageSize,currentPage);
    }

    @Override
    public ForTeachSearchBean call(ResponseBody responseBody) {
        //解析
        String string = "";
        try {
            string = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSON.parseObject(string, ForTeachSearchBean.class);
    }
}
