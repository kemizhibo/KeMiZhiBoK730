package com.kemizhibo.kemizhibo.yhr.adapter.forteachadapter;

/**
 * Author: yhr
 * Date: on 2018/7/3.
 * Describe:授课列表适配器
 */

import android.net.Uri;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.preparing_center.bean.PreparingCenterBean;
import com.kemizhibo.kemizhibo.other.preparing_online.bean.PreparingOnlineBean;
import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.preparing_lessons.bean.PreparingLessonsBean;
import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.teaching_lessons.bean.TeachingLessonsBean;

import java.util.List;

public class ShouKeAdapter extends BaseQuickAdapter<TeachingLessonsBean.ContentBean.DataBean, BaseViewHolder> {

    public ShouKeAdapter(int layoutResId, @Nullable List<TeachingLessonsBean.ContentBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TeachingLessonsBean.ContentBean.DataBean item) {
        SimpleDraweeView simpleDraweeView = helper.getView(R.id.logo);
        simpleDraweeView.setImageURI(Uri.parse(String.valueOf(item.getLogo())));
        helper.setText(R.id.name, item.getCourseName());
    }
}
