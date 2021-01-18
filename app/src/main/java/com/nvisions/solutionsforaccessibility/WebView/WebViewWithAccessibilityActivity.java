package com.nvisions.solutionsforaccessibility.WebView;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.webkit.WebSettingsCompat;
import androidx.webkit.WebViewFeature;

import com.nvisions.solutionsforaccessibility.R;

public class WebViewWithAccessibilityActivity extends AppCompatActivity {
    WebView browser;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_with_accessibility);
        setTitle(R.string.goodExample);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        browser=findViewById(R.id.browser);
        progressBar = findViewById(R.id.progressBar);
        final WebSettings websettings = browser.getSettings();
        websettings.setDomStorageEnabled(true);  // Open DOM storage function
        websettings.setAppCacheMaxSize(1024*1024*8);
        String appCachePath = getApplicationContext().getCacheDir().getAbsolutePath();
        websettings.setAppCachePath(appCachePath);
        websettings.setAllowFileAccess(true);    // Readable file cache
        websettings.setAppCacheEnabled(true);    //Turn on the H5(APPCache) caching function
        websettings.setJavaScriptEnabled(true);
        browser.setWebViewClient(new WebViewClient() {
            public void onPageStarted(WebView view, String url,
                                      android.graphics.Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                DarkModeSupport(websettings);
                int nightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
                progressBar.setVisibility(View.VISIBLE);
            };


            public void onPageFinished(WebView view, String url) {
                // do your stuff here
                progressBar.setVisibility(View.INVISIBLE);


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        browser.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED);
                    }
                }, 1000);


            }
        });
        browser.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);
                progressBar.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED);
                progressBar.setContentDescription(newProgress+"%");
            }
        });


        browser.loadUrl("https://a11y-nvisions.github.io/Solutions/WEB/example.radioButton/index.html");
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void DarkModeSupport(WebSettings ws){
        int NIGHT_FLAG = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if(NIGHT_FLAG == Configuration.UI_MODE_NIGHT_YES){
            if(WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK_STRATEGY)) {
                WebSettingsCompat.setForceDarkStrategy(ws, WebSettingsCompat.DARK_STRATEGY_WEB_THEME_DARKENING_ONLY);
                ws.setForceDark(ws.FORCE_DARK_ON);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (browser.canGoBack()) {
            browser.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
