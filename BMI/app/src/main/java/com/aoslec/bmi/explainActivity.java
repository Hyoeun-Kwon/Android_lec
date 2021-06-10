package com.aoslec.bmi;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class explainActivity extends AppCompatActivity {

    Button btnOk, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explain);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        btnOk = findViewById(R.id.btnOk);
        btnCancel = findViewById(R.id.btnCancel);

        btnOk.setOnClickListener(onClickListener);
        btnCancel.setOnClickListener(onClickListener);


    }//onCreate
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnOk:
                    //매개변수1 : 현재 context, 2: 어떤 activity로 전활 될건지 (타겟 엑티비티)
                    Intent intent = new Intent(explainActivity.this, CalculateActivity.class);
                    //intent 시작 메소드
                    startActivity(intent);
                    break;
                case R.id.btnCancel:
                    new AlertDialog.Builder(explainActivity.this)
                            .setIcon(R.mipmap.ic_launcher)
                            .setTitle("앱 종료")
                            .setMessage("정말 종료하시겠습니까")
                            .setNegativeButton("아니오", null )
                            .setPositiveButton("예", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            })
                    .show();
                    break;
            }//switch

        }//onClick
    };//listener



}//Main