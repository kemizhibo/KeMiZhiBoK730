package com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.teaching_lessons.view;

import android.content.Context;

import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.teaching_lessons.bean.TeachingLessonsBean;

import java.util.Map;

/**
 * Created by Administrator on 2018/8/1.
 */

public interface TeachingLessonsView {
    Context getCustomContext();
    Map getRequestParams();
    void refreshSuccess(TeachingLessonsBean bean);
    void loadMoreSuccess(TeachingLessonsBean bean);
    void error(int errorCode, boolean isLoadMore);
}
