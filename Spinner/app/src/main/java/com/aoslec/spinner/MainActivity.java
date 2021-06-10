package com.aoslec.spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    //char 값을 읽을 수 있는 시퀀스
    ArrayAdapter<CharSequence> adapter = null;
    //data와 adapter 설정 다 된거임
    Spinner spinner = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Context를 필요로하는 곳에 Activity 객체인 this를 념길수 있는 이유는
        //Activity가  Context를 상속 받았기 때문에 Activity는 Context의 모든 정보를 가지고 있는 것이이다.
        //그래서 Context를 필요로 하는 곳에서 Activity의 객체를 넘길수 있는 것이다.

        //Activity 객체를 통해서. Activity 객체 자체가 Context이기도 하기 때문에 Context를
        //   필요로하는 곳에 Activity 객체인 this를 사용하면 된다.
        adapter = ArrayAdapter.createFromResource(this, R.array.travelArea,
                android.R.layout.simple_spinner_dropdown_item);
        //spinner 연결
        spinner = findViewById(R.id.sp_01);
        spinner.setAdapter(adapter);

    }
}