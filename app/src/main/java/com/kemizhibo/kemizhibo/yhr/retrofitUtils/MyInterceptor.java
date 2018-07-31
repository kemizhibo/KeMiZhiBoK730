package com.kemizhibo.kemizhibo.yhr.retrofitUtils;

/**
 * Created by 闫浩然 on 2018/1/17.
 */

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by BAIPEI on 2017/12/19.
 */

public class MyInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String string = request.url().url().toString();
        String s = string + "&scourcr=android";
        Request request1 = request.newBuilder().url(s).build();
        Response response = chain.proceed(request1);
        return response;
    }
}
