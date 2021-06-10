package com.aoslec.callactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("Message", "onCreate_SubActivity");
        super.onCreate(savedInstanceState);
        //화면 회전
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_sub);


        Button btnClose = findViewById(R.id.close);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }//onCreate

    @Override
    protected void onStart() {
        Log.v("Message", "onStart_SubActivity");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.v("Message", "onResume_SubActivity");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.v("Message", "onPause_SubActivity");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.v("Message", "onStop_SubActivity");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.v("Message", "onDestroy_SubActivity");
        super.onDestroy();
    }



}//Main