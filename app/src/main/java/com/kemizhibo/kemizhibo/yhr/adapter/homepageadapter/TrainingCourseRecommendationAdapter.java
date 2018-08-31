package com.kemizhibo.kemizhibo.yhr.adapter.homepageadapter;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.bean.homepagerbean.HomePageBean;
import com.kemizhibo.kemizhibo.yhr.utils.immersion.GlideRoundTransform;
import java.util.List;

/**
 * Author: yhr
 * Date: on 2018/7/19.
 * Describe: 培训课推荐的适配器
 */

public class TrainingCourseRecommendationAdapter extends BaseQuickAdapter<HomePageBean.ContentBean.ReturnTrainBean, BaseViewHolder> {

    public TrainingCourseRecommendationAdapter(int layoutResId, @Nullable List<HomePageBean.ContentBean.ReturnTrainBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomePageBean.ContentBean.ReturnTrainBean item) {
        SimpleDraweeView simpleDraweeView = helper.getView(R.id.training_course_recommendation_imageview);
        simpleDraweeView.setImageURI(Uri.parse(item.getLogo()));
        helper.setText(R.id.training_course_recommendation_title,item.getCourseName());
        helper.getView(R.id.collection_box_play_butn).setVisibility(View.VISIBLE);
    }
}

