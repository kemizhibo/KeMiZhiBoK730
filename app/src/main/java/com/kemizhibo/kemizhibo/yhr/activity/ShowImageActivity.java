package com.kemizhibo.kemizhibo.yhr.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.widgets.HackyViewPager;
import com.kemizhibo.kemizhibo.yhr.widgets.TapBarLayout;
import com.kemizhibo.kemizhibo.yhr.widgets.photoview.PhotoView;
import com.kemizhibo.kemizhibo.yhr.widgets.photoview.PhotoViewAttacher;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Author: yhr
 * Date: 2018/5/17
 * Describe: 图片缩放展示
 */
public class ShowImageActivity extends BaseActivity {


    @BindView(R.id.viewPager)
    HackyViewPager viewPager;
    @BindView(R.id.public_title_bar_root)
    TapBarLayout publicTitleBarRoot;

    private int position;
    private ArrayList<String> list;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_show_image;
    }

    @Override
    protected void initData() {
        setStatusBar();
        getBundleData();
        bindTitleBar();

        initVP();
    }
    public void bindTitleBar() {
//        publicTitleBarRoot.setLeftImageResouse(R.drawable.ic_back_white).setLeftLinearLayoutListener(new TapBarLayout.LeftOnClickListener() {
//            @Override
//            public void onClick() {
//                finish();
//            }
//        });
        publicTitleBarRoot.changeTitleBar((position+1)+"/"+list.size());
        publicTitleBarRoot.setTitleColor(Color.WHITE);
        publicTitleBarRoot.buildFinish();
    }
    private void initVP() {
        viewPager.setAdapter(new MyAdapter());
        viewPager.setCurrentItem(position);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {

                publicTitleBarRoot.changeTitleBar((arg0+1)+"/"+list.size());
                int childCount = viewPager.getChildCount();

                for (int i = 0; i < childCount; i++) {
                    View childAt = viewPager.getChildAt(i);

                    try {
                        if (childAt != null && childAt instanceof PhotoView) {
                            PhotoView  photoView = (PhotoView) childAt;
                            PhotoViewAttacher attacher;
                            attacher = new PhotoViewAttacher(photoView);
                            attacher.update();
                            attacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                                @Override
                                public void onPhotoTap(View view, float x, float y) {
                                    finish();
                                }
                                @Override
                                public void onOutsidePhotoTap() {
                                }
                            });
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub
            }
        });
    }

    private void getBundleData() {
        Bundle bundle=  getIntent().getBundleExtra("data");
        position = bundle.getInt("position");
        list = bundle.getStringArrayList("list");
    }
    public class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            if(list != null) {
                return list.size();
            }else {
                return 0;
            }

        }
        @Override
        public View instantiateItem(ViewGroup container, int position) {
            final PhotoView photoView = new PhotoView(container.getContext());
            container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            Glide.with(ShowImageActivity.this).load(list.get(position)).into(photoView);
            photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {

                @Override
                public void onPhotoTap(View arg0, float arg1, float arg2) {
                    finish();
                }
                @Override
                public void onOutsidePhotoTap() {}

            });
            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
