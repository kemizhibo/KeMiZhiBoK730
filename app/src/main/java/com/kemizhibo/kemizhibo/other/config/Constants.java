package com.kemizhibo.kemizhibo.other.config;

/**
 * Created by Administrator on 2018/7/25.
 */

public class Constants {
    public static final String SERVER = "http://39.155.221.165:8080/";

    public static final String PREPARING_CENTER_URL = SERVER + "kemiapi/prepare/module/prepareList";
    public static final String PREPARING_ONLINE_URL = SERVER + "kemiapi/prepare/module/planList";
    public static final String COMMON_FILTER_URL = SERVER + "kemiapi/queryCriteria/queryBaseInfo";
    public static final String PREPARING_PACKAGE_DETAIL_URL = SERVER + "kemiapi/prepare/module/packageDetail";
    public static final String PREPARING_LESSONS_RECORD_URL = SERVER + "kemiapi/prepare/module/getPrepareRecord";
    public static final String TEACHING_LESSONS_RECORD_URL = SERVER + "kemiapi/prepare/module/getPlanRecord";
    public static final String GET_USER_INFO = SERVER + "kemiapi/ketang/user/info/get";
    public static final String GET_TEACHER = SERVER + "kemiapi/prepare/module/getTeacherBySchoolId";

    public static final int NET_ERROR_CODE = 12345;
    public static final int REQUEST_ERROR_CODE = 23456;

    public static final String METERIAL_ID = "materialId";
    public static final String GRADE_ID = "gradeId";
    public static final String SEMESTER_ID = "semesterId";
    public static final String REQUEST_SOURCE = "requestSource";
    public static final String CURRENT_PAGE = "currentPage";
    public static final String PAGE_SIZE = "pageSize";

    public static final String CHAPTER_ID = "chapterId";
    public static final String TYPE = "type";

    public static final String PLAN_STATUS = "planStatus";
    public static final String USER_ID = "userId";
    public static final String START_TIME = "startTime";

    public static final String COURSE_ID = "courseId";

    public static final String ROLE_ID = "roleId";
    public static final int MANAGER_ROLE_ID = 8;
    public static final int CHILD_ROLE_ID = 9;

    public static final String TEST_IMAGE_URL = "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1532500163&di=0b977fc00b6968ed4e6e5fc7707bd986&src=http://file25.mafengwo.net/M00/0A/AC/wKgB4lMC26CAWsKoAALb5778DWg60.rbook_comment.w1024.jpeg";
}
