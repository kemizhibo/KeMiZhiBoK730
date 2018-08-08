package com.kemizhibo.kemizhibo.yhr.adapter.personcenteradapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.CollectionBoxBean;
import com.kemizhibo.kemizhibo.yhr.utils.immersion.GlideRoundTransform;
import java.util.List;

/**
 * Author: 闫浩然
 * Date: on 2018/7/31.
 * Describe: 收藏夹适配器
 */

public class CollectionBoxAdapter extends BaseQuickAdapter<CollectionBoxBean.ContentBean.DataBean.CourseBean, BaseViewHolder> {

    public CollectionBoxAdapter(int layoutResId, @Nullable List<CollectionBoxBean.ContentBean.DataBean.CourseBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CollectionBoxBean.ContentBean.DataBean.CourseBean item) {
        //赋值
        // 加载网络图片
        /*Glide.with(mContext)
                .load(item.getLogo())
                .crossFade()
                .into((ImageView) helper.getView(R.id.yinxiang_recyclerview_imageview));
*/
        //设置圆角图片
        Glide.with(mContext).load(item.getLogo()).crossFade().centerCrop().transform(new GlideRoundTransform(mContext, 5)).into((ImageView) helper.getView(R.id.collection_box_recyclerview_imageview));
        helper.setText(R.id.collection_box_item_title, item.getCourseName());
    }
}
