package com.aoslec.dbcrud.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aoslec.dbcrud.NetworkTask.NetworkTask;
import com.aoslec.dbcrud.R;

public class UpdateActivity extends AppCompatActivity {
    String urlAddr = null;
    String scode, sname, sdept, sphone, macIP;
    EditText ecode, ename, edept, ephone;
    Button btnUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        //select all 에서 intent 값 넘겨 받기
        Intent intent = getIntent();
        scode = intent.getStringExtra("code");
        sname = intent.getStringExtra("name");
        sdept = intent.getStringExtra("dept");
        sphone = intent.getStringExtra("phone");
        macIP = intent.getStringExtra("macIP");
        //jsp를 get방식으로 넘기려고 마지막이 ?
        urlAddr = "http://"+ macIP + ":8080/test/studentUpdateReturn.jsp?";

        //연결(update에 있는 edittext)
        ecode = findViewById(R.id.update_code);
        ename = findViewById(R.id.update_name);
        edept = findViewById(R.id.update_dept);
        ephone = findViewById(R.id.update_phone);

        //입력시 자릿수 제한
        ecode.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
        ename.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
        edept.setFilters(new InputFilter[]{new InputFilter.LengthFilter(12)});
        ephone.setFilters(new InputFilter[]{new InputFilter.LengthFilter(12)});

        //버튼
        btnUpdate = findViewById(R.id.update_btn);
        btnUpdate.setOnClickListener(onClickListener);

        //가져온 걸 화면에 보여줘야 함
        ecode.setText(scode); //intent로 넘겨 받은 값을 edittext에 보여주는 것
        ename.setText(sname);
        edept.setText(sdept);
        ephone.setText(sphone);

    }//onCreate

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            scode = ecode.getText().toString();
            sname = ename.getText().toString();
            sdept = edept.getText().toString();
            sphone = ephone.getText().toString();

            //get방식
            urlAddr = urlAddr + "code=" + scode +
                    "&name=" + sname + "&dept=" +sdept +"&phone=" +sphone;

            Log.v("Message","urlAddr" + urlAddr);

            //urlAddr는 전역변수라 아무 메소드에서 쓸 수 있음
            String result = connectUpdateData();//여기에 return값 줄거임
            //->conncecInsertData 메소드 만들어 줄거임
            if(result.equals("1")){
                Toast.makeText(UpdateActivity.this, scode+"가 수정되었습니다.", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(UpdateActivity.this, "입력이 실패하였습니다.", Toast.LENGTH_SHORT).show();
            }
            finish();// back 버튼 누른거랑 같음 (=main화면으로 감)
        }
    };//onClick

    private String connectUpdateData(){
        String result = null;
        try{
            //여기서 networktask
            //insertactivity에서 부른거야, 나는 ip주소 줄게 그리고     hb insert 할거야
            NetworkTask networkTask = new NetworkTask(UpdateActivity.this, urlAddr,"update");
            //jsp통해서 받아온 return 값 -> object
            Object obj = networkTask.execute().get();
            result = (String) obj;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  result;//잘끝났으면 1 아니면 다른값 넘길 거임
    }//connectUpdateData

}