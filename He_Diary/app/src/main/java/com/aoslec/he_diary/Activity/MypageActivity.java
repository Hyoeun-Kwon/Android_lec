package com.aoslec.he_diary.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.aoslec.he_diary.R;
import com.bumptech.glide.Glide;

public class MypageActivity extends AppCompatActivity {

    ImageView img_rabbit;
    Button btnLogout;
    TextView phonenumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        img_rabbit = findViewById(R.id.img_rabbit);

        Glide.with(this).load(R.raw.rabbit_heart).into(img_rabbit);

                //로그아웃 작업
        btnLogout = findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(onClickListener);

        phonenumber = findViewById(R.id.phonenum);
        phonenumber.setOnClickListener(onClickListener);

    }//onCreate

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btn_logout:
                    //로그아웃 하기
                    Intent intent = new Intent(MypageActivity.this, LoginActivity.class);
                    startActivity(intent);
                    //finish();//앱 끄기
                    break;
                case R.id.phonenum:
                    intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:013-2222-1111"));
                    startActivity(intent);
                    break;
            }
        }
    };
}//main