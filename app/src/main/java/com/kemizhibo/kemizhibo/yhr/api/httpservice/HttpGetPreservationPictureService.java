package com.kemizhibo.kemizhibo.yhr.api.httpservice;



import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Author: yhr
 * Date: 2018/5/8
 * Describe: 保存用户头像
 */

public interface HttpGetPreservationPictureService {
    @FormUrlEncoded
   @POST("ketang/user/info/editPicImg")
    Observable<ResponseBody> getPreservationPictureData(@Header("Authorization") String token,
                                                        @Field("picImg") String picImg);
}
