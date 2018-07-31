package com.kemizhibo.kemizhibo.yhr.adapter.homepageadapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.bean.homepagerbean.HomePageBean;
import com.kemizhibo.kemizhibo.yhr.utils.immersion.GlideRoundTransform;
import java.util.List;

/**
 * Author: yhr
 * Date: on 2018/7/19.
 * Describe: 素材推荐适配器
 */

public class MaterialRecommendedAdapter extends BaseQuickAdapter<HomePageBean.ContentBean.ReturnMaterialBean, BaseViewHolder> {

    public MaterialRecommendedAdapter(int layoutResId, @Nullable List<HomePageBean.ContentBean.ReturnMaterialBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomePageBean.ContentBean.ReturnMaterialBean item) {
        Glide.with(mContext).load(item.getLogo()).crossFade().centerCrop().transform(new GlideRoundTransform(mContext, 5)).into((ImageView) helper.getView(R.id.material_recommended_imageview));
        helper.setText(R.id.material_recommended_title,item.getCourseName());
    }

}
