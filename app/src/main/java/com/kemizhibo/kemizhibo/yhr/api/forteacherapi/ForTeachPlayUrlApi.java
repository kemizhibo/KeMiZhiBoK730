package com.kemizhibo.kemizhibo.yhr.api.forteacherapi;


import com.alibaba.fastjson.JSON;
import com.kemizhibo.kemizhibo.yhr.api.httpservice.HttpGetService;
import com.kemizhibo.kemizhibo.yhr.bean.findPwdbean.BeforLoginValiDatePhoneBean;
import com.kemizhibo.kemizhibo.yhr.bean.forteachbean.ForTeachPlayUrlBean;
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

public class ForTeachPlayUrlApi extends BaseApi<ForTeachPlayUrlBean> {
    String courseId;
    String videoType;
    String encryption;
    String videoClarity;
    String kpointId;
    public ForTeachPlayUrlApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity, String courseId, String videoType,String encryption,String videoClarity,String kpointId) {
        super(listener, rxAppCompatActivity);
        this.courseId = courseId;
        this.videoType = videoType;
        this.encryption = encryption;
        this.videoClarity = videoClarity;
        this.kpointId = kpointId;
    }


    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getForTeachPlayUrlData(courseId,videoType,encryption,videoClarity,kpointId);
    }

    @Override
    public ForTeachPlayUrlBean call(ResponseBody responseBody) {
        //解析
        String string = "";
        try {
            string = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSON.parseObject(string, ForTeachPlayUrlBean.class);
    }
}
