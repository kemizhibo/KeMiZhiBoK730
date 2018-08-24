package com.kemizhibo.kemizhibo.yhr.adapter.personcenteradapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.activity.personcenters.PersonCenterLiuLanActivity;
import com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity.PictrueDetailsActivity;
import com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity.YingXinagVideoDetailsActivity;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.LiuLanBean;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.kemizhibo.kemizhibo.yhr.utils.NoFastClickUtils;
import com.kemizhibo.kemizhibo.yhr.utils.TimeH;
import com.kemizhibo.kemizhibo.yhr.utils.immersion.GlideRoundTransform;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author: 闫浩然
 * Date: on 2018/7/31.
 * Describe: 浏览记录适配器
 */
public class LiuLanAdapter extends RecyclerView.Adapter<LiuLanAdapter.ViewHolder> {

    private static final int MYLIVE_MODE_CHECK = 0;
    int mEditMode = MYLIVE_MODE_CHECK;

    private Context context;
    private List<LiuLanBean.ContentBean.DataBean> mMyLiveList;
    private OnItemClickListener mOnItemClickListener;

    public LiuLanAdapter(Context context) {
        this.context = context;
    }


    public void notifyAdapter(List<LiuLanBean.ContentBean.DataBean> myLiveList, boolean isAdd) {
        if (!isAdd) {
            this.mMyLiveList = myLiveList;
        } else {
            this.mMyLiveList.addAll(myLiveList);
        }
        notifyDataSetChanged();
    }

    public List<LiuLanBean.ContentBean.DataBean> getMyLiveList() {
        if (mMyLiveList == null) {
            mMyLiveList = new ArrayList<>();
        }
        return mMyLiveList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_liulan_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return mMyLiveList.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final LiuLanBean.ContentBean.DataBean myLive = mMyLiveList.get(holder.getAdapterPosition());
        //填充值
        Glide.with(context).load(myLive.getCourse().getLogo()).crossFade().centerCrop().transform(new GlideRoundTransform(context, 5)).into( holder.itemLiulanPhoto);
        holder.itemLiulanTitle.setText(myLive.getCourse().getCourseName());
        holder.itemLiulanTime.setText(myLive.getWatchTime());
        if (myLive.getCourse().getIsImageText()==1){
            holder.itemLiulanButn.setText("查看");
            holder.itemLiulanButn.setTextColor(0xFF67c58c);
            holder.itemLiulanButn.setBackgroundResource(R.mipmap.touming_lvse_state);
        }else {
            if (myLive.getCourse().getIsEnd()==0){
                holder.itemLiulanButn.setText("继续观看");
                holder.itemLiulanButn.setTextColor(0xFFffffff);
                holder.itemLiulanButn.setBackgroundResource(R.mipmap.green_state);
                holder.itemLiulanState.setText("上次观看至"+ TimeH.formatTime(Long.valueOf(myLive.getCourse().getWatchTime()))+"处");
                LogUtils.i("播放判断浏览记录拿到的时间长度", String.valueOf((myLive.getCourse().getWatchTime())));
            }else {
                //helper.setTextColor(R.id.item_liulan_butn,R.color.text_999999);
                holder.itemLiulanButn.setText("重新观看");
                holder.itemLiulanButn.setTextColor(0xFF999999);
                holder.itemLiulanButn.setBackgroundResource(R.mipmap.touming_huise_state);
                holder.itemLiulanState.setText("已看完");
            }
        }
        //按钮点击
        holder.itemLiulanButn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (NoFastClickUtils.isFastClick()) {
                } else {
                    LogUtils.i("播放判断点击继续播放传回去视频详情的毫秒值",myLive.getCourse().getWatchTime()+"");
                    if (myLive.getCourse().getIsImageText()==0) {
                        Intent intent = new Intent(context, YingXinagVideoDetailsActivity.class);
                        Bundle bundle = new Bundle();
                        //视频和当前时长
                        bundle.putString("courseId", String.valueOf(myLive.getCourse().getCourseId()));
                        bundle.putString("watchTime", String.valueOf(myLive.getCourse().getWatchTime()));
                        LogUtils.i("+++++++++++++++++++++++++",String.valueOf(myLive.getCourse().getWatchTime()));
                        intent.putExtras(bundle);
                        //这里一定要获取到所在Activity再startActivity()；
                        context.startActivity(intent);
                    }
                    else {
                        Intent intent = new Intent(context, PictrueDetailsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("courseId", String.valueOf(myLive.getCourse().getCourseId()));
                        intent.putExtras(bundle);
                        //这里一定要获取到所在Activity再startActivity()；
                        context.startActivity(intent);
                    }
                }
            }
        });

        if (mEditMode == MYLIVE_MODE_CHECK) {
            holder.checkBox.setVisibility(View.GONE);
        } else {
            holder.checkBox.setVisibility(View.VISIBLE);
            if (myLive.isSelect()) {
                holder.checkBox.setImageResource(R.drawable.checkbox_select);
            } else {
                holder.checkBox.setImageResource(R.drawable.checkbox_null);
            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClickListener(holder.getAdapterPosition(), mMyLiveList);
            }
        });
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


    public interface OnItemClickListener {
        void onItemClickListener(int pos, List<LiuLanBean.ContentBean.DataBean> myLiveList);
    }

    public void setEditMode(int editMode) {
        mEditMode = editMode;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.check_box)
        ImageView checkBox;
        @BindView(R.id.item_liulan_photo)
        ImageView itemLiulanPhoto;
        @BindView(R.id.item_liulan_title)
        TextView itemLiulanTitle;
        @BindView(R.id.item_liulan_state)
        TextView itemLiulanState;
        @BindView(R.id.item_liulan_time)
        TextView itemLiulanTime;
        @BindView(R.id.item_liulan_butn)
        Button itemLiulanButn;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

/*
public class LiuLanAdapter extends BaseQuickAdapter<LiuLanBean.ContentBean.DataBean, BaseViewHolder> {

    private static final int MYLIVE_MODE_CHECK = 0;
    int mEditMode = MYLIVE_MODE_CHECK;
    private List<LiuLanBean.ContentBean.DataBean> mMyLiveList;

    public LiuLanAdapter(int layoutResId, @Nullable List<LiuLanBean.ContentBean.DataBean> data) {
        super(layoutResId, data);
    }

    public void notifyAdapter(List<LiuLanBean.ContentBean.DataBean> data, boolean isAdd) {
        if (!isAdd) {
            this.mMyLiveList = data;
        } else {
            this.mMyLiveList.addAll(data);
        }
        notifyDataSetChanged();
    }

    public List<LiuLanBean.ContentBean.DataBean> getMyLiveList() {
        if (mMyLiveList == null) {
            mMyLiveList = new ArrayList<>();
        }
        return mMyLiveList;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void convert(BaseViewHolder helper, LiuLanBean.ContentBean.DataBean item) {
        LiuLanBean.ContentBean.DataBean.CourseBean course = item.getCourse();
        //多选按钮
        ImageView checkBox = helper.getView(R.id.check_box);
        if (mEditMode == MYLIVE_MODE_CHECK) {
            checkBox.setVisibility(View.GONE);
        } else {
            checkBox.setVisibility(View.VISIBLE);

            if (course.isSelect()) {
                checkBox.setImageResource(R.drawable.checkbox_select);
            } else {
                checkBox.setImageResource(R.drawable.checkbox_null);
            }
        }


        //设置圆角图片
        Glide.with(mContext).load(course.getLogo()).crossFade().centerCrop().transform(new GlideRoundTransform(mContext, 5)).into((ImageView) helper.getView(R.id.item_liulan_photo));
        helper.setText(R.id.item_liulan_title, course.getCourseName());
        helper.setText(R.id.item_liulan_time, item.getWatchTime());
        Button item_liulan_butn = (Button)helper.getView(R.id.item_liulan_butn);
        if (item.getCourse().getIsEnd()==0){
            item_liulan_butn.setText("继续观看");
            item_liulan_butn.setTextColor(R.color.white);
            item_liulan_butn.setBackgroundResource(R.mipmap.green_state);
            helper.setText(R.id.item_liulan_state, "上次观看至"+ TimeH.formatTime(Long.valueOf(item.getPlayPosition()))+"处");
        }else {
            //helper.setTextColor(R.id.item_liulan_butn,R.color.text_999999);
            item_liulan_butn.setText("重新观看");
            item_liulan_butn.setTextColor(R.color.text_999999);
            item_liulan_butn.setBackgroundResource(R.mipmap.touming_huise_state);
            helper.setText(R.id.item_liulan_state, "已看完");
        }
    }

    public void setEditMode(int editMode) {
        mEditMode = editMode;
        notifyDataSetChanged();
    }
}
*/
