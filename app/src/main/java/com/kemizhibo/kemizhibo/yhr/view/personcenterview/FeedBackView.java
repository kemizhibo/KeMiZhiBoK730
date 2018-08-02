package com.kemizhibo.kemizhibo.yhr.view.personcenterview;

import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BaseView;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.ChangeUserBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.FeedBackBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.GetUserBean;

/**
 * Created by 17600 on 2018/5/18.
 */

public interface FeedBackView extends BaseView {
    //反馈
    void onFeedBackSuccess(FeedBackBean feedBackBean);
    void onFeedBackError(String msg);
}
