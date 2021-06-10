package com.aoslec.activity_he;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText et_mail;
    EditText et_pwd;
    Button bt_signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_mail = findViewById(R.id.et_email);
        et_pwd = findViewById(R.id.et_pwd);
        bt_signup = findViewById(R.id.bt_signup);
        bt_signup.setOnClickListener(onClickListener);



    }//on

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.bt_signup:

                    //매개변수1 : 현재 context, 2: 어떤 activity로 전활 될건지 (타겟 엑티비티)
                    Intent intent = new Intent(MainActivity.this, SignupCon.class);
                    //intent 시작 메소드
                    startActivity(intent);


                    break;
            }
        }
    };


}