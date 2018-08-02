package com.kemizhibo.kemizhibo.yhr.api.httpservice;

import okhttp3.ResponseBody;
import retrofit2.http.Body;
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
 * Describe: 发表评论
 */

public interface HttpGetPutCommentService {

    //发表评论
    @FormUrlEncoded
    @POST("course/comment/putForB")
    Observable<ResponseBody> getPutCommentData(@Header("Authorization") String token,
                                               @Field("courseId") String courseId,
                                               @Field("content") String content,
                                               @Field("pCommentId") String pCommentId);
}
