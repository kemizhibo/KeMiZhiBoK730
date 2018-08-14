package com.kemizhibo.kemizhibo.yhr.activity.web;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.config.Constants;
import com.kemizhibo.kemizhibo.other.web.JavaScriptInterface;
import com.kemizhibo.kemizhibo.other.web.WebSetting;
import com.kemizhibo.kemizhibo.other.web.view.CommonWebView;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.tungdx.mediapicker.MediaItem;
import vn.tungdx.mediapicker.MediaOptions;
import vn.tungdx.mediapicker.activities.MediaPickerActivity;

public class MyLiveRoomWebActivity extends BaseActivity implements CommonWebView {

    @BindView(R.id.loading_page)
    LinearLayout loadingPage;
    @BindView(R.id.live_web_view)
    WebView liveWebView;
    private String url;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_live_room_web;
    }

    @Override
    protected void initData() {
        //获取传过来的值
        Intent intent = getIntent();
        String courseId = intent.getStringExtra("courseId");
        if(TextUtils.isEmpty(courseId)){
            finish();
        }else{
            url = "接口路劲".replace(Constants.H5_REPLACE_STR, courseId);
        }
        // 设置界面跳转只在自己应用程序中
        //liveWebView.setWebChromeClient(new WebChromeClient());
        //加载链接路径
        //liveWebView.loadUrl("");
        WebSetting.getInstance().setWebViewSetting(liveWebView.getSettings());
        liveWebView.addJavascriptInterface(new JavaScriptInterface(this, this), "android");
        liveWebView.setWebChromeClient(new WebChromeClient() {
            //     在WebView开始加载网页时，显示进度框；加载完毕时，隐藏进度框
            public void onProgressChanged(WebView view, int newProgress) {
                if(newProgress==100){
                    loadingPage.setVisibility(View.GONE);
                    liveWebView.setVisibility(View.VISIBLE);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
        liveWebView.loadUrl(url);
    }

    @Override
    public void loadError(String errorCode) {
        finish();
    }

    @Override
    public void back() {
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && liveWebView.canGoBack()){
            liveWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
