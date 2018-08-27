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
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.LiveRoomBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.YingXiangFragmentBean;
import com.kemizhibo.kemizhibo.yhr.utils.immersion.GlideRoundTransform;

import java.util.List;

public class LiveRoomFragmentAdapter extends BaseQuickAdapter<LiveRoomBean.ContentBean.DataBean, BaseViewHolder> {

    public LiveRoomFragmentAdapter(int layoutResId, @Nullable List<LiveRoomBean.ContentBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LiveRoomBean.ContentBean.DataBean item) {
        //设置值
        Glide.with(mContext).load(item.getLogo()).error(R.mipmap.milier).crossFade().centerCrop().transform(new GlideRoundTransform(mContext, 5)).into((ImageView) helper.getView(R.id.liveroom_recyclerview_imageview));
        helper.setText(R.id.liveroom_item_title,item.getCourseName());
    }




}
