package com.aoslec.he_diary.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.aoslec.he_diary.NetworkTask.NetworkTask;
import com.aoslec.he_diary.R;

public class DiaryUpdateActivity extends AppCompatActivity {

    String urlAddr= null;
    String subUrl_update = null;
    String subUrl_delete = null;
    String sdate, stitle, sdetail,sstatus,myIP;

    TextView tv_diary_date_update;
    EditText edt_diary_detail_update, edt_diary_title_update;
    WebView web_diary_status_update;
    Button btn_dairy_update, btn_diary_delete;

    Intent intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.v("Message", "onCreate : Update");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_update);


        intent = getIntent();

        myIP = intent.getStringExtra("myIP");
        urlAddr = "http://" + myIP + ":8080/test/";
        subUrl_update = "diaryUpdateReturn.jsp?";
        subUrl_delete = "diaryDeleteReturn.jsp?";


        tv_diary_date_update = findViewById(R.id.tv_diary_date_update);
        edt_diary_title_update = findViewById(R.id.edt_diary_title_update);
        edt_diary_detail_update = findViewById(R.id.edt_diary_detail_update);
        web_diary_status_update = findViewById(R.id.web_diary_status_update);

        tv_diary_date_update.setText(intent.getStringExtra("date"));
        edt_diary_title_update.setText(intent.getStringExtra("title"));
        edt_diary_detail_update.setText(intent.getStringExtra("detail"));
        sstatus = intent.getStringExtra("status");

        web_diary_status_update.loadData(htmlData(sstatus), "text/html", "UTF-8");



        // 수정 버튼
        btn_dairy_update = findViewById(R.id.new_diary_update);
        btn_dairy_update.setOnClickListener(onClickListener);

        // 삭제 버튼
        btn_diary_delete = findViewById(R.id.new_diary_delete);
        btn_diary_delete.setOnClickListener(onClickListener);

    }



    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            sdate = tv_diary_date_update.getText().toString();
            stitle = edt_diary_title_update.getText().toString();
            sdetail = edt_diary_detail_update.getText().toString();
            int dbstatus = web_diary_status_update.getId();

            switch (v.getId()){
                case R.id.new_diary_update:
                urlAddr = urlAddr + subUrl_update + "date=" + sdate + "&title=" + stitle + "&detail=" + sdetail + "&status=" + dbstatus;
                String result = connectUpdateData();
                break;
                case R.id.new_diary_delete:
                urlAddr = urlAddr + subUrl_delete + "date=" + sdate;
                String result_del = connectDeleteData();
                break;
            }


            finish();
        }
    };

    private String connectUpdateData(){
        Log.v("Message", "METHOD : connectUpdateData Start");
        String result = null;
        try {
            // NetworkTask 가져와서 일을 시킬 거다 (어디에?, 어느 주소받아서?, 어느역할이야?)
            NetworkTask networkTask = new NetworkTask(DiaryUpdateActivity.this, urlAddr, "update");
            Object obj = networkTask.execute().get();
            result = (String)obj;
        }catch (Exception e){
            e.printStackTrace();
        }
        Log.v("Message", "End of METHOD // result : " + result);
        return result;
    }

    private String connectDeleteData(){
        Log.v("Message", "METHOD : connectUpdateData Start");
        String result_del = null;
        try {
            // NetworkTask 가져와서 일을 시킬 거다 (어디에?, 어느 주소받아서?, 어느역할이야?)
            NetworkTask networkTask = new NetworkTask(DiaryUpdateActivity.this, urlAddr, "delete");
            Object obj = networkTask.execute().get();
            result_del = (String)obj;
        }catch (Exception e){
            e.printStackTrace();
        }
        Log.v("Message", "End of METHOD // result : " + result_del);
        return result_del;
    }

    public String htmlData(String sstatus) {

        String content =
                "<?xml version=\"1.0\" encoding=\"utf-8\" ?>" +
                        "<html><head>" +
                        "<meta http-equiv=\"content-type\" content=\"text/html; charset=utf8\"/>" +
                        "<head><body><center>" +
                        "<img src=\"http://192.168.35.20:8080/test/";
                        //"<img src=\""+myIP+":8080/test/";
        content += sstatus + "\" alt=\"오늘의 사진\" style=\"width: auto; height: 100%;\"></center></body></html>";
        return content;

    }


}