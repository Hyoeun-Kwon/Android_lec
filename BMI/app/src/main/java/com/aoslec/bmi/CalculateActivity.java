package com.aoslec.bmi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class CalculateActivity extends AppCompatActivity {

    Button btnResult;
    EditText et_height, et_weight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //키,몸무게 에딧텍스트 변수 연결
        et_height = findViewById(R.id.et_height);
        et_weight = findViewById(R.id.et_weight);

        //버튼 변수와 리스너 연결
        btnResult = findViewById(R.id.btnResult);
        btnResult.setOnClickListener(onClickListener);

    }//onCreate
        //결과 확인 버튼 --> 값가지고 다음 화면으로
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnResult:
                    Intent intent = new Intent(CalculateActivity.this, ResultActivity.class);
                    //값을 어떻게 가져갈 것인가?
                    //putExtra(string,string)이용
                    intent.putExtra("height",et_height.getText().toString());//key, value
                    intent.putExtra("weight",et_weight.getText().toString());//key, value

                    startActivity(intent);
                    break;
            }//switch
        }
    };



}//Main