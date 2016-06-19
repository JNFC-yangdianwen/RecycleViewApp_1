package com.example.yangdianwen.recycleviewapp_1.HttpUtis;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by yangdianwen on 16-6-19.
 */
public class MyWebviewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return  true;
    }
}

