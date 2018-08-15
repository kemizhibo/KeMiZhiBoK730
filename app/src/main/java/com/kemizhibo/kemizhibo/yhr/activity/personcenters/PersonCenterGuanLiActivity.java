package com.kemizhibo.kemizhibo.yhr.activity.personcenters;

import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.widgets.TapBarLayout;
import butterknife.BindView;

public class PersonCenterGuanLiActivity extends BaseActivity {


    @BindView(R.id.public_title_bar_root)
    TapBarLayout publicTitleBarRoot;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_person_center_guan_li;
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
        publicTitleBarRoot.changeTitleBar("教学管理");
        publicTitleBarRoot.buildFinish();
    }

}
