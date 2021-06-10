package com.aoslec.bmi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button btn_explain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        btn_explain = findViewById(R.id.btn_explain);
        btn_explain.setOnClickListener(onClickListener);

    }//onCreate

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_explain:
                    //매개변수1 : 현재 context, 2: 어떤 activity로 전활 될건지 (타겟 엑티비티)
                    Intent intent = new Intent(MainActivity.this, explainActivity.class);
                    //intent 시작 메소드
                    startActivity(intent);
                    break;
            }
        }
    };



}//Main