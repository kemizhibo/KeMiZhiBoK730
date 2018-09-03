package com.kemizhibo.kemizhibo.yhr.fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.LoadingPager;
import com.kemizhibo.kemizhibo.yhr.base.BaseFragment;
import com.kemizhibo.kemizhibo.yhr.utils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author: 闫浩然
 * Date: on 2018/7/27.
 * Describe:测试fragment
 */

public class DemoFragment extends BaseFragment {
    private TextView textView;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    @Override
    public int getEmptyPageLayoutId() {
        return 0;
    }

    @Override
    public View createSuccessView() {
        View view = UIUtils.inflate(mActivity,R.layout.demo);
        //TextView textView = new TextView(getContext());
        textView = view.findViewById(R.id.textssss);
        textView.setText("11111111111111111111111111111111");
        return view;
    }
    @Override
    public void load() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(200);
                setState(LoadingPager.LoadResult.success);
            }
        }).start();
    }
}
