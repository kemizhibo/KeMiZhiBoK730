package com.kemizhibo.kemizhibo.yhr.api.httpservice;

import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Author: yhr
 * Date: 2018/5/8
 * Describe: 回复评论
 */

public interface HttpGetReplyCommentService {

    @FormUrlEncoded
    @POST("kemiapi/course/comment/putForB")
    Observable<ResponseBody> getPutCommentData(@Header("Authorization") String token,
                                               @Field("courseId") String courseId,
                                               @Field("content") String content,
                                               @Field("pCommentId") String pCommentId);
}
