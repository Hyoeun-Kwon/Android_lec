package com.aoslec.callother;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.web).setOnClickListener(mClickListener);
        findViewById(R.id.dial).setOnClickListener(mClickListener);
        //다른 앱 구동 시켜 볼 것
        findViewById(R.id.list).setOnClickListener(mClickListener);

    }//
    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()){
                case R.id.web:
                    //명시적 인텐트
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
                    startActivity(intent);
                    break;
                case R.id.dial:
                    intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:010-1111-2222"));
                    startActivity(intent);
                    break;
                case R.id.list:
                    intent = new Intent(Intent.ACTION_MAIN);
                    //package이름 알아야 함
                    intent.setComponent(new ComponentName("com.aoelec.listadddel","com.aoslec.listadddel.MainActivity"));
                    startActivity(intent);
                    break;
            }
        }
    };

}//