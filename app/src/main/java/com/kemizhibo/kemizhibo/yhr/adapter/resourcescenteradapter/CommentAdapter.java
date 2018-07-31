package com.kemizhibo.kemizhibo.yhr.adapter.resourcescenteradapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.CommentBean;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Author: yhr
 * Date: on 2018/7/5.
 * Describe: 评论列表适配器
 */

public class CommentAdapter extends BaseQuickAdapter<CommentBean.ContentBean.DataBean, BaseViewHolder> {

    public CommentAdapter(int layoutResId, @Nullable List<CommentBean.ContentBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final CommentBean.ContentBean.DataBean item) {
        Glide.with(mContext).load(item.getUserHead())
                .error(R.mipmap.milier)
                .placeholder(R.mipmap.milier)
                .into(helper.<CircleImageView>getView(R.id.comment_teacher_touxiang));//头像
        //头像
        helper.setText(R.id.comment_teacher_name,item.getUserName());
        helper.setText(R.id.comment_time_txt,item.getAddtime());
        helper.setText(R.id.comment_text,item.getContent());
        helper.addOnClickListener(R.id.comment_dianzan);
    }
}
