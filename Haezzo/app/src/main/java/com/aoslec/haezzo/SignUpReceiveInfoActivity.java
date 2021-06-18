package com.aoslec.haezzo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUpReceiveInfoActivity extends AppCompatActivity {

    Button signup_btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_receive_info);

        //확인 버튼 초기화
        signup_btnOk = findViewById(R.id.signup_btnOk);
        signup_btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent = new Intent(SignUpReceiveInfoActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }//onCreate
}//