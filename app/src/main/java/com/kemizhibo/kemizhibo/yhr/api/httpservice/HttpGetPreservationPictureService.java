package com.kemizhibo.kemizhibo.yhr.api.httpservice;



import java.io.File;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Author: yhr
 * Date: 2018/5/8
 * Describe: 上传保存头像
 */

public interface HttpGetPreservationPictureService {
    @FormUrlEncoded
    @POST("kemiapi/ketang/user/info/editPicImg")
    Observable<ResponseBody> getPreservationPictureData(@Header("Authorization") String token,
                                                        @Field("picImg") String picImg);

    /*//上传头像
    @Multipart
    @POST("kemiapi/ketang/user/uploadImg")
    //@POST("image/upload")
    Observable<ResponseBody> getTakePhotoData(@Part ("file") File file);*/
    //上传头像@Part("description") RequestBody description
    @Multipart
    @POST("file/upload")
    //@POST("image/upload")
    Observable<ResponseBody> getTakePhotoData(@Part MultipartBody.Part file);
}
