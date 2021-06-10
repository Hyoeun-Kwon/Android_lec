package com.aoslec.webview_quizservier;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    Button btn_hello, btn_originalimg, btn_fullimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("webserver 이미지");


        webView = findViewById(R.id.webview);

        btn_hello = findViewById(R.id.btn_hello);
        btn_originalimg = findViewById(R.id.btn_originalimg);
        btn_fullimg = findViewById(R.id.btn_fullimg);

        btn_hello.setOnClickListener(onClickListener);
        btn_originalimg.setOnClickListener(onClickListener);
        btn_fullimg.setOnClickListener(onClickListener);


//        //나는 내꺼에서만 다할거야
//        //Link시 다른 Browser 안보이게
//        webView.setWebViewClient(new WebViewClient(){
//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                //page 뜰경우
//                super.onPageStarted(view, url, favicon);
//                btnReload.setText("로딩 중...");
//            }
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                //page 끝날때
//                super.onPageFinished(view, url);
//                btnReload.setText(webView.getTitle());
//            }
//        });

        //Web Setting
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);//JavaScript 사용
        webSettings.setBuiltInZoomControls(true);//확대 축소 가능
        webSettings.setDisplayZoomControls(false);//돋보기 없애기


    }//onCreate


    //onClickListener
    View.OnClickListener onClickListener = new View.OnClickListener() {
        String uri = "<html>" +
                "<head>" +
                "</head>" +
                "<body>";
        String uri2 = "</body>" +
                "</html>";
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_hello:
                    String word = "<h1>Hello Word!</h1>";
                    webView.loadData(word, "text/html", "UTF-8");
                    //webView.loadUrl("http://192.168.3.130:8080/test/helloworld.jsp");
                    break;
                case R.id.btn_originalimg:
                    webView.loadDataWithBaseURL(null, uri+"<img src=\"http://192.168.3.130:8080/test/dog.jpg\" style=\"width: 50%; height: auto;\">"+uri2, "text/html", "UTF-8", null);

                    //webView.loadUrl("http://192.168.3.130:8080/test/originalimg.jsp");
                    break;
                case R.id.btn_fullimg:
                    webView.loadDataWithBaseURL(null, uri+"<img src=\"http://192.168.3.130:8080/test/dog.jpg\" style=\"width: 100%; height: auto;\">"+uri2, "text/html", "UTF-8", null);
                    //webView.loadUrl("http://192.168.3.130:8080/test/fullimg.jsp");
                    break;

            }//switch

        }
    };




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

