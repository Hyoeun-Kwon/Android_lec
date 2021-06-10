package com.aoslec.callactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("Message", "onCreate_MainActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //xml과 연결
        Button btnCall = findViewById(R.id.call);
        //속성 넣기
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent 는 from, to 만 정해주면 됨
                //컴파일되면 자바는 클래스로 바뀌므로
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                startActivity(intent);
            }
        });

    }//onCreate

    @Override
    protected void onStart() {
        Log.v("Message", "onStart_MainActivity");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.v("Message", "onResume_MainActivity");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.v("Message", "onPause_MainActivity");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.v("Message", "onStop_MainActivity");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.v("Message", "onDestroy_MainActivity");
        super.onDestroy();
    }
}//Main