package com.kemizhibo.kemizhibo.other.web;

import android.os.Build;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * WebView配置
 */

public class WebSetting {

    private static WebSetting settings;

    private WebSetting(){

    }

    public static WebSetting getInstance(){
        if (null == settings){
            settings = new WebSetting();
        }
        return settings;
    }

    public void setWebViewSetting(WebSettings setting){
        setting.setSupportMultipleWindows(true);
        setting.setJavaScriptEnabled(true);
        setting.setJavaScriptCanOpenWindowsAutomatically(true);
        setting.setDomStorageEnabled(true);
        setting.setDatabaseEnabled(true);
        setting.setAllowFileAccess(true);
        setting.setGeolocationEnabled(true);
        setting.setCacheMode(WebSettings.LOAD_DEFAULT);
//        setting.setAppCacheEnabled(false);
        setting.setLoadWithOverviewMode(true);
        setting.setUseWideViewPort(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setting.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        setting.setAllowUniversalAccessFromFileURLs(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        setting.setBlockNetworkImage(false);
    }
}
