package com.aoslec.he_diary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aoslec.he_diary.NetworkTask.NetworkTask;

public class DiaryActivity extends AppCompatActivity {
    String urlAddr= null;
    String sdate, stitle, sdetail, myIP;

    TextView tv_diary_date;
    EditText edt_diary_detail, edt_diary_title;
    Button btn_dairy_insert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);



//        Intent intent = getIntent();
//
//        macIP = intent.getStringExtra("myIP");
//        urlAddr = "http://" + myIP + ":8080/test/diaryInsertReturn.jsp?";

//        tv_diary_date = findViewById(R.id.tv_diary_date);
//        tv_diary_date.setText(intent.getStringExtra("date"));

        edt_diary_title = findViewById(R.id.edt_diary_title);
        edt_diary_detail = findViewById(R.id.edt_diary_detail);

        btn_dairy_insert = findViewById(R.id.new_diary_insert);
        btn_dairy_insert.setOnClickListener(onClickListener);

    }//onCreate

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();

        myIP = intent.getStringExtra("myIP");
        urlAddr = "http://" + myIP + ":8080/test/diaryInsertReturn.jsp?";

        tv_diary_date = findViewById(R.id.tv_diary_date);
        tv_diary_date.setText(intent.getStringExtra("date"));


    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            sdate = tv_diary_date.getText().toString();
            stitle = edt_diary_title.getText().toString();
            sdetail = edt_diary_detail.getText().toString();

            urlAddr = urlAddr + "date=" + sdate + "&title=" + stitle + "&detail=" + sdetail;

            String result = connectInsertData();
//            if(result.equals("1")){
//                // 정상인 경우 ( 1만 정상이라는 것은 jsp 에서 판단 할 수 있도록 만들 예정임. )
//                Toast.makeText(DiaryActivity.this, sdate+"에 입력되었습니다.", Toast.LENGTH_SHORT).show();
//            }else{
//                Toast.makeText(DiaryActivity.this, "입력이 실패 되었습니다.", Toast.LENGTH_SHORT).show();
//            }
//            // back 버튼을 누른 것과 동일한 역할 (= 입력 다했으니 메인으로 간다)
            finish();
        }
    };

    private String connectInsertData(){
        String result = null;
        try {
            // NetworkTask 가져와서 일을 시킬 거다 (어디에?, 어느 주소받아서?, 어느역할이야?)
            NetworkTask networkTask = new NetworkTask(DiaryActivity.this, urlAddr, "insert");
            Object obj = networkTask.execute().get();
            result = (String)obj;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }





}//Main