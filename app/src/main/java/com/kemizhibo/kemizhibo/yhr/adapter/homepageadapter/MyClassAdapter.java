package com.kemizhibo.kemizhibo.yhr.adapter.homepageadapter;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.bean.homepagerbean.HomePageBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.CommentBean;
import com.kemizhibo.kemizhibo.yhr.utils.immersion.GlideRoundTransform;

import java.util.List;

/**
 * Author: yhr
 * Date: on 2018/7/5.
 * Describe:  我的备课的适配器
 */

public class MyClassAdapter extends BaseQuickAdapter<HomePageBean.ContentBean.ReturnPrepareBean, BaseViewHolder> {

    public MyClassAdapter(int layoutResId, @Nullable List<HomePageBean.ContentBean.ReturnPrepareBean> data) {
        super(layoutResId, data);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void convert(BaseViewHolder helper, HomePageBean.ContentBean.ReturnPrepareBean item) {
        TextView view = helper.getView(R.id.myclass_state);
        Glide.with(mContext).load(item.getLogo()).crossFade().centerCrop().transform(new GlideRoundTransform(mContext, 5)).into((ImageView) helper.getView(R.id.myclass_imageview));
        if (item.getPrepareStatus()==1){
            helper.setText(R.id.myclass_state,"未备课");
            view.setTextColor(R.color.text_444444);
        }else {
            helper.setText(R.id.myclass_state,"已备课");
            //view.setTextColor(R.color.text_444444);
        }
        helper.setText(R.id.myclass_title,item.getCourseName());
        helper.setText(R.id.myclass_time,item.getCreateTime());
    }
}
