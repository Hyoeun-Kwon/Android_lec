package com.aoslec.he_diary.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aoslec.he_diary.NetworkTask.NetworkTaskRegister;
import com.aoslec.he_diary.R;

public class RegisterActivity extends AppCompatActivity {
    //작업용
    String urlAddr = null;
    String sName, sPwd, sPwdChk;
    String myIP = null;

    private EditText eName,ePwd,ePwdChk; //회원가입 입력필드(이름,이메일,패스워드,패스워드 확인)
    private Button btnRegister,btnCancel; //회원가입 및 취소 버튼
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Intent intent = getIntent();
        myIP = intent.getStringExtra("myIP");
        urlAddr = "http://"+ myIP + ":8080/test/diaryInsert.jsp?";


        //에딧 텍스트 초기화
        eName = findViewById(R.id.reg_etName);
        ePwd = findViewById(R.id.reg_etPwd);
        ePwdChk = findViewById(R.id.reg_etPwdCheck);
        //가입하기,취소 버튼 초기화
        btnRegister = findViewById(R.id.reg_btnRegister);
        btnCancel = findViewById(R.id.reg_btnCancel);
        //회원가입 클릭리스너 연결
        btnRegister.setOnClickListener(onClickListener);
        btnCancel.setOnClickListener(onClickListener);

        //입력 글자 수 제한
        eName.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
        ePwd.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
        ePwdChk.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});

    }//onCreate


    //등록하기 버튼 입력시 데이터베이스에 값을 넘겨주기
    View.OnClickListener onClickListener = new View.OnClickListener() {
       Intent intent = null;


        @Override
        public void onClick(View v) {
            //회원가입 처리 시작
            sName = eName.getText().toString();
            sPwd = ePwd.getText().toString();
            sPwdChk = ePwdChk.getText().toString();

            switch(v.getId()){
                case R.id.reg_btnRegister:
                    if(sPwd.equals(sPwdChk)){
                        Log.v("Message","password : "+sPwd);
                        Log.v("Message","passwordCheck : "+sPwdChk);
                        urlAddr = urlAddr + "name=" + sName + "&password=" +sPwd;
                        String result = connectInsertData();//여기에 return값 줄거임
                            if(result.equals("1")){
                                Toast.makeText(RegisterActivity.this, sName+"가 입력되었습니다.", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(RegisterActivity.this, "입력이 실패하였습니다.", Toast.LENGTH_SHORT).show();
                            }
                        Toast.makeText(RegisterActivity.this, "가입에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(RegisterActivity.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.reg_btnCancel:
                    finish();
                    break;
            }//switch
        }//onClick
    };//onClickListener

    private String connectInsertData(){
        String result = null;
        try{
            //여기서 networktask
            //insertactivity에서 부른거야, 나는 ip주소 줄게 그리고     hb insert 할거야
            NetworkTaskRegister networkTaskRegister = new NetworkTaskRegister(RegisterActivity.this, urlAddr,"insert");
            //jsp통해서 받아온 return 값 -> object
            Object obj = networkTaskRegister.execute().get();
            result = (String) obj;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  result;//잘끝났으면 1 아니면 다른값 넘길 거임
    }//connectInsertData




}//Main