package com.kemizhibo.kemizhibo.other.preparing_package_detail.view;

import android.app.Activity;
import android.content.Context;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.config.Constants;
import com.kemizhibo.kemizhibo.other.config.OkHttpRequest;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.PreparingPackageDetailBean;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.RequestUtil;
import com.kemizhibo.kemizhibo.other.web.CommonWebActivity;
import com.kemizhibo.kemizhibo.yhr.utils.UIUtils;
import com.kemizhibo.kemizhibo.yhr.widgets.TapBarLayout;

import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import retrofit2.http.Url;

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
    //private String url;
    private String logo;
    private String introduce;
    private int kpiontId;
    private String title;
    private TextView titleText;

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
            titleText = convertView.findViewById(R.id.title_222);
        }

        return convertView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle arguments = getArguments();
        courseid = arguments.getInt("courseid");
        moduleid = arguments.getInt("moduleid");
        /*
        bundle.putString("url", kemiVideo.get(position).getUrl());
                            bundle.putString("logo", kemiVideo.get(position).getVideoLogo());
                            bundle.putString("introduce", kemiVideo.get(position).getVideoIntroduce());
         */
        //url = arguments.getString("url");
        logo = arguments.getString("logo");
        introduce = arguments.getString("introduce");
        //RequestUtil.requestSuCaiVideo((Activity) getContext(), courseid,jzVideoPlayerStandard);
        /*jzVideoPlayerStandard.setUp(url
                , 1, "");*/
        kpiontId = arguments.getInt("kpointId");
        title = arguments.getString("title");
        titleText.setText(title);
        loadLogo();
        getPlayUrl(getActivity(), jzVideoPlayerStandard, courseid, kpiontId);
        adjshipin.setText(TextUtils.isEmpty(introduce) ? "暂无" : introduce);
        jzVideoPlayerStandard.thumbImageView.setImageURI(Uri.parse(logo));
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

    private void getPlayUrl(final Context context, final JZVideoPlayerStandard jcVideoPlayer, int courseId, int kpointId) {
        Map params = new HashMap();
        params.put(Constants.COURSE_ID, String.valueOf(courseId));
        params.put("videoType", "HLS");
        params.put("encryption", String.valueOf(true));
        params.put("videoClarity", "HD");
        params.put(Constants.KPOINT_ID, String.valueOf(kpointId));
        OkHttpRequest.doGet(context, OkHttpRequest.attachHttpGetParams(Constants.PREPARING_PACKAGE_VIDEO_URL, params), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                UIUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "获取视频播放地址失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    int code = jsonObject.optInt("code");
                    if(0 == code){
                        final String url = jsonObject.optString("content");
                        if(null != url){
                            UIUtils.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if(null != getActivity() && null != jcVideoPlayer){
                                        jcVideoPlayer.setUp(url, 1, "");
                                    }
                                }
                            });
                        }else{
                            UIUtils.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if(null != getActivity()){
                                        Toast.makeText(context, "获取视频播放地址失败", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }else{
                        UIUtils.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(null != getActivity()){
                                    Toast.makeText(context, "获取视频播放地址失败", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
                if(null != getActivity()){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(null != jzVideoPlayerStandard){
                                jzVideoPlayerStandard.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                                jzVideoPlayerStandard.thumbImageView.setImageBitmap(bitmap);
                            }
                        }
                    });
                }
            }
        }.execute();
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
