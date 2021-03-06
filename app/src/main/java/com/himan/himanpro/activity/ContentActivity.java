package com.himan.himanpro.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.himan.himanpro.R;
import com.himan.himanpro.core.BaseActivity;
import com.himan.himanpro.utils.LogUtils;
import com.himan.himanpro.utils.ProgressUtils;
import com.himan.himanpro.utils.ToastUtils;
import com.himan.himanpro.view.CustomDialog;

/**
 * Created by HIMan on 16/7/6.
 */
public class ContentActivity extends BaseActivity {


    private WebView wb_content;
    private Toolbar custom_toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_content;
    }

    @Override
    public void initView() {
        wb_content = (WebView) findViewById(R.id.wb_content);
        custom_toolbar = (Toolbar) findViewById(R.id.custom_toolbar);
    }

    @Override
    public void initData() {
        final String file = "android_asset/data.html";
        final String encoding = "utf-8";
        Intent intent = getIntent();
        final String content = intent.getStringExtra("content");
        String title = intent.getStringExtra("title");
        custom_toolbar.setTitle(title);
        wb_content.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        wb_content.getSettings().setUseWideViewPort(true);
        wb_content.getSettings().setLoadWithOverviewMode(true);
        wb_content.loadDataWithBaseURL("file://", content, file, encoding, "about:blank");
        wb_content.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                ProgressUtils.showProgressDialog(ContentActivity.this, "正在加载");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                ProgressUtils.dismissDialog();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
