package com.kemizhibo.kemizhibo.yhr.fragment.forteaching;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.LoadingPager;
import com.kemizhibo.kemizhibo.yhr.base.BaseFragment;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;
import com.kemizhibo.kemizhibo.yhr.utils.UIUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author: yhr
 * Date: on 2018/6/19.
 * Describe:备授课的授课中心
 */

public class ForTeanchingFirstFragment extends BaseFragment {
    @BindView(R.id.forteaching_shaixuan_imageview)
    ImageView forteachingShaixuanImageview;
    @BindView(R.id.forteaching_shaixuan_butn)
    RelativeLayout forteachingShaixuanButn;
    @BindView(R.id.forteaching_shaixuan_layout)
    RelativeLayout forteachingShaixuanLayout;
    //隐藏和显示布局模块
    private boolean isOpen = false;
    ValueAnimator valueAnimator = null;
    private ViewGroup.LayoutParams layoutParams;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    @Override
    public View createSuccessView() {
        View view = UIUtils.inflate(R.layout.forteaching_first_fragment);
        ButterKnife.bind(this, view);
        //隐藏和显示布局
        initView();
        return view;
    }

    @Override
    public void load() {
        setState(LoadingPager.LoadResult.success);
    }


    @OnClick(R.id.forteaching_shaixuan_butn)
    public void onViewClicked() {
        expend();
    }

    private void initView() {
        //测量完整展示的高度
        getLongHeight();
        //不展示的高度
        layoutParams = forteachingShaixuanLayout.getLayoutParams();
        layoutParams.height = 0;
        forteachingShaixuanLayout.setLayoutParams(layoutParams);

    }

    /*@Override
    public void onHiddenChanged(boolean hidd) {
        super.onHiddenChanged(hidd);
        if (!hidd) {
            expend();
        } else {
            ToastUtils.showToast("影藏时所做的事情");
            isOpen = false;
            valueAnimator = ValueAnimator.ofInt(getLongHeight(), 0);
        }
    }*/

    private int getLongHeight() {
        forteachingShaixuanLayout.measure(0, 0);
        return forteachingShaixuanLayout.getMeasuredHeight();
    }

    private void expend() {
        if (!isOpen) {
            //点击前是关闭状态,点击后就扩展拉开
            isOpen = true;
            valueAnimator = ValueAnimator.ofInt(0, getLongHeight());
        } else {
            //点击前是开启状态,点击后就缩回去
            isOpen = false;
            valueAnimator = ValueAnimator.ofInt(getLongHeight(), 0);
        }

        if (valueAnimator != null) {
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int height = (Integer) valueAnimator.getAnimatedValue();
                    layoutParams.height = height;
                    forteachingShaixuanLayout.setLayoutParams(layoutParams);
                }
            });
            valueAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    if (isOpen) {
                        //扩展开箭头向上
                        forteachingShaixuanImageview.setImageResource(R.mipmap.shouhui_2);
                    } else {
                        //收缩时候向下
                        forteachingShaixuanImageview.setImageResource(R.mipmap.xiala_2);
                    }
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        }
        valueAnimator.setDuration(100);
        valueAnimator.start();
    }

}
