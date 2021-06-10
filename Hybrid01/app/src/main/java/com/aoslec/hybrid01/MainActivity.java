package com.aoslec.hybrid01;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    WebView webView = null;
    Button btnReload, btnPage1, btnPage2, btnPage3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webview);
        btnReload = findViewById(R.id.btn_reload);
        btnPage1 = findViewById(R.id.btn_page1);
        btnPage2 = findViewById(R.id.btn_page2);
        btnPage3 = findViewById(R.id.btn_page3);

        btnReload.setOnClickListener(onClickListener);
        btnPage1.setOnClickListener(onClickListener);
        btnPage2.setOnClickListener(onClickListener);
        btnPage3.setOnClickListener(onClickListener);

        //Web Setting
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);//JavaScript 사용
        webSettings.setBuiltInZoomControls(true);//확대 축소 가능
        webSettings.setDisplayZoomControls(false);//돋보기 없애기

        //나는 내꺼에서만 다할거야
        //Link시 다른 Browser 안보이게
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //page 뜰경우
                super.onPageStarted(view, url, favicon);
                btnReload.setText("로딩 중...");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                //page 끝날때
                super.onPageFinished(view, url);
                btnReload.setText(webView.getTitle());
            }
        });



    }//onCreate

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_page1:
                    btnPage1Click();
                    break;
                case R.id.btn_page2:
                    webView.loadUrl("https://www.naver.com");
                    break;
                case R.id.btn_page3:
                    webView.loadUrl("https://www.daum.net");
                    break;
            }// switch
        }
    };

    public void btnPage1Click(){
        webView.loadUrl("http://192.168.2.6:8080/test/Arithmetic.jsp");
    }

    //back 눌렀을때 죽지 않게 하기 위함(android)!!! 필수
    @Override
    public void onBackPressed() {
       // super.onBackPressed();
        if(webView.canGoBack()){
            webView.goBack();
        }else{
            finish();
        }
    }//onBackPressed
}//Main