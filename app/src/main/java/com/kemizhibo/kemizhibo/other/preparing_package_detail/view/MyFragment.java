package com.kemizhibo.kemizhibo.other.preparing_package_detail.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.config.Constants;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.PreparingPackageDetailBean;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.RequestUtil;
import com.kemizhibo.kemizhibo.yhr.widgets.TapBarLayout;

import butterknife.BindView;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by asus on 2018/8/16.
 */

public class MyFragment extends Fragment {

    JZVideoPlayerStandard jzVideoPlayerStandard;
    private TextView adjshipin;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shipinitem, container, false);
        jzVideoPlayerStandard = view.findViewById(R.id.jcVideoPlayer_video);
        adjshipin = view.findViewById(R.id.adj);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle arguments = getArguments();
        String url = arguments.getString("url");
        int courseid = arguments.getInt("courseid");
        RequestUtil.requestSuCaiVideo((Activity) getContext(), courseid,jzVideoPlayerStandard);
    }

    @Override
    public void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

}
