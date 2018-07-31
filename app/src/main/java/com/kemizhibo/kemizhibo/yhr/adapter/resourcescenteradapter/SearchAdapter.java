package com.kemizhibo.kemizhibo.yhr.adapter.resourcescenteradapter;

/**
 * Author: yhr
 * Date: on 2018/7/3.
 * Describe:搜索中心的适配器
 */

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.SearchBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.YingXiangFragmentBean;
import com.kemizhibo.kemizhibo.yhr.utils.immersion.GlideRoundTransform;

import java.util.List;

public class SearchAdapter extends BaseQuickAdapter<SearchBean.ContentBean.DataBean, BaseViewHolder> {

    public SearchAdapter(int layoutResId, @Nullable List<SearchBean.ContentBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchBean.ContentBean.DataBean item) {
        //赋值
        // 加载网络图片
        /*Glide.with(mContext)
                .load(item.getLogo())
                .crossFade()
                .into((ImageView) helper.getView(R.id.yinxiang_recyclerview_imageview));
*/
        //设置圆角图片
        Glide.with(mContext).load(item.getLogo()).crossFade().centerCrop().transform(new GlideRoundTransform(mContext, 5)).into((ImageView) helper.getView(R.id.search_recyclerview_imageview));
        helper.setText(R.id.search_item_title, item.getCourseName());
    }


}
