package com.kemizhibo.kemizhibo.other.preparing_package_detail.preview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Advanceable;

import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.web.WebSetting;

import im.delight.android.webview.AdvancedWebView;

public class PreviewActivity extends AppCompatActivity implements AdvancedWebView.Listener{

    private AdvancedWebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        String url = getIntent().getStringExtra("url");
        webView = findViewById(R.id.web_view);webView.setListener(this, this);
        //webView.loadUrl("https://docs.google.com/viewer?url=" + url);
        WebSetting.getInstance().setWebViewSetting(webView.getSettings());
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                String js = getClearAdDivJs(PreviewActivity.this);
                //Log.v("adJs",js);
                view.loadUrl(js);
            }
        });
        webView.loadUrl("https://view.officeapps.live.com/op/view.aspx?src=" + url);
    }

    public String getClearAdDivJs(Context context){
        String js = "javascript:";
        Resources res = context.getResources();
        String[] adDivs = res.getStringArray(R.array.adBlockDiv);
        for(int i=0;i<adDivs.length;i++){

            js += "var adDiv"+i+"= document.getElementById('"+adDivs[i]+"');if(adDiv"+i+" != null)adDiv"+i+".parentNode.removeChild(adDiv"+i+");";
        }
        return js;
    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) {

    }

    @Override
    public void onPageFinished(String url) {

    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {

    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {

    }

    @Override
    public void onExternalPageRequest(String url) {

    }

    @SuppressLint("NewApi")
    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume();
        // ...
    }

    @SuppressLint("NewApi")
    @Override
    protected void onPause() {
        webView.onPause();
        // ...
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        if (!webView.onBackPressed()) { return; }
        // ...
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        webView.onDestroy();
        // ...
        super.onDestroy();
    }
}
