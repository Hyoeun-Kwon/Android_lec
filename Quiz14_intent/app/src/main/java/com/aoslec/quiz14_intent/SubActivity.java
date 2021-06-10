package com.aoslec.quiz14_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SubActivity extends AppCompatActivity {

    TextView tv_sub = null;
    Button btn_subClose = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        tv_sub = findViewById(R.id.tv_sub);
        btn_subClose = findViewById(R.id.btn_subClose);

        //처음의 값을 받자
        Intent intent = getIntent();
        String userid = intent.getStringExtra("userid");
        int passwd = intent.getIntExtra("passwd",0);






//        btn_subClose.setOnClickListener(onClickListener);

    }//onCreate
}//Main