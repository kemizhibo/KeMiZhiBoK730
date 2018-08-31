package com.kemizhibo.kemizhibo.yhr.adapter.resourcescenteradapter;

/**
 * Author: yhr
 * Date: on 2018/7/3.
 * Describe:教师培训适配器
 */

import android.net.Uri;
import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.TeacherTrainingBean;
import java.util.List;

public class TeacherTrainingFragmentAdapter extends BaseQuickAdapter<TeacherTrainingBean.ContentBean.DataBean, BaseViewHolder> {

    public TeacherTrainingFragmentAdapter(int layoutResId, @Nullable List<TeacherTrainingBean.ContentBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TeacherTrainingBean.ContentBean.DataBean item) {
        SimpleDraweeView simpleDraweeView = helper.getView(R.id.teacher_training_recyclerview_imageview);
        simpleDraweeView.setImageURI(Uri.parse(item.getLogo()));
        helper.setText(R.id.teacher_training_item_title,item.getCourseName());
    }
}
