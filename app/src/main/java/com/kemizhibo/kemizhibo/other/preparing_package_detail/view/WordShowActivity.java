package com.kemizhibo.kemizhibo.other.preparing_package_detail.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;

import com.baidu.bdocreader.BDocInfo;
import com.baidu.bdocreader.BDocView;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.MyPreviewBean;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;

import java.io.Serializable;

import butterknife.BindView;

public class WordShowActivity extends BaseActivity {
    @BindView(R.id.dv_doc)
    BDocView mdvdoc;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_word_show;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        MyPreviewBean myPreviewBean = (MyPreviewBean) intent.getSerializableExtra("word");
        String host = myPreviewBean.getHost();
        String docId = myPreviewBean.getDocId();
        String docType = myPreviewBean.getDocType();
        String token = myPreviewBean.getToken();
        String thisDocDir = myPreviewBean.getThisDocDir();
        int totalPage = myPreviewBean.getTotalPage();
        String docTitle = myPreviewBean.getDocTitle();
        int startPage = myPreviewBean.getStartPage();
        BDocInfo docInfo = new BDocInfo(host, docId, docType, token)
                .setLocalFileDir(thisDocDir)
                .setTotalPage(totalPage)
                .setDocTitle(docTitle)
                .setStartPage(startPage);

        Log.d("baidu", host);
        Log.d("baidu", docId);
        Log.d("baidu", docType);
        Log.d("baidu", token);
        Log.d("baidu", thisDocDir);
        Log.d("baidu", totalPage + "");
        Log.d("baidu", docTitle);
        Log.d("baidu", startPage + "");

        mdvdoc.setOnDocLoadStateListener(new BDocView.OnDocLoadStateListener() {
            @Override
            public void onDocLoadComplete() {
                Log.d("test", "onDocLoadComplete");
            }

            @Override
            public void onDocLoadFailed(String errorDesc) {
                // errorDesc format: ERROR_XXXX_DESC(code=xxxxx)
                Log.d("test", "onDocLoadFailed errorDesc=" + errorDesc);
            }

            @Override
            public void onCurrentPageChanged(int currentPage) {
                // 记录当前页面
                Log.i("test", "currentPage = " + currentPage);
            }
        });
// 加载文档
        mdvdoc.loadDoc(docInfo);

    }

}
