package com.kemizhibo.kemizhibo.yhr.activity.personcenters;

import android.os.Bundle;

import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.widgets.TapBarLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonCenterSheZhiActivity extends BaseActivity {


    @BindView(R.id.public_title_bar_root)
    TapBarLayout publicTitleBarRoot;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_person_center_she_zhi;
    }

    @Override
    protected void initData() {
        bindTitleBar();
    }

    private void bindTitleBar() {
        publicTitleBarRoot.setLeftImageResouse(R.drawable.ic_back).setLeftLinearLayoutListener(new TapBarLayout.LeftOnClickListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
        publicTitleBarRoot.changeTitleBar("意见反馈");
        publicTitleBarRoot.buildFinish();
    }
}
