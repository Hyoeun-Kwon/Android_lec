package com.aoslec.quiz6_touchmessage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    TextView tv_fruit;
    Button btnApple, btnOrange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_fruit = findViewById(R.id.tv_fruit);
        tv_fruit.setOnTouchListener(onTouchListener);

        //button 2개 연결
        btnApple = findViewById(R.id.btnApple);
        btnOrange = findViewById(R.id.btnOrange);

        //버튼 listener에 연결
        btnApple.setOnClickListener(onClickListener);
        btnOrange.setOnClickListener(onClickListener);

        //시간딜레이 사용해보기
       ;

    }//onCreate

    View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            Toast.makeText(MainActivity.this, "새콤달콤", Toast.LENGTH_SHORT).show();

            return true;
        }
    };

    //button
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btnApple:
                    tv_fruit.setText("APPLE");
                    tv_fruit.setTextColor(0xFFF61212);
                    Snackbar.make(v, "APPLE IS SELECTED", Snackbar.LENGTH_SHORT).show();

                    break;
                case R.id.btnOrange:
                    tv_fruit.setText("ORANGE");
                    tv_fruit.setTextColor(0xFFFF5900);
                    Snackbar.make(v, "ORANGE IS SELECTED", Snackbar.LENGTH_SHORT).show();
                    break;
            }//switch


        }
    };





}//Main