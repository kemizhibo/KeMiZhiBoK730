package com.kemizhibo.kemizhibo.yhr.adapter.resourcescenteradapter;

/**
 * Author: yhr
 * Date: on 2018/7/3.
 * Describe:
 */

import android.support.annotation.Nullable;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.YingXiangFragmentBean;
import com.kemizhibo.kemizhibo.yhr.utils.immersion.GlideRoundTransform;

import java.util.List;

public class YingXiangFragmentAdapter extends BaseQuickAdapter<YingXiangFragmentBean.ContentBean.DataBean, BaseViewHolder> {

    public YingXiangFragmentAdapter(int layoutResId, @Nullable List<YingXiangFragmentBean.ContentBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, YingXiangFragmentBean.ContentBean.DataBean item) {
        //赋值
        // 加载网络图片
        /*Glide.with(mContext)
                .load(item.getLogo())
                .crossFade()
                .into((ImageView) helper.getView(R.id.yinxiang_recyclerview_imageview));
*/
        //设置圆角图片
        Glide.with(mContext).load(item.getLogo()).error(R.mipmap.milier).crossFade().centerCrop().transform(new GlideRoundTransform(mContext, 5)).into((ImageView) helper.getView(R.id.yinxiang_recyclerview_imageview));
        helper.setText(R.id.yinxiang_item_title, (String)item.getTitle());
    }

}
