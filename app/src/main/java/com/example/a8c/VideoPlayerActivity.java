package com.example.a8c;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;
//z6AHgwhw_KE
public class VideoPlayerActivity extends AppCompatActivity {
    private WebView webView;
    private String videoId;
    private String url="K6u7hvBW6P0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);

        Intent intent = getIntent();
        String videoUrl = intent.getStringExtra("url");
        if (videoUrl != null && videoUrl.length() > 12) {
            videoId = videoUrl.substring(videoUrl.indexOf("=") + 1);
        }

        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.loadUrl("javascript:player.playVideo()");
            }
        });

        webView.loadData("<html>" +
                        "<body>" +
                        "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"
                        + url + "?enablejsapi=1\" frameborder=\"0\" allowfullscreen></iframe>" +
                        "</body>" +
                        "</html>",
                "text/html",
                "utf-8");

    }
}
