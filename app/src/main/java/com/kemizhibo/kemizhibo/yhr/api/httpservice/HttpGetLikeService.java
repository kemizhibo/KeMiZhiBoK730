package com.kemizhibo.kemizhibo.yhr.api.httpservice;

import com.kemizhibo.kemizhibo.yhr.App;

import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Author: yhr
 * Date: 2018/5/8
 * Describe: 评论点赞
 */

public interface HttpGetLikeService {

    @FormUrlEncoded
    @POST("kemiapi/course/praise/addForB")
    Observable<ResponseBody> getLikeData(@Header("Authorization") String token,
                                          @Field("targetId") String targetId,
                                          @Field("type") String type);

}
