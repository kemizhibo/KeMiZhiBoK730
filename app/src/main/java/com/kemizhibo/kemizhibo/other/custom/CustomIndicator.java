package com.kemizhibo.kemizhibo.other.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kemizhibo.kemizhibo.R;

/**
 * Created by Administrator on 2018/7/30.
 */

public class CustomIndicator extends HorizontalScrollView{
    private static final int COLOR_INDICATOR_COLOR = Color.BLACK;

    private Context context;
    private String[] titles;
    private int count;
    private Paint mPaint;
    private float mTranslationX;
    private float lineheight = 0.0f;
    private OnIndicatorClickListener listener;
    private int selectIndex = -1;

    public CustomIndicator(Context context) {
        this(context, null);
    }

    public CustomIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        this.context = context;
        mPaint = new Paint();
        mPaint.setColor(COLOR_INDICATOR_COLOR);
        mPaint.setStrokeWidth(lineheight);//底部指示线的宽度
        setHorizontalScrollBarEnabled(false);
    }

    public void setTitles(String[] titles){
        this.titles = titles;
        count = titles.length;
        generateTitleView();
    }

    public void setSelectIndex(int selectI){
        selectIndex = selectI;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void dispatchDraw(Canvas canvas)
    {
        super.dispatchDraw(canvas);
        canvas.save();
        canvas.translate(mTranslationX, getHeight() - lineheight);
        //canvas.drawLine(0, 0, tabWidth, 0, mPaint);//（startX, startY, stopX, stopY, paint）
        canvas.restore();
    }

    private void generateTitleView()
    {
        if (getChildCount() > 0)
            this.removeAllViews();
        count = titles.length;

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        for (int i = 0; i < count; i++)
        {
            TextView tv = new TextView(getContext());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            tv.setPadding(50, 0, 25, 0);
            tv.setGravity(Gravity.CENTER_VERTICAL);
            tv.setTextColor(selectIndex == i ? getResources().getColor(R.color.filter_text_select) : getResources().getColor(R.color.filter_text_normal));
            tv.setText(titles[i]);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);//字体大小
            tv.setLayoutParams(lp);
            final int finalI = i;
            tv.setOnClickListener(new OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    selectIndex = finalI;
                    generateTitleView();
                    if(listener != null){
                        listener.onIndicatorClick(finalI);
                    }
                }
            });
            linearLayout.addView(tv);
        }
        addView(linearLayout);
    }

    public void setOnIndicatorClickListener(OnIndicatorClickListener listener){
        this.listener = listener;
    }

    public interface OnIndicatorClickListener{
        void onIndicatorClick(int position);
    }

}
