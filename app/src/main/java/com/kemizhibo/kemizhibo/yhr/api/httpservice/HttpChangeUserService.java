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
 * Describe: 更改用户信息
 */

public interface HttpChangeUserService {
    @FormUrlEncoded
    @POST("kemiapi/ketang/user/info/edit")
    Observable<ResponseBody> getChangeUserData(@Header("Authorization") String token,
                                               @Field("school") String school,
                                               @Field("realName") String realName,
                                               @Field("grade") String grade,
                                               @Field("subject") String subject,
                                               @Field("idCardNo") String idCardNo,
                                               @Field("email") String email,
                                               @Field("address") String address);
}
