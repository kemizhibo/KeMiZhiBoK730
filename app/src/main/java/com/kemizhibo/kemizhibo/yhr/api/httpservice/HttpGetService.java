package com.kemizhibo.kemizhibo.yhr.api.httpservice;

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
    @GET("user/school/login")
    Observable<ResponseBody> getLoginData(@Query("account") String account,
                                          @Query("password") String password);

   /* @GET("product/getProducts")
    Observable<ResponseBody> getDemoData(@Query("pscid") String pscid,@Query("page") String page);*/
   //筛选条件
   @GET("queryCriteria/queryBaseInfo")
   Observable<ResponseBody> getFilterData();

    //影像素材列表页
    @GET("course/focusing/getSimpleCourseForB")
    Observable<ResponseBody> getYingXinagFragmentData(@Query("sellType") String sellType,
                                                      @Query("currentPage") String currentPage,
                                                      @Query("pageSize") String pageSize,
                                                      @Query("materialEdition") String materialEdition,
                                                      @Query("subjectId") String subjectId,
                                                      @Query("semester") String semester,
                                                      @Query("knowledgeId") String knowledgeId);

    //影像素材视频详情页面，教师培训详情页,科学观察室详情页
    @GET("course/getCourseForB")
    Observable<ResponseBody> getYingXinagVideoDetailsData(@Header("Authorization") String token,
                                                          @Query("courseId") String courseId);
    //收藏夹
    @GET("user/favourite/listForB")
    Observable<ResponseBody> getCollectionBoxData(@Header("Authorization") String token,
                                                          @Query("page") String page,
                                                          @Query("size") String size);
    //获取视频地址
    @GET("course/getPlayUrl")
    Observable<ResponseBody> getYingXinagVideoDetailsUrlData(@Header("Authorization") String token,
                                                             @Query("courseId") String courseId,
                                                      @Query("videoType") String videoType,
                                                      @Query("encryption") String encryption,
                                                      @Query("videoClarity") String videoClarity);
    //视屏详情评论列表
    @GET("course/shortvideo/comment/listForB")
    Observable<ResponseBody> getYingXinagVideoDetailsCommentData(@Header("Authorization") String token,
                                                             @Query("otherId") String otherId,
                                                             @Query("page") String page,
                                                             @Query("size") String size,
                                                             @Query("type") String type);
    //反馈
    @FormUrlEncoded
    @POST("course/saveUserOption")
    Observable<ResponseBody> getFeedBackData(@Header("Authorization") String token,
                                                                 @Field("content") String content,
                                                                 @Field("type") String type);
    //图文详情
    @GET("course/getCourseForB")
    Observable<ResponseBody> getPictrueFragmnetData(@Header("Authorization") String token,
                                                    @Query("courseId") String courseId);

    //教师培训列表页
    @GET("course/focusing/getSimpleCourseForB")
    Observable<ResponseBody> getTeacherTrainingData(@Query("sellType") String sellType,
                                                   @Query("currentPage") String currentPage,
                                                   @Query("pageSize") String pageSize,
                                                   @Query("materialEdition") String materialEdition,
                                                   @Query("subjectId") String subjectId,
                                                   @Query("semester") String semester,
                                                   @Query("courseType") String courseType,
                                                   @Query("knowledgeId") String knowledgeId);
    //教师培训直播详情讲解列表页
    @GET("course/focusing/getSimpleCourseForB")
    Observable<ResponseBody> getTeacherTrainingLookFragmnetData(@Query("sellType") String sellType,
                                                                @Query("subjectId") String subjectId,
                                                                @Query("currentPage") String currentPage,
                                                                @Query("pageSize") String pageSize);
    //资源中心搜索总列表接口
    @GET("course/focusing/getSimpleCourse")
    Observable<ResponseBody> getSearchData(@Query("sellType") String sellType,
                                           @Query("currentPage") String currentPage,
                                           @Query("pageSize") String pageSize,
                                           @Query("courseName") String courseName);
    //首页轮播图
    @GET("prepare/module/homePageBanner")
    Observable<ResponseBody> getSowingMapData(@Query("device") String device);
    //首页数据
    @POST("prepare/module/appHomePage")
    Observable<ResponseBody> getHomePageData();

    @GET("recommend")
    Observable<ResponseBody> getRecommendData();

    @GET("microblog/init")
    Observable<ResponseBody> getDynamicData();



   /* @POST("microblog/get/{microblogId}")
    Observable<ResponseBody> getLectureDetailData(@Path("microblogId") String microblogId,@Body Page page);*/

}
