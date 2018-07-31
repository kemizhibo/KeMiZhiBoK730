package com.kemizhibo.kemizhibo.yhr.presenter.resourcescenterpresenter;

import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenter;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.LiveRoomView;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe  科学观察室列表
 */
public interface LiveRoomPresenter extends BasePresenter<LiveRoomView> {
    void getFilterData(BaseActivity activity);
    void getLiveRoomData(BaseActivity activity, String sellType, String currentPage, String pageSize, String materialEdition, String subjectId, String semester, String knowledgeId);
}
