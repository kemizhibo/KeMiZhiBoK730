package com.kemizhibo.kemizhibo.yhr.adapter.resourcescenteradapter;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
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
        SimpleDraweeView simpleDraweeView = helper.getView(R.id.comment_teacher_touxiang);
        if (!TextUtils.isEmpty(item.getUserHead())){
            simpleDraweeView.setImageURI(Uri.parse(item.getUserHead()));
        }
        //头像
        helper.setText(R.id.comment_teacher_name,item.getShowName());
        helper.setText(R.id.comment_time_txt,item.getAddtime());
        helper.setText(R.id.comment_text,item.getContent());
        helper.addOnClickListener(R.id.comment_dianzan_layout);
        if (item.getPraiseHistory()==1){
            helper.getView(R.id.comment_dianzan).setBackgroundResource(R.mipmap.getlike_select_2);
        }else {
            helper.getView(R.id.comment_dianzan).setBackgroundResource(R.mipmap.dianzan_2);
        }
    }
}
