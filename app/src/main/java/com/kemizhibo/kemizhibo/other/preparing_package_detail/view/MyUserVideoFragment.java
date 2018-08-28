package com.kemizhibo.kemizhibo.other.preparing_package_detail.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
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

import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.config.Constants;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.RequestUtil;
import com.kemizhibo.kemizhibo.other.web.CommonWebActivity;

import java.net.HttpURLConnection;
import java.net.URL;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by asus on 2018/8/16.
 */

public class MyUserVideoFragment extends Fragment {

    JZVideoPlayerStandard jzVideoPlayerStandard;
    private TextView adjshipin;
    private Button btn;
    private int usercourseid;
    private int usermoduleid;
    private String url;
    private String logo;
    private String introduce;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shipinitem, container, false);
        jzVideoPlayerStandard = view.findViewById(R.id.jcVideoPlayer_video);
        adjshipin = view.findViewById(R.id.adj);
        btn = view.findViewById(R.id.btn);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle arguments = getArguments();
        usercourseid = arguments.getInt("usercourseid",0);
        usermoduleid = arguments.getInt("usermoduleid",0);
        url = arguments.getString("url");
        //loadLogo();
        logo = arguments.getString("logo");
        introduce = arguments.getString("introduce");
        /*if (usercourseid !=0){
            RequestUtil.requestSuCaiVideo((Activity) getContext(), usercourseid,jzVideoPlayerStandard);
        }*/
        loadLogo();
        jzVideoPlayerStandard.setUp(url
                , 1, "");
        jzVideoPlayerStandard.thumbImageView.setImageURI(Uri.parse(logo));
        adjshipin.setText(TextUtils.isEmpty(introduce) ? "暂无" : introduce);
        Log.i("===usercourseid", usercourseid +"");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getContext(), CommonWebActivity.class);
                intent.putExtra(CommonWebActivity.OPERATE_KEY, CommonWebActivity.MAKE);
                intent.putExtra(Constants.COURSE_ID, usercourseid);
                intent.putExtra(Constants.MODULE_ID, usermoduleid);
                getContext().startActivity(intent);
            }
        });
    }

    private void loadLogo() {
        if(null == logo){
            return;
        }
        new AsyncTask<Void, Void, Bitmap>(){

            @Override
            protected Bitmap doInBackground(Void... voids) {
                try {
                    URL loadUrl = new URL(logo);
                    HttpURLConnection connection = (HttpURLConnection) loadUrl.openConnection();
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    int responseCode = connection.getResponseCode();
                    if(responseCode == 200){
                        return BitmapFactory.decodeStream(connection.getInputStream());
                    }else{
                        return null;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(final Bitmap bitmap) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        jzVideoPlayerStandard.thumbImageView.setImageBitmap(bitmap);
                    }
                });
            }
        }.execute();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

}
