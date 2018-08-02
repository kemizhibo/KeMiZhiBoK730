package com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.preparing_lessons.view;

import android.content.Context;

import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.preparing_lessons.bean.PreparingLessonsBean;

import java.util.Map;

/**
 * Created by Administrator on 2018/8/1.
 */

public interface PreparingLessonsView {
    Context getCustomContext();
    Map getRequestParams();
    void refreshSuccess(PreparingLessonsBean bean);
    void loadMoreSuccess(PreparingLessonsBean bean);
    void error(int errorCode, boolean isLoadMore);
}
