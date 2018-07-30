package com.kemizhibo.kemizhibo.other.preparing_package_detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;

import butterknife.BindView;

public class PreparingPackageDetailActivity extends BaseActivity {
    @BindView(R.id.back_img)
    ImageView back;
    @BindView(R.id.list_view)
    ListView listView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_preparing_package_detail;
    }

    @Override
    protected void initData() {

    }

}
