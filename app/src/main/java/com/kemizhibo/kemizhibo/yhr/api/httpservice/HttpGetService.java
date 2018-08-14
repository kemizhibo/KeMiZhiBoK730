package com.kemizhibo.kemizhibo.yhr.api.httpservice;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Author: yhr
 * Date: 2018/5/8
 * Describe: 通用
 */

public interface HttpGetService {

    //图片，不用管
    String Host = "http://192.168.1.101";

    @GET("data")
    Observable<ResponseBody> getData();
    //路劲，加参数

    //获取Token
    //@Headers({"User-Agent: Mozilla/5.0 (Linux; U; Android 3.0; en-us; Xoom Build/HRI39) AppleWebKit/534.13 (KHTML, like Gecko) Version/4.0 Safari/534.13"})
    //登录
    @GET("kemiapi/user/school/login")
    Observable<ResponseBody> getLoginData(@Query("account") String account,
                                          @Query("password") String password);

   /* @GET("product/getProducts")
    Observable<ResponseBody> getDemoData(@Query("pscid") String pscid,@Query("page") String page);*/
   //筛选条件
   @GET("kemiapi/queryCriteria/queryBaseInfo")
   Observable<ResponseBody> getFilterData();

    //影像素材列表页
    @GET("kemiapi/course/focusing/getSimpleCourseForB")
    Observable<ResponseBody> getYingXinagFragmentData(@Query("sellType") String sellType,
                                                      @Query("currentPage") String currentPage,
                                                      @Query("pageSize") String pageSize,
                                                      @Query("materialEdition") String materialEdition,
                                                      @Query("subjectId") String subjectId,
                                                      @Query("semester") String semester,
                                                      @Query("knowledgeId") String knowledgeId);

    //影像素材视频详情页面，教师培训详情页,科学观察室详情页
    @GET("kemiapi/course/getCourseForB")
    Observable<ResponseBody> getYingXinagVideoDetailsData(@Header("Authorization") String token,
                                                          @Query("courseId") String courseId);
    //收藏夹
    @GET("kemiapi/user/favourite/listForB")
    Observable<ResponseBody> getCollectionBoxData(@Header("Authorization") String token,
                                                          @Query("page") String page,
                                                          @Query("size") String size);
    //获取视频地址
    @GET("kemiapi/course/getPlayUrl")
    Observable<ResponseBody> getYingXinagVideoDetailsUrlData(@Header("Authorization") String token,
                                                             @Query("courseId") String courseId,
                                                      @Query("videoType") String videoType,
                                                      @Query("encryption") String encryption,
                                                      @Query("videoClarity") String videoClarity);
    //视屏详情评论列表
    @GET("kemiapi/course/shortvideo/comment/listForB")
    Observable<ResponseBody> getYingXinagVideoDetailsCommentData(@Header("Authorization") String token,
                                                             @Query("otherId") String otherId,
                                                             @Query("page") String page,
                                                             @Query("size") String size,
                                                             @Query("type") String type);
    //反馈
    @FormUrlEncoded
    @POST("kemiapi/course/saveUserOption")
    Observable<ResponseBody> getFeedBackData(@Header("Authorization") String token,
                                                                 @Field("content") String content,
                                                                 @Field("type") String type);

    //保存头像图片
    @FormUrlEncoded
    @POST("kemiapi/ketang/user/info/editPicImg")
    Observable<ResponseBody> getPreservationPhotoData(@Header("Authorization") String token,
                                             @Field("picImg") String picImg);

 //第一次播放视频记录
 @FormUrlEncoded
 @POST("kemiapi/user/watchHistory/putForB")
 Observable<ResponseBody> getOneLookData(@Header("Authorization") String token,
                                          @Field("playPosition") String playPosition,
                                          @Field("keyId") String keyId,
                                          @Field("courseId") String courseId,
                                          @Field("watchTime") String watchTime,
                                          @Field("isEnd") String isEnd);
 /*//第二次及以后播放视频记录
 @FormUrlEncoded
 @POST("kemiapi/user/watchHistory/putForB")
 Observable<ResponseBody> getTwoLookData(@Header("Authorization") String token,
                                         @Field("playPosition") String playPosition,
                                         @Field("keyId") String keyId,
                                         @Field("courseId") String courseId,
                                         @Field("watchTime") String watchTime,
                                         @Field("isEnd") String isEnd);*/
    //图文详情
    @GET("kemiapi/course/getCourseForB")
    Observable<ResponseBody> getPictrueFragmnetData(@Header("Authorization") String token,
                                                    @Query("courseId") String courseId);

    //新旧手机发送验证码
    @GET("kemiapi/ketang/user/info/{flag}/sendMsg")
    Observable<ResponseBody> getSendYanZhengMaData(@Path("flag") String flag,
                                                   @Header("Authorization") String token,
                                                   @Query("mobile") String mobile);
    //验证旧手机号
    @FormUrlEncoded
    @POST("kemiapi/ketang/user/info/validate/old")
    Observable<ResponseBody> getOldPhoneData(@Header("Authorization") String token,
                                             @Field("mobile") String mobile,
                                             @Field("code") String code);
    //验证新手机号
    @FormUrlEncoded
    @POST("kemiapi/ketang/user/info/validate/new")
    Observable<ResponseBody> getNewPhoneData(@Header("Authorization") String token,
                                             @Field("mobile") String mobile,
                                             @Field("code") String code);
    //修改密码
    @FormUrlEncoded
    @POST("kemiapi/ketang/user/info/edit/password")
    Observable<ResponseBody> getChangePwdData(@Header("Authorization") String token,
                                             @Field("oldPassword") String oldPassword,
                                             @Field("newPassword") String newPassword);
    //退出登录
    @GET("kemiapi/ketang/user/info/exit")
    Observable<ResponseBody> getSignOutData(@Header("Authorization") String token);

    //清空浏览记录
    @GET("kemiapi/user/watchHistory/emptyForB")
    Observable<ResponseBody> getClearLiuLanData(@Header("Authorization") String token);

    //删除一个或者多个浏览记录
    @GET("kemiapi/user/watchHistory/deleteForB")
    Observable<ResponseBody> getClearOneOrMoreLiuLanData(@Header("Authorization") String token,
                                                         @Query("ids") List ids);

    //教师培训列表页
    @GET("kemiapi/course/focusing/getSimpleCourseForB")
    Observable<ResponseBody> getTeacherTrainingData(@Query("sellType") String sellType,
                                                   @Query("currentPage") String currentPage,
                                                   @Query("pageSize") String pageSize,
                                                   @Query("materialEdition") String materialEdition,
                                                   @Query("subjectId") String subjectId,
                                                   @Query("semester") String semester,
                                                   @Query("courseType") String courseType,
                                                   @Query("knowledgeId") String knowledgeId);
    //教师培训直播详情讲解列表页
    @GET("kemiapi/course/focusing/getSimpleCourseForB")
    Observable<ResponseBody> getTeacherTrainingLookFragmnetData(@Query("sellType") String sellType,
                                                                @Query("subjectId") String subjectId,
                                                                @Query("currentPage") String currentPage,
                                                                @Query("pageSize") String pageSize);
    //资源中心搜索总列表接口
    @GET("kemiapi/course/focusing/getSimpleCourse")
    Observable<ResponseBody> getSearchData(@Query("sellType") String sellType,
                                           @Query("currentPage") String currentPage,
                                           @Query("pageSize") String pageSize,
                                           @Query("courseName") String courseName);
    //获取浏览记录列表
    @GET("kemiapi/user/watchHistory/listForB")
    Observable<ResponseBody> getLiuLanData(@Header("Authorization") String token,
                                                             @Query("page") String page,
                                                             @Query("size") String size);

    //首页轮播图
    @GET("kemiapi/prepare/module/homePageBanner")
    Observable<ResponseBody> getSowingMapData(@Header("Authorization") String token,
                                              @Query("device") String device);
    //首页数据
    @GET("kemiapi/prepare/module/appHomePage")
    Observable<ResponseBody> getHomePageData(@Header("Authorization") String token);

    //获取版本信息
    @GET("kemiapi/version/getLatestVersion")
    Observable<ResponseBody> getVersionInformationData();

    @GET("kemiapi/recommend")
    Observable<ResponseBody> getRecommendData();

    @GET("kemiapi/microblog/init")
    Observable<ResponseBody> getDynamicData();



   /* @POST("microblog/get/{microblogId}")
    Observable<ResponseBody> getLectureDetailData(@Path("microblogId") String microblogId,@Body Page page);*/

}
