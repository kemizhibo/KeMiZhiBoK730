package com.kemizhibo.kemizhibo.yhr.activity.personcenters;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.activity.logins.FinishActivity;
import com.kemizhibo.kemizhibo.yhr.activity.logins.LoginActivity;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.widgets.TapBarLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangePhoneFinishActivity extends BaseActivity {


    @BindView(R.id.public_title_bar_root)
    TapBarLayout publicTitleBarRoot;
    @BindView(R.id.daojishi)
    TextView daojishi;
    int count = 3;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (count < 0 ) {// 跳转
                startActivity(new Intent(ChangePhoneFinishActivity.this, LoginActivity.class));
                ChangePhoneFinishActivity.this.finish();
                finish();
            } else {// 倒计时处理
                SpannableStringBuilder style = new SpannableStringBuilder(count+"s后跳到登录页面");
                style.setSpan(new ForegroundColorSpan(Color.RED), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                daojishi.setText(style);
                count--;
                handler.sendEmptyMessageDelayed(99, 1000);
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_phone_finish;
    }

    @Override
    protected void initData() {
        bindTitleBar();
        handler.sendEmptyMessageDelayed(99,1000);
    }

    private void bindTitleBar() {
        publicTitleBarRoot.setLeftImageResouse(R.drawable.ic_back).setLeftLinearLayoutListener(new TapBarLayout.LeftOnClickListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
        publicTitleBarRoot.changeTitleBar("修改手机号");
        publicTitleBarRoot.buildFinish();
    }
}
