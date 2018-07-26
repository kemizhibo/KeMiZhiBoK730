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
 * Describe: 删除评论
 */

public interface HttpGetDeleteCommentService {

    //删除评论commentId
    @GET("course/shortvideo/comment/delete")
    Observable<ResponseBody> getDeleteCommentData(@Header("Authorization") String token,
                                                  @Query("commentId") String commentId,
                                                  @Query("type") String type);
}
