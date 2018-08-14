package com.kemizhibo.kemizhibo.yhr.adapter.resourcescenteradapter;

/**
 * Author: yhr
 * Date: on 2018/7/3.
 * Describe:
 */

import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.TeacherTrainingBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.YingXiangFragmentBean;
import com.kemizhibo.kemizhibo.yhr.utils.immersion.GlideRoundTransform;

import java.util.List;

public class TeacherTrainingFragmentAdapter extends BaseQuickAdapter<TeacherTrainingBean.ContentBean.DataBean, BaseViewHolder> {

    public TeacherTrainingFragmentAdapter(int layoutResId, @Nullable List<TeacherTrainingBean.ContentBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TeacherTrainingBean.ContentBean.DataBean item) {

        //设置圆角图片
        Glide.with(mContext).load(item.getLogo()).error(R.mipmap.milier).crossFade().centerCrop().transform(new GlideRoundTransform(mContext, 5)).into((ImageView) helper.getView(R.id.teacher_training_recyclerview_imageview));
        helper.setText(R.id.teacher_training_item_title,item.getCourseName());
       /* helper.getView(R.id.teacher_training_recyclerview_imageview).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Toast.makeText(mContext,"点击了。。。",Toast.LENGTH_SHORT).show();
                return true;
            }
        });*/
    }
}
