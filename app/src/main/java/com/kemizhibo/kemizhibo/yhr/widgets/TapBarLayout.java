package com.kemizhibo.kemizhibo.yhr.widgets;

/**
 * Created by jorryLiu on 2015/a1111/a1111.
 * liujiawei@puhuifinance.com
 */

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.utils.ScreenUtils;

import java.util.Calendar;

/**
 * Author: yhr
 * Date: 2018/5/17
 * Describe: 两边图片，中间文字或图片的titleBar
 */
public class TapBarLayout extends RelativeLayout {
    //左边layout
    private LinearLayout mLeftLinearLayout;
    //右边layout
    private LinearLayout mRightLinearLayout;
    private LinearLayout mRightLinearLayout2;
    //中间文字标题
    private TextView titleBarTitle;
    //中间图片标题
    private ImageView imgViewTitle;
    private Context mContext;
//    private ShopCardNumImg imageView;
    private String title;
    private int leftResId;
    private int rightResId;
    private LeftOnClickListener mLeftOnClickListener;
    private RightOnClickListener mRightOnClickListener;
    private TextView mLeftTv;//左边文字
    private OnClickListener mLeftTvListen;//左边文字 监听
    private ImageView rightImageView;


    /**
     * 移除图
     * author: sqq
     * created at 2016/7/4 18:01
     */
    public void removeAllViews() {
        mLeftLinearLayout.removeAllViews();
        mRightLinearLayout.removeAllViews();
    }

    public TapBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        onFinishInflate();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mLeftLinearLayout = (LinearLayout) findViewById(R.id.public_title_bar_left);
        mRightLinearLayout = (LinearLayout) findViewById(R.id.public_title_bar_right);
        mRightLinearLayout2 = (LinearLayout) findViewById(R.id.public_title_bar_right2);
        titleBarTitle = (TextView) findViewById(R.id.titleBarTitle);
        mLeftTv = (TextView) findViewById(R.id.public_title_bar_left_tv);
        imgViewTitle = (ImageView) findViewById(R.id.ic_title);

    }


    public void setTitleColor(int color) {
        titleBarTitle.setTextColor(color);
    }

    public TapBarLayout setTitleBarTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * 显示左边文字
     * text 文字，没有时 传null
     * color 文字颜色 ，没有时 传0
     * author: sqq
     * created at 2016/7/6 15:30
     */
    public void setLeftText(String text, int color) {
        mLeftLinearLayout.setVisibility(GONE);
        mLeftTv.setVisibility(VISIBLE);
        if (!TextUtils.isEmpty(text)) {
            mLeftTv.setText(text);
        }
        if (0 != color) {
            mLeftTv.setTextColor(color);
        }
    }

    /**
     * 显示右边文字
     * text 文字，没有时 传null
     * color 文字颜色 ，没有时 传0
     * author: sqq
     * created at 2016/7/6 16:30
     */
    public void setRightText(String text, int color) {
        mRightLinearLayout.setVisibility(VISIBLE);
        TextView textView = new TextView(getContext());
        if (!TextUtils.isEmpty(text)) {
            textView.setText(text);
        }
        if (0 != color) {
            textView.setTextColor(color);
        }
        textView.setGravity(Gravity.RIGHT|Gravity.CENTER_VERTICAL);
        textView.setTextSize(20);
        ViewGroup.LayoutParams lp = mRightLinearLayout.getLayoutParams();
        lp.width= ScreenUtils.getScreenWidthPixels(getContext())/3+dip2px(getContext(),20);
        mRightLinearLayout.setLayoutParams(lp);
        mRightLinearLayout.setPadding(0, 0, dip2px(getContext(),20), 0);

        mRightLinearLayout.addView(textView);
    }

    /**
     * 隐藏左边文字
     * author: sqq
     * created at 2016/7/a22 9:32
     */
    public void setLeftTextGone() {
        mLeftTv.setVisibility(GONE);
    }

    /**
     * 左边文字 监听
     * author: sqq
     * created at 2016/7/a22 9:42
     */
    public void setLeftTextListen(OnClickListener onClickListener) {
        mLeftTvListen = onClickListener;
    }

    /**
     * 标题为图片
     * author: sqq
     * created at 2016/7/4 18:03
     */
    public void setTitleImag() {
        imgViewTitle.setVisibility(View.VISIBLE);
        titleBarTitle.setVisibility(View.GONE);
    }

    public void changeTitleBar(String title) {
        if (title == null) {
            title = getResources().getString(R.string.app_name);
        }
        titleBarTitle.setText(title);
    }

    //左边图 不可见
    public void setLeftInvisible() {
        mLeftLinearLayout.setVisibility(INVISIBLE);
    }

    //右边图 不可见
    public void setRightInvisible() {
        mRightLinearLayout.setVisibility(INVISIBLE);
    }

    public void setListener() {
        mLeftLinearLayout.setOnClickListener(new NoDoubleClickListener());
        mRightLinearLayout.setOnClickListener(new NoDoubleClickListener());
        mRightLinearLayout2.setOnClickListener(new NoDoubleClickListener());
        if (null != mLeftTvListen) mLeftTv.setOnClickListener(mLeftTvListen);
    }

    public TapBarLayout setLeftImageResouse(int resouse) {
        this.leftResId = resouse;
        return this;
    }

    public void setRightImageResouse(int resouse) {
        this.rightResId = resouse;
    }

    public void buildFinish() {
        if (!TextUtils.isEmpty(title)) {
            titleBarTitle.setText(title);
        }
        if (leftResId != 0) {
            ImageView imageView = new ImageView(mContext);
            imageView.setImageResource(leftResId);

            LinearLayout.LayoutParams rl = new LinearLayout.LayoutParams(-2, -2);
            rl.gravity = Gravity.CENTER;
            mLeftLinearLayout.addView(imageView, rl);
        }
        if (rightResId != 0) {
            rightImageView = new ImageView(mContext);
            rightImageView.setImageResource(rightResId);
            mRightLinearLayout2.setVisibility(View.VISIBLE);

            LinearLayout.LayoutParams rl = new LinearLayout.LayoutParams(60, 15);
            rl.gravity = Gravity.CENTER;
            mRightLinearLayout2.addView(rightImageView, rl);
        }
        setListener();
    }
//
//    //设置购物车图
//    public void setChardImage(int imageId) {
//        mRightLinearLayout.removeAllViews();
//        imageView = new ShopCardNumImg(mContext, imageId);
//        if (0 != imageId) {
//            imageView.setImageResource(imageId);
//        } else {
//            imageView.setImageResource(R.drawable.main_shopping_car);
//        }
//        mRightLinearLayout.setVisibility(View.VISIBLE);
//        LinearLayout.LayoutParams rl = new LinearLayout.LayoutParams(dip2px(mContext, 40), -2);
//        rl.gravity = Gravity.CENTER;
//        mRightLinearLayout.addView(imageView, rl);
//    }
//
//    public void setShopCardNum(String num) {
//        imageView.setCount(num);
//    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


    /**
     * 坐标文字
     *
     * @param rightStr
     */
    public void addRightViewTextView(String rightStr) {
        TextView tv = new TextView(mContext);
        tv.setText(rightStr);

        LinearLayout.LayoutParams rl = new LinearLayout.LayoutParams(-2, -2);
        rl.gravity = Gravity.CENTER;
        mRightLinearLayout.addView(tv, rl);
    }


    public class NoDoubleClickListener implements OnClickListener {

        public static final int MIN_CLICK_DELAY_TIME = 1000;
        private long lastClickTime = 0;

        @Override
        public void onClick(View view) {
            long currentTime = Calendar.getInstance().getTimeInMillis();
            if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
                lastClickTime = currentTime;

                if (mLeftLinearLayout == view) {
                    if (mLeftOnClickListener != null) {
                        mLeftOnClickListener.onClick();
                    }
                } else if (mRightLinearLayout2 == view) {
                    if (mRightOnClickListener != null) {
                        mRightOnClickListener.onClick();
                    }
                }
            }
        }
    }

    public ImageView getRightImageView(){
        return rightImageView;
    }

    public TapBarLayout setLeftLinearLayoutListener(LeftOnClickListener leftOnClickListener) {
        this.mLeftOnClickListener = leftOnClickListener;
        return this;
    }

    public void setRightLinearLayoutListener(RightOnClickListener rightOnClickListener) {

        this.mRightOnClickListener = rightOnClickListener;
    }

    public interface LeftOnClickListener {
        public void onClick();
    }

    public interface RightOnClickListener {
        public void onClick();
    }
}
