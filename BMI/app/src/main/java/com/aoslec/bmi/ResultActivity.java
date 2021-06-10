package com.aoslec.bmi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class ResultActivity extends AppCompatActivity {

    TextView tv_height, tv_weight, tv_result;

    String weight, height, str;

    Double result;

    //image
    ImageView img_ex1, img_ex2, img_ex3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        tv_weight = findViewById(R.id.tv_weight);
        tv_height = findViewById(R.id.tv_height);
        tv_result = findViewById(R.id.tv_result);

        //intent 값을 받기 위함
        Intent intent = getIntent();
        height = intent.getStringExtra("height");
        weight = intent.getStringExtra("weight");

        //textview에 넣어주기
        tv_weight.setText(weight);
        tv_height.setText(height);

        //값 계산해주기
        double result_height = Double.parseDouble(tv_height.getText().toString())/100;
        Integer result_weight = Integer.parseInt(tv_weight.getText().toString());

        result = result_weight/(result_height*result_height);
        String rs = String.format("%.2f", result);


        if(result>=35){
            str = rs+" 는 고도비만 입니다.";
        }else if(result>=30){
            str = rs+" 는 중도비만 입니다.";
        }else if(result>=25){
            str = rs+ " 는 경도비만 입니다.";
        }else if(result>=23){
            str = rs + " 는 과체중 입니다.";
        }else if(result>=18.5){
            str = rs + " 는 정상체중 입니다.";
        }else{
            str = rs + " 는 저체중 입니다.";
        }

        tv_result.setText(str);

        //image 넣기
        img_ex1 = findViewById(R.id.img_ex1);
        img_ex2 = findViewById(R.id.img_ex2);
        img_ex3 = findViewById(R.id.img_ex3);

        Glide.with(this).load(R.raw.cycling).into(img_ex1);
        Glide.with(this).load(R.raw.running).into(img_ex2);
        Glide.with(this).load(R.raw.walkcycle).into(img_ex3);


    }//onCreate

}//Main