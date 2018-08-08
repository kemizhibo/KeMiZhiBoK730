package com.kemizhibo.kemizhibo.yhr.api.httpservice;



import java.io.File;

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
    @POST("kemiapi/ketang/user/info/editPicImg")
    Observable<ResponseBody> getPreservationPictureData(@Header("Authorization") String token,
                                                        @Field("picImg") String picImg);

    //上传头像
    @FormUrlEncoded
    @POST("image/upload")
    Observable<ResponseBody> getTakePhotoData(@Header("Authorization") String token,
                                                        @Field("uploadfile") File uploadfile,
                                                        @Field("param") String param);
}
