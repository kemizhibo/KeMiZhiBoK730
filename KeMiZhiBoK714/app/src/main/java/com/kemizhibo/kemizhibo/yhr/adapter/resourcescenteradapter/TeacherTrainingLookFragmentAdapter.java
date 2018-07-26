package com.kemizhibo.kemizhibo.yhr.adapter.resourcescenteradapter;

/**
 * Author: yhr
 * Date: on 2018/7/3.
 * Describe:
 */

import android.support.annotation.Nullable;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.TeacherTrainingLookBean;
import java.util.List;

public class TeacherTrainingLookFragmentAdapter extends BaseQuickAdapter<TeacherTrainingLookBean.ContentBean.DataBean, BaseViewHolder> {

    public TeacherTrainingLookFragmentAdapter(int layoutResId, @Nullable List<TeacherTrainingLookBean.ContentBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TeacherTrainingLookBean.ContentBean.DataBean item) {
        //设置视频播放
        //视频地址
        String str = item.getContext();
        //View view = helper.getView(R.id.teacher_look_fragment_video);
       /* view.setUp(str,
                JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL,
                "");
        Glide.with(mContext)
                .load(item.getLogo())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(view.thumbImageView);*/
        //Glide.with(mContext).load(item.getLogo()).crossFade().into((ImageView) helper.getView(R.id.teacher_look_fragment_teacher_touxiang));
        helper.setText(R.id.teacher_look_fragment_video_title,item.getCourseName());
        helper.setText(R.id.teacher_look_fragment_teacher_name,item.getCourseName());
        helper.setText(R.id.teacher_look_fragment_txt,item.getTitle());
    }
}
