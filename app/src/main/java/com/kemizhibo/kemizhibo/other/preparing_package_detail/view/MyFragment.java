package com.kemizhibo.kemizhibo.other.preparing_package_detail.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.config.Constants;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.PreparingPackageDetailBean;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.RequestUtil;
import com.kemizhibo.kemizhibo.other.web.CommonWebActivity;
import com.kemizhibo.kemizhibo.yhr.widgets.TapBarLayout;

import java.util.List;

import butterknife.BindView;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by asus on 2018/8/16.
 */

public class MyFragment extends Fragment {

    JZVideoPlayerStandard jzVideoPlayerStandard;
    private TextView adjshipin;
    private Button btn;
    private int courseid;
    private int moduleid;
    private View convertView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(convertView != null){
            ViewGroup parent = (ViewGroup) convertView.getParent();
            if (parent != null) {
                parent.removeView(convertView);
            }
            return convertView;
        }else{
            convertView = inflater.inflate(R.layout.fragment_shipinitem, container, false);
            jzVideoPlayerStandard = convertView.findViewById(R.id.jcVideoPlayer_video);
            adjshipin = convertView.findViewById(R.id.adj);
            btn = convertView.findViewById(R.id.btn);
        }

        return convertView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle arguments = getArguments();
        courseid = arguments.getInt("courseid");
        moduleid = arguments.getInt("moduleid");
        RequestUtil.requestSuCaiVideo((Activity) getContext(), courseid,jzVideoPlayerStandard);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getContext(), CommonWebActivity.class);
                intent.putExtra(CommonWebActivity.OPERATE_KEY, CommonWebActivity.MAKE);
                intent.putExtra(Constants.COURSE_ID, courseid);
                intent.putExtra(Constants.MODULE_ID, moduleid);
                getContext().startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        JZVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
