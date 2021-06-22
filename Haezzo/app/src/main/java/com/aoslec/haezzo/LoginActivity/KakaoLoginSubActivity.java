package com.aoslec.haezzo.LoginActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.aoslec.haezzo.MainActivity;
import com.aoslec.haezzo.R;

public class KakaoLoginSubActivity extends AppCompatActivity {

    private String strNick, strProfileImg, strEmail, strGender, strAgeRange;
    EditText editText ;

    private Button btn_Ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_receive_info);

        // 인텐트에서 값을 받아오기
        Intent intent = getIntent();
        strEmail = intent.getStringExtra("email");
        strGender = intent.getStringExtra("gender");
        strAgeRange = intent.getStringExtra("agerange");
        strProfileImg = intent.getStringExtra("profileImg");

        //확인 버튼 클릭 후 메인(->변경:사용자 설정화면)으로 넘기기
        btn_Ok = findViewById(R.id.signup_btnOk);
        btn_Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KakaoLoginSubActivity.this, MainActivity.class);

                // 버튼 클릭 시 나머지 값과 함께 닉네임 값을 보냄
                editText = findViewById(R.id.et_nickname);
                strNick = editText.getText().toString();
                intent.putExtra("nickname", strNick);

                intent.putExtra("email",strEmail);
                intent.putExtra("profileImg",strProfileImg);
                intent.putExtra("gender", strGender);
                intent.putExtra("agerange", strAgeRange);

                startActivity(intent);
            }
        });

    }//onCreate

} //KakaoLoginSubActivity