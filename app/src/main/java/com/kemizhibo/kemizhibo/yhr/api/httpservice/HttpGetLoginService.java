package com.kemizhibo.kemizhibo.yhr.api.httpservice;

import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Author: yhr
 * Date: 2018/5/8
 * Describe: 
 */

public interface HttpGetLoginService {
    //图片，不用管
    String Host = "http://192.168.1.101";

    //获取Token
    @Headers({"User-Agent: Mozilla/5.0 (Linux; U; Android 3.0; en-us; Xoom Build/HRI39) AppleWebKit/534.13 (KHTML, like Gecko) Version/4.0 Safari/534.13"})

    @POST("kemiapi/user/school/login")
    Observable<ResponseBody> getTokenData(@Query("account") String account,
                                          @Query("password") String password);

}
