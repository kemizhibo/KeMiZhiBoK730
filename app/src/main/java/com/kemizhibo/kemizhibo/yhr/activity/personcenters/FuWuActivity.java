package com.kemizhibo.kemizhibo.yhr.activity.personcenters;

import android.os.Bundle;
import android.widget.TextView;

import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.widgets.TapBarLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FuWuActivity extends BaseActivity {

    @BindView(R.id.public_title_bar_root)
    TapBarLayout publicTitleBarRoot;
    @BindView(R.id.fuwu_text)
    TextView fuwuText;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fu_wu;
    }

    @Override
    protected void initData() {
        bindTitleBar();
        fuwuText.setText("4564646464864864864674864+4896486");
    }

    private void bindTitleBar() {
        publicTitleBarRoot.setLeftImageResouse(R.drawable.ic_back).setLeftLinearLayoutListener(new TapBarLayout.LeftOnClickListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
        publicTitleBarRoot.changeTitleBar("使用协议");
        publicTitleBarRoot.buildFinish();
    }
}
