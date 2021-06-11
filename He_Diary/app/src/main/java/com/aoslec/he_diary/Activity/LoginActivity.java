package com.aoslec.he_diary.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aoslec.he_diary.R;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin, btnRegister;
    Intent intent = null;
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

        //Intent intent = getIntent();


    }//onCreate

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.login_btnLogin:
                        intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.login_btnRegister:
                        intent = new Intent(LoginActivity.this, RegisterActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        };//onClickListener



}//Main