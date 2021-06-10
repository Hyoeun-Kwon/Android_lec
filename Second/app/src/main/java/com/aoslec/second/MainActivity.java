package com.aoslec.second;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //코딩은 자바에!! xml은 화면(그림그린 것)= activity_main.xml
    //class 는 프로퍼티,
    //버튼 타입이다 선언 해줌
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // 화면에 그림을 그리겠다
        //연결 시킴 (화면과 코딩(자바)을)
        button = findViewById(R.id.btnOk);

        //이벤트 활성화 시키면 lisner로 받아야 함
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "버튼을 누르다!", Toast.LENGTH_SHORT).show();
            }
        });


    }
}