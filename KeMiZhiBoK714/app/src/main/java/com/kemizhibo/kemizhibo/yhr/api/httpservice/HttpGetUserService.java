package com.kemizhibo.kemizhibo.yhr.api.httpservice;



import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Author: yhr
 * Date: 2018/5/8
 * Describe: 获取用户信息
 */

public interface HttpGetUserService {

   @GET("ketang/user/info/get")
    Observable<ResponseBody> getUserData(@Header("Authorization") String token);
}
