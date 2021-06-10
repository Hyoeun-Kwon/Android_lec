package com.aoslec.quiz14_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    //RValue!
    EditText et_mainId = null;
    EditText et_mainPwd = null;
    Button btn_Ok = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_mainId = findViewById(R.id.et_mainId);
        et_mainPwd = findViewById(R.id.et_mainPwd);
        btn_Ok = findViewById(R.id.btn_mainOk);

        btn_Ok.setOnClickListener(onClickListener);

    }//onCreate

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch(v.getId()){
                case R.id.btn_mainOk:
                    if(et_mainId.getText().toString().equals("aaa") && et_mainPwd.getText().toString().equals("1111")){
                        intent = new Intent(MainActivity.this, SubActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(MainActivity.this,"틀렸습니다.", Toast.LENGTH_SHORT).show();

                    }
                    break;

            }

        }
    };



}//Main