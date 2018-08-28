package com.kemizhibo.kemizhibo.yhr.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.PictureBean;
import com.kemizhibo.kemizhibo.yhr.utils.immersion.GlideRoundTransform;

import java.util.List;

/**
 * Author: 闫浩然
 * Date: on 2018/8/26.
 * Describe:图文详情适配器
 */

public class ViewPagerAdapter extends PagerAdapter{
    private Context mContext;
    private List<PictureBean.ContentBean.ImageTextListBean> mData;

    public ViewPagerAdapter(Context context ,List<PictureBean.ContentBean.ImageTextListBean> list) {
        mContext = context;
        mData = list;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(mContext, R.layout.view_pager_item,null);
        ImageView pictrue_details_viewpager = (ImageView) view.findViewById(R.id.pictrue_details_viewpager);
        TextView pictrue_details_txt = (TextView) view.findViewById(R.id.pictrue_details_txt);
        for (int i = 0;i<mData.get(position).getImgList().size();i++){
            String s = mData.get(position).getImgList().get(i);
            Glide.with(mContext).load(s).error(R.mipmap.milier).transform(new GlideRoundTransform(mContext, 5)).placeholder(R.mipmap.milier).into(pictrue_details_viewpager);
        }
        pictrue_details_txt.setText(mData.get(position).getText());
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
