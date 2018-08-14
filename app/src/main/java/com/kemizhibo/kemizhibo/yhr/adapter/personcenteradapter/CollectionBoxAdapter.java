package com.kemizhibo.kemizhibo.yhr.adapter.personcenteradapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.CollectionBoxBean;
import com.kemizhibo.kemizhibo.yhr.utils.immersion.GlideRoundTransform;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author: 闫浩然
 * Date: on 2018/7/31.
 * Describe: 收藏夹适配器
 */

public class CollectionBoxAdapter extends RecyclerView.Adapter<CollectionBoxAdapter.ViewHolder> {

    private static final int MYLIVE_MODE_CHECK = 0;
    int mEditMode = MYLIVE_MODE_CHECK;

    private Context context;
    private List<CollectionBoxBean.ContentBean.DataBean> mMyLiveList;
    private OnItemClickListener mOnItemClickListener;

    public CollectionBoxAdapter(Context context) {
        this.context = context;
    }


    public void notifyAdapter(List<CollectionBoxBean.ContentBean.DataBean> myLiveList, boolean isAdd) {
        if (!isAdd) {
            this.mMyLiveList = myLiveList;
        } else {
            this.mMyLiveList.addAll(myLiveList);
        }
        notifyDataSetChanged();
    }

    public List<CollectionBoxBean.ContentBean.DataBean> getMyLiveList() {
        if (mMyLiveList == null) {
            mMyLiveList = new ArrayList<>();
        }
        return mMyLiveList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.collection_box_item_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return mMyLiveList.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final CollectionBoxBean.ContentBean.DataBean myLive = mMyLiveList.get(holder.getAdapterPosition());
        //填充值
        Glide.with(context).load(myLive.getCourse().getLogo()).crossFade().centerCrop().transform(new GlideRoundTransform(context, 5)).into(holder.collectionBoxRecyclerviewImageview);
        holder.collectionBoxItemTitle.setText(myLive.getCourse().getCourseName());


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
        void onItemClickListener(int pos, List<CollectionBoxBean.ContentBean.DataBean> myLiveList);
    }

    public void setEditMode(int editMode) {
        mEditMode = editMode;
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.collection_box_recyclerview_imageview)
        ImageView collectionBoxRecyclerviewImageview;
        @BindView(R.id.collection_box_item_title)
        TextView collectionBoxItemTitle;
        @BindView(R.id.check_box)
        ImageView checkBox;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /*public CollectionBoxAdapter(int layoutResId, @Nullable List<CollectionBoxBean.ContentBean.DataBean.CourseBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CollectionBoxBean.ContentBean.DataBean.CourseBean item) {
        //设置圆角图片
        Glide.with(mContext).load(item.getLogo()).crossFade().centerCrop().transform(new GlideRoundTransform(mContext, 5)).into((ImageView) helper.getView(R.id.collection_box_recyclerview_imageview));
        helper.setText(R.id.collection_box_item_title, item.getCourseName());
    }*/
}
