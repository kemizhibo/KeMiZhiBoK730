package com.kemizhibo.kemizhibo.yhr.api.httpservice;



import okhttp3.ResponseBody;
import retrofit2.http.Body;
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
 * Describe: 收藏
 */

public interface HttpGetCollectionService {

   /* @POST("course/shortvideo/createfavorites")
    Observable<ResponseBody> getCollectionData(@Header("Authorization") String token,
      @FormUrlEncoded                                          @Body String courseId);*/
   @FormUrlEncoded
   @POST("course/shortvideo/createfavoritesForB")
    Observable<ResponseBody> getCollectionData(@Header("Authorization") String token,
                                               @Field("courseId") String courseId);


}