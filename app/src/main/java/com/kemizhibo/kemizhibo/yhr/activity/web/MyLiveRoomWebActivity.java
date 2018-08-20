package com.kemizhibo.kemizhibo.yhr.activity.web;

import android.content.Intent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import butterknife.BindView;

public class MyLiveRoomWebActivity extends BaseActivity {

    @BindView(R.id.prog)
    ProgressBar prog;
    @BindView(R.id.wed)
    WebView wed;
    @BindView(R.id.activity_wed)
    RelativeLayout activityWed;
    private String url;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_live_room_web;
    }

    @Override
    protected void initData() {
        initview();
        SettingsP();
        //url = "http://192.168.1.101:8080/kmzb/ng/#/roomDetail/releVideo${id}".replace(Constants.H5_REPLACE_STR, String.valueOf(intent.getIntExtra("courseId", 0)));
    }

    private void initview() {
        Intent intent = getIntent();
        String courseId = intent.getStringExtra("courseId");
        //http://192.168.1.101:8080/ng/my-app/#/roomDetail
        //wed.loadUrl("http://192.168.1.101:8080/ng/#/roomDetail/releVideo?courseId="+courseId);
        wed.loadUrl("http://192.168.1.101:8080/ng/my-app/#/roomDetail?courseId="+courseId);
    }

    private void SettingsP() {
        WebSettings seting = wed.getSettings();
        seting.setJavaScriptEnabled(true);//设置webview支持javascript脚本

        // 覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        wed.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
        wed.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                if (newProgress == 100) {
                    // 网页加载完成
                    prog.setVisibility(View.GONE);//加载完网页进度条消失
                } else {
                    // 加载中
                    prog.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    prog.setProgress(newProgress);//设置进度值
                }
            }
        });
    }
}
