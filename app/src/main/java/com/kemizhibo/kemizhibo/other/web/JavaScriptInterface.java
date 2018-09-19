package com.kemizhibo.kemizhibo.other.web;

import android.app.Activity;
import android.webkit.JavascriptInterface;
import com.kemizhibo.kemizhibo.other.utils.PreferencesUtils;
import com.kemizhibo.kemizhibo.other.web.view.CommonWebView;


/**
 *
 */

public class JavaScriptInterface {

    private CommonWebView commonWebView;
    private Activity mActivity;

    public JavaScriptInterface(Activity activity){
        this.mActivity = activity;
    }

    public JavaScriptInterface(Activity activity, CommonWebView commonWebView){
        this.mActivity = activity;
        this.commonWebView = commonWebView;
    }

    @JavascriptInterface
    public String getToken(){
        //new TokenInterceptor(mActivity).getNewToken();
        String token = PreferencesUtils.getLoginInfo("token", mActivity);
        return token;
    }

    @JavascriptInterface
    public void loadError(String errorCode){
        if(null != commonWebView){
            commonWebView.loadError(errorCode);
        }
    }

    @JavascriptInterface
    public void back(){
        if(null != commonWebView){
            commonWebView.back();
        }
    }
}
