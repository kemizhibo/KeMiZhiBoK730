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
        BDocInfo docInfo = new BDocInfo(myPreviewBean.getHost(), myPreviewBean.getDocId(), myPreviewBean.getDocType(), myPreviewBean.getToken())
                .setLocalFileDir(myPreviewBean.getThisDocDir())
                .setTotalPage(myPreviewBean.getTotalPage())
                .setDocTitle(myPreviewBean.getDocTitle())
                .setStartPage(myPreviewBean.getStartPage());

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
