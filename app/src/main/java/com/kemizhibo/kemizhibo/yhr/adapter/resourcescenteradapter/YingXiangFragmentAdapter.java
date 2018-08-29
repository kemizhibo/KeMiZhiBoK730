package com.kemizhibo.kemizhibo.yhr.adapter.resourcescenteradapter;

/**
 * Author: yhr
 * Date: on 2018/7/3.
 * Describe:
 */
import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
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
        //.crossFade().centerCrop().transform(new GlideRoundTransform(mContext, 5))
        //Glide.with(mContext).load(item.getLogo()).transform(new GlideRoundTransform(mContext, 5)).error(R.mipmap.milier).into((ImageView) helper.getView(R.id.yinxiang_recyclerview_imageview));
        SimpleDraweeView simpleDraweeView = helper.getView(R.id.yinxiang_recyclerview_imageview);
        simpleDraweeView.setImageURI(Uri.parse(item.getLogo()));
        helper.setText(R.id.yinxiang_item_title, item.getCourseName());
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
