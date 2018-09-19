package com.kemizhibo.kemizhibo.yhr.activity.web;

import android.app.Activity;
import android.webkit.JavascriptInterface;

import com.kemizhibo.kemizhibo.other.utils.PreferencesUtils;
import com.kemizhibo.kemizhibo.other.web.view.CommonWebView;

/**
 *
 */

public class JavaScriptInterface {

    private MyLiveRoomWebActivity webActivity;
    private Activity mActivity;

    public JavaScriptInterface(Activity activity){
        this.mActivity = activity;
    }

    public JavaScriptInterface(Activity activity, MyLiveRoomWebActivity webActivity){
        this.mActivity = activity;
        this.webActivity = webActivity;
    }

    @JavascriptInterface
    public String getToken(){
        //new TokenInterceptor(mActivity).getNewToken();
        String token = PreferencesUtils.getLoginInfo("token", mActivity);
        return token;
    }

    @JavascriptInterface
    public void loadError(String errorCode){
        if(null != webActivity){
            //webActivity.loadError(errorCode);
        }
    }

    @JavascriptInterface
    public void back(){
        if(null != webActivity){
            //webActivity.back();
        }
    }

}
