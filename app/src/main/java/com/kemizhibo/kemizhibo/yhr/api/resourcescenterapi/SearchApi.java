package com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi;

import com.alibaba.fastjson.JSON;
import com.kemizhibo.kemizhibo.yhr.api.httpservice.HttpGetService;
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

public class SearchApi extends BaseApi<SearchBean> {
    //声明参数
    private String sellType;
    private String currentPage;
    private String pageSize;
    private String courseName;

    public SearchApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity, String sellType, String currentPage,String pageSize,String courseName) {
        super(listener, rxAppCompatActivity);
        this.sellType = sellType ;
        this.currentPage = currentPage ;
        this.pageSize = pageSize ;
        this.courseName = courseName ;
    }


    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getSearchData(sellType,currentPage,pageSize,courseName);
    }

    @Override
    public SearchBean call(ResponseBody responseBody) {
        //解析
        String string = "";
        try {
            string = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //LogUtils.e(string+"1111111111111111111111111111111");
        return JSON.parseObject(string, SearchBean.class);
    }
}
