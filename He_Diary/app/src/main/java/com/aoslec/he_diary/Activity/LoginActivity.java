package com.aoslec.he_diary.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.aoslec.he_diary.Bean.Bean;
import com.aoslec.he_diary.R;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    Intent intent = null;
    Button btnLogin, btnRegister;
    EditText userName, userPwd;
    String sName, sPwd;
    String myIP = "192.168.35.35";
    String urlAddr = null;//만들어줄 어드레스
    ArrayList<Bean> beans;
    //BeanAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //로그인
        btnLogin = findViewById(R.id.login_btnLogin);
        btnLogin.setOnClickListener(onClickListener);
        //회원가입
        btnRegister = findViewById(R.id.login_btnRegister);
        btnRegister.setOnClickListener(onClickListener);
        //에딧텍스트
        userName = findViewById(R.id.login_etname);
        userPwd = findViewById(R.id.login_etpwd);
        //에딧텍스트에 있는 값 받아오기
        sName = userName.getText().toString();
        sPwd = userPwd.getText().toString();

        //select 문을 위한 주소
        //urlAddr = "http://"+ myIP + ":8080/test/diaryloginselect.jsp?";

        //로그인에 입력된 값(아이디,패스워드) 받아와서 intent에 담기
       // intent.putExtra("userName", sName);
       // intent.putExtra("userPwd", sPwd);


    }//onCreate

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.login_btnLogin:
                        //if(sName.equals(beans.get(position).getName()))
                        intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.login_btnRegister:
                        intent = new Intent(LoginActivity.this, RegisterActivity.class);
                        intent.putExtra("myIP",myIP);
                        startActivity(intent);
                        break;
                }
            }
        };//onClickListener



}//Main