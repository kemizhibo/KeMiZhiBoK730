package com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi;

import com.alibaba.fastjson.JSON;
import com.kemizhibo.kemizhibo.yhr.api.httpservice.HttpGetService;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.CommentBean;
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

public class YingXiangDetailsVideoCommentApi extends BaseApi<CommentBean> {
    //声明参数
    private String otherId;
    private String page;
    private String size;
    private String type;
    public YingXiangDetailsVideoCommentApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity, String otherId,String page,String size,String type) {
        super(listener, rxAppCompatActivity);
        this.otherId = otherId ;
        this.page = page ;
        this.size = size ;
        this.type = type ;

    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getYingXinagVideoDetailsCommentData(otherId,page,size,type);
    }

    @Override
    public CommentBean call(ResponseBody responseBody) {
        //解析
        String string = "";
        try {
            string = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LogUtils.e(string);
        return JSON.parseObject(string, CommentBean.class);
    }
}
