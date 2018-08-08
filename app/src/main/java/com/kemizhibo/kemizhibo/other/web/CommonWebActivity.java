package com.kemizhibo.kemizhibo.other.web;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.web.view.CommonWebView;
import com.kemizhibo.kemizhibo.yhr.LoadingPager;
import com.kemizhibo.kemizhibo.yhr.activity.MainActivity;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import vn.tungdx.mediapicker.MediaItem;
import vn.tungdx.mediapicker.MediaOptions;
import vn.tungdx.mediapicker.activities.MediaPickerActivity;

public class CommonWebActivity extends BaseActivity implements CommonWebView {
    private static final int REQUEST_CODE = 10000;
    public static final String OPERATE_KEY = "operate";
    public static final String MAKE = "make";
    public static final String PREVIEW = "preview";
    public static final String TEACH = "teach";

    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.loading_page)
    LinearLayout loading_page;
    private ValueCallback<Uri[]> mUploadCallbackAboveL;
    private ValueCallback<Uri> mUploadCallbackBelow;
    private ArrayList<MediaItem> mediaItemSelectedList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_common_web;
    }

    @Override
    protected void initData() {
        WebSetting.getInstance().setWebViewSetting(webView.getSettings());
        webView.addJavascriptInterface(new JavaScriptInterface(this, this), "android");
        webView.setWebChromeClient(new WebChromeClient() {
            //     在WebView开始加载网页时，显示进度框；加载完毕时，隐藏进度框
            public void onProgressChanged(WebView view, int newProgress) {
                if(newProgress==100){
                    loading_page.setVisibility(View.GONE);
                    webView.setVisibility(View.VISIBLE);
                }
                super.onProgressChanged(view, newProgress);
            }

            /**
             * 8(Android 2.2) <= API <= 10(Android 2.3)回调此方法
             */
            private void openFileChooser(android.webkit.ValueCallback<Uri> uploadMsg) {
                Log.e("CommonWebActivity", "运行方法 openFileChooser-1");
                // (2)该方法回调时说明版本API < 21，此时将结果赋值给 mUploadCallbackBelow，使之 != null
                mUploadCallbackBelow = uploadMsg;
                takeResource();
            }
            /**
             * 11(Android 3.0) <= API <= 15(Android 4.0.3)回调此方法
             */
            public void openFileChooser(android.webkit.ValueCallback<Uri> uploadMsg, String acceptType) {
                Log.e("CommonWebActivity", "运行方法 openFileChooser-2 (acceptType: " + acceptType + ")");
                // 这里我们就不区分input的参数了，直接用拍照
                openFileChooser(uploadMsg);
            }
            /**
             * 16(Android 4.1.2) <= API <= 20(Android 4.4W.2)回调此方法
             */
            public void openFileChooser(android.webkit.ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                Log.e("CommonWebActivity", "运行方法 openFileChooser-3 (acceptType: " + acceptType + "; capture: " + capture + ")");
                // 这里我们就不区分input的参数了，直接用拍照
                openFileChooser(uploadMsg);
            }
            /**
             * API >= 21(Android 5.0.1)回调此方法
             */
            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, FileChooserParams fileChooserParams) {
                Log.e("CommonWebActivity", "运行方法 onShowFileChooser");
                // (1)该方法回调时说明版本API >= 21，此时将结果赋值给 mUploadCallbackAboveL，使之 != null
                mUploadCallbackAboveL = valueCallback;
                takeResource();
                return true;
            }


        });
        webView.loadUrl("http://demo.mulpush.cn/kemi/#/make/2832");
    }

    private void takeResource() {
        MediaOptions options = new MediaOptions.Builder()
                .canSelectBothPhotoVideo()
                .build();
        MediaPickerActivity.open(this, REQUEST_CODE, options);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            // 经过上边(1)、(2)两个赋值操作，此处即可根据其值是否为空来决定采用哪种处理方法
            if (mUploadCallbackBelow != null) {
                chooseBelow(resultCode, data);
            } else if (mUploadCallbackAboveL != null) {
                chooseAbove(resultCode, data);
            } else {
                Toast.makeText(this, "发生错误", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Android API < 21(Android 5.0)版本的回调处理
     * @param resultCode 选取文件或拍照的返回码
     * @param data 选取文件或拍照的返回结果
     */
    private void chooseBelow(int resultCode, Intent data) {
        Log.e("CommonWebActivity", data.toString());
        if (RESULT_OK == resultCode) {
            if (data != null) {
                mediaItemSelectedList = MediaPickerActivity.getMediaItemSelected(data);
                Uri uri = mediaItemSelectedList.get(0).getUriOrigin();
                if (uri != null) {
                    Log.e("CommonWebActivity", "系统返回URI：" + uri.toString());
                    mUploadCallbackBelow.onReceiveValue(uri);
                } else {
                    mUploadCallbackBelow.onReceiveValue(null);
                }
            } else {
                mUploadCallbackBelow.onReceiveValue(null);
            }
        } else {
            mUploadCallbackBelow.onReceiveValue(null);
        }
        mUploadCallbackBelow = null;
    }

    /**
     * Android API >= 21(Android 5.0) 版本的回调处理
     * @param resultCode 选取文件或拍照的返回码
     * @param data 选取文件或拍照的返回结果
     */
    private void chooseAbove(int resultCode, Intent data) {

        if (RESULT_OK == resultCode) {
            if (data != null) {
                // 这里是针对从文件中选图片的处理
                Uri[] results;
                if (data != null) {
                    mediaItemSelectedList = MediaPickerActivity.getMediaItemSelected(data);
                    Uri uriOrigin = mediaItemSelectedList.get(0).getUriOrigin();
                    results = new Uri[]{uriOrigin};
                    for (Uri uri : results) {
                        Log.e("CommonWebActivity", "系统返回URI：" + uri.toString());
                    }
                    mUploadCallbackAboveL.onReceiveValue(results);

                } else {
                    mUploadCallbackAboveL.onReceiveValue(null);
                }
            } else {
                mUploadCallbackAboveL.onReceiveValue(null);
            }
        } else {
            mUploadCallbackAboveL.onReceiveValue(null);
        }
        mUploadCallbackAboveL = null;
    }

    @Override
    public void loadError() {

    }

    @Override
    public void back() {

    }
}
