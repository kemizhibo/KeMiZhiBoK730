package com.kemizhibo.kemizhibo.yhr.adapter.personcenteradapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.CollectionBoxBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.LiuLanBean;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.kemizhibo.kemizhibo.yhr.utils.immersion.GlideRoundTransform;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: 闫浩然
 * Date: on 2018/7/31.
 * Describe: 浏览记录适配器
 */

public class LiuLanAdapter extends BaseQuickAdapter<LiuLanBean.ContentBean.DataBean, BaseViewHolder> {


    public LiuLanAdapter(int layoutResId, @Nullable List<LiuLanBean.ContentBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LiuLanBean.ContentBean.DataBean item) {
        LiuLanBean.ContentBean.DataBean.CourseBean course = item.getCourse();
        //设置圆角图片
        Glide.with(mContext).load(course.getLogo()).crossFade().centerCrop().transform(new GlideRoundTransform(mContext, 5)).into((ImageView) helper.getView(R.id.item_liulan_photo));
        helper.setText(R.id.item_liulan_title, course.getCourseName());
        helper.setText(R.id.item_liulan_state, "上次观看至"+item.getPlayPosition()+"秒处");
        helper.setText(R.id.item_liulan_time, item.getWatchTime());
        LogUtils.i("00000000000000000000",course.getCourseName()+item.getWatchTime());
    }

}
