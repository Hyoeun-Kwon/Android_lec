package com.aoslec.haezzo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

public class SubActivity extends AppCompatActivity {

    private String strNick, strProfileImg, strEmail;
    private Button btn_Ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent intent = getIntent();
        strNick = intent.getStringExtra("name");
        strEmail = intent.getStringExtra("email");
        strProfileImg = intent.getStringExtra("profileImg");

        TextView tv_nick = findViewById(R.id.tv_nickName);
        TextView tv_email = findViewById(R.id.tv_email);
        ImageView iv_profile = findViewById(R.id.iv_profile);

        // 닉네임, 이메일 set
        tv_nick.setText(strNick);
        tv_email.setText(strEmail);


        // Glide를 이용해 이미지 파일 불러와 set
        Glide.with(this).load(strProfileImg).into(iv_profile);

        //확인 버튼 클릭 후 메인(->변경:사용자 설정화면)으로 넘기기
        btn_Ok = findViewById(R.id.sub_btnOk);
        btn_Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(SubActivity.this, SignUpReceiveInfoActivity.class);
//        intent.putExtra("name", strNick);
//        intent.putExtra("email",strEmail);
//        intent.putExtra("profileImg",strProfileImg);
                startActivity(intent1);
            }
        });


        //로그아웃 버튼
        findViewById(R.id.btn_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
                    @Override
                    public void onCompleteLogout() {
                        //로그아웃 성공 시
                        finish(); // 현재 액티비티 종료
                    }
                });
            }
        });

    }
}