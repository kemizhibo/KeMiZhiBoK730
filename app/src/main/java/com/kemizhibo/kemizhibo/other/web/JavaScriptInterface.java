package com.kemizhibo.kemizhibo.other.web;

import android.app.Activity;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.kemizhibo.kemizhibo.other.utils.GsonUtils;
import com.kemizhibo.kemizhibo.other.utils.PreferencesUtils;
import com.kemizhibo.kemizhibo.other.web.view.CommonWebView;
import com.kemizhibo.kemizhibo.yhr.bean.LoginBean;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;

import org.json.JSONObject;

import java.util.HashMap;

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
        String token = PreferencesUtils.getLoginInfo("token", mActivity);
        return token;
    }

    @JavascriptInterface
    public void loadError(){

    }

    @JavascriptInterface
    public void back(){

    }

}
