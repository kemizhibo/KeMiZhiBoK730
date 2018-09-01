package com.kemizhibo.kemizhibo.other.preparing_package_detail.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.config.Constants;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.RequestUtil;
import com.kemizhibo.kemizhibo.other.web.CommonWebActivity;

import cn.jzvd.JZVideoPlayer;

/**
 * Created by asus on 2018/8/16.
 */

public class MyUserPicFragment extends Fragment {

    SimpleDraweeView simpleDraweeView;
    private TextView adjPic;
    private Button btn;
    private int moduleId;
    private int courseid;
    private String url;
    private String introduce;
    private String title;
    private TextView titleText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_picitem, container, false);
        simpleDraweeView = view.findViewById(R.id.simpledraweeview_pic);
        adjPic = view.findViewById(R.id.adj);
        btn = view.findViewById(R.id.btn);
        titleText = view.findViewById(R.id.title_333);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle arguments = getArguments();
        moduleId = arguments.getInt("moduleid");
        courseid = arguments.getInt("courseid");
        url = arguments.getString("url");
        introduce = arguments.getString("introduce");
        title = arguments.getString("title");
        titleText.setText(title);

        Log.i("userpiccouseid",courseid+"");
        adjPic.setText(TextUtils.isEmpty(introduce) ? "暂无" : introduce);
        simpleDraweeView.setImageURI(Uri.parse(url));
        //RequestUtil.requestSuCaiPic((Activity) getContext(), moduleId,6,simpleDraweeView);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getContext(), CommonWebActivity.class);
                intent.putExtra(CommonWebActivity.OPERATE_KEY, CommonWebActivity.MAKE);
                intent.putExtra(Constants.COURSE_ID, courseid);
                intent.putExtra(Constants.MODULE_ID, moduleId);
                getContext().startActivity(intent);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

}
