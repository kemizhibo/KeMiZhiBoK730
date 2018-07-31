package com.kemizhibo.kemizhibo.yhr.widgets;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;
import com.kemizhibo.kemizhibo.yhr.utils.UIUtils;
import com.kemizhibo.kemizhibo.yhr.widgets.popup.BasePopup;

/**
 * Created by zyyoona7 on 2017/8/7.
 */

public class DynamicPopup extends BasePopup<DynamicPopup> {


    private RadioGroup rg_top ;
    private RadioButton rb_1 , rb_2 , rb_3 , rb_4 , rb_5 , rb_6;

    public static DynamicPopup create(){
        return new DynamicPopup();
    }

    @Override
    protected void initAttributes() {
        setContentView(R.layout.layout_dynamic_pop);
        setHeight(UIUtils.dip2px(50));
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setFocusAndOutsideEnable(true);
//                .setBackgroundDimEnable(true)
//                .setDimColor(Color.GRAY)
//                .setDimValue(0.3f)
//                .setAnimationStyle(R.style.TopPopAnim);
    }

    @Override
    protected void initViews(View view) {
        rg_top = findViewById(R.id.rg_top);
        rb_1 = findViewById(R.id.rb_1);
        rb_2 = findViewById(R.id.rb_2);
        rb_3 = findViewById(R.id.rb_3);
        rb_4 = findViewById(R.id.rb_4);
        rb_5 = findViewById(R.id.rb_5);
        rb_6 = findViewById(R.id.rb_6);

        rg_top.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_1://全部
                        ToastUtils.showToast("全部");
                        break;
                    case R.id.rb_2://经验分享
                        ToastUtils.showToast("经验分享");

                        break;
                    case R.id.rb_3://实验攻略
                        ToastUtils.showToast("实验攻略");

                        break;
                    case R.id.rb_4://熊孩子
                        ToastUtils.showToast("熊孩子");

                        break;
                    case R.id.rb_5://教材解读
                        ToastUtils.showToast("教材解读");

                        break;
                    case R.id.rb_6://其他
                        ToastUtils.showToast("其他");

                        break;
                }
            }
        });
        rg_top.check(R.id.rb_1);
    }


}
