package com.kemizhibo.kemizhibo.yhr.adapter.homepageadapter;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
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
        SimpleDraweeView simpleDraweeView = helper.getView(R.id.material_recommended_imageview);
        simpleDraweeView.setImageURI(Uri.parse(item.getLogo()));
        helper.setText(R.id.material_recommended_title,item.getCourseName());
        if (item.getIsImageText() == 1) {
            helper.getView(R.id.collection_box_tuji).setVisibility(View.VISIBLE);
            helper.getView(R.id.collection_box_play_butn).setVisibility(View.GONE);
        }else {
            if (item.getFileType().equals("VIDEO")){
                helper.getView(R.id.collection_box_tuji).setVisibility(View.GONE);
                helper.getView(R.id.collection_box_play_butn).setVisibility(View.VISIBLE);
            }else {
                //直播图标
            }
        }
    }

}
