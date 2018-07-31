package com.kemizhibo.kemizhibo.yhr.adapter.resourcescenteradapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.CommentBean;
import java.util.List;

/**
 * Author: yhr
 * Date: on 2018/7/5.
 * Describe: 评论列表适配器
 */

public class CommentAdapter extends BaseQuickAdapter<CommentBean.ContentBean.DataBean, BaseViewHolder> implements View.OnClickListener{

    private CallBack mCallBack;
    private int p;
    //点赞回调接口
    @Override
    public void onClick(View view) {
        mCallBack.click(view,p);
    }

    public interface CallBack{
        void click(View view, int p);
    }
    public CommentAdapter(int layoutResId, @Nullable List<CommentBean.ContentBean.DataBean> data,CallBack callBack) {
        super(layoutResId, data);
        mCallBack=callBack;
    }

    @Nullable
    @Override
    public CommentBean.ContentBean.DataBean getItem(int position) {
        p = position;
        return super.getItem(p);
    }

    @Override
    protected void convert(BaseViewHolder helper, final CommentBean.ContentBean.DataBean item) {
        Glide.with(mContext).load(item.getUserHead()).into(helper.<ImageView>getView(R.id.comment_teacher_touxiang));//头像
        //头像
        //Glide.with(mContext).load(item.getUserHead()).crossFade().centerCrop().transform(new GlideRoundTransform(mContext, 5)).into((ImageView) helper.getView(R.id.comment_teacher_touxiang));
        helper.setText(R.id.comment_teacher_name,item.getUserName());
        helper.setText(R.id.comment_time_txt,item.getAddtime());
        helper.setText(R.id.comment_text,item.getContent());
        final ImageView commentDianzan = helper.getView(R.id.comment_dianzan);
        commentDianzan.setOnClickListener(this);
        final ImageView commentLogo = helper.getView(R.id.comment_logo);
        commentLogo.setOnClickListener(this);
        //commentDianzan.setTag(item);
        /*final ImageView commentDianzan = helper.getView(R.id.comment_dianzan);
        // 取出bean中当记录状态是否为true，是的话则给img设置focus点赞图片
        if (item.isZanFocus()) {
            commentDianzan.setImageResource(R.mipmap.dianzan_2);
        } else {
            commentDianzan.setImageResource(R.mipmap.getlike_select_2);
        }

        commentDianzan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取上次是否已经被点击
                boolean flag = item.isZanFocus();
                // 反向存储记录，实现取消点赞功能
                item.setZanFocus(!flag);
                AnimationTools.scale(commentDianzan);
            }
        });*/
    }
}
