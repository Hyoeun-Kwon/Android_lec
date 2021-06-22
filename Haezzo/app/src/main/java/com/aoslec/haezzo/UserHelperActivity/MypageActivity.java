package com.aoslec.haezzo.UserHelperActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.aoslec.haezzo.LoginActivity.KakaoLoginActivity;
import com.aoslec.haezzo.Pay1Activity;
import com.aoslec.haezzo.MainActivity;
import com.aoslec.haezzo.MyHaezzoListActivity;
import com.aoslec.haezzo.R;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

public class MypageActivity extends AppCompatActivity {
    Button button;
    BottomNavigationView main_bottomNavigationView;
    private String strNick, strProfileImg, strEmail, strGender, strAgeRange, strAddress;
    TextView tv_nickname, tv_gender, tv_agerange, tv_email, tv_address;
    ImageView iv_profileimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        //네비게이션
        main_bottomNavigationView = (BottomNavigationView)findViewById(R.id.main_bottom_navigation);
        main_bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        //Intent 에서 값 넘겨오기
        Intent intent = getIntent();
        strNick = intent.getStringExtra("nickname");
        strEmail = intent.getStringExtra("email");
        strProfileImg = intent.getStringExtra("profileImg");
        strGender = intent.getStringExtra("gender");
        strAgeRange = intent.getStringExtra("agerange");
        strAddress = intent.getStringExtra("address");

        tv_nickname = findViewById(R.id.tv_nickname);
        tv_gender = findViewById(R.id.tv_gender);
        tv_agerange = findViewById(R.id.tv_agerange);
        tv_email = findViewById(R.id.tv_email);
        tv_address = findViewById(R.id.tv_address);
        iv_profileimg = findViewById(R.id.iv_profileimg);

        // 마이페이지에 값 설정
        tv_nickname.setText(strNick);
        tv_email.setText(strEmail);
        tv_gender.setText(strGender);
        tv_agerange.setText(strAgeRange);
        tv_address.setText(strAddress);

        //Glide를 이용해 이미지 파일 불러와 프로필 사진으로 set
        Glide.with(this).load(strProfileImg).into(iv_profileimg);


        // 결제 버튼 (임시)
        button = findViewById(R.id.test);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MypageActivity.this, Pay1Activity.class);
                startActivity(intent);
            }
        });


        //로그아웃 버튼
        findViewById(R.id.myPage_btnLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
                    @Override
                    public void onCompleteLogout() {
                        Intent intent = new Intent(MypageActivity.this, KakaoLoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });
            }
        }); // logout


        //        btnSignout.setOnClickListener(new Button.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                new AlertDialog.Builder(MainActivity.this)
        //                        .setMessage("탈퇴하시겠습니까?")
        //                        .setPositiveButton("네", new DialogInterface.OnClickListener() {
        //                            @Override
        //                            public void onClick(DialogInterface dialog, int which) {
        //                                UserManagement.getInstance().requestUnlink(new UnLinkResponseCallback() {
        //                                    @Override
        //                                    public void onFailure(ErrorResult errorResult) {
        //                                        int result = errorResult.getErrorCode();
        //
        //                                        if(result == ApiErrorCode.CLIENT_ERROR_CODE) {
        //                                            Toast.makeText(getApplicationContext(), "네트워크 연결이 불안정합니다. 다시 시도해 주세요.", Toast.LENGTH_SHORT).show();
        //                                        } else {
        //                                            Toast.makeText(getApplicationContext(), "회원탈퇴에 실패했습니다. 다시 시도해 주세요.", Toast.LENGTH_SHORT).show();
        //                                        }
        //                                    }
        //
        //                                    @Override
        //                                    public void onSessionClosed(ErrorResult errorResult) {
        //                                        Toast.makeText(getApplicationContext(), "로그인 세션이 닫혔습니다. 다시 로그인해 주세요.", Toast.LENGTH_SHORT).show();
        //                                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        //                                        startActivity(intent);
        //                                        finish();
        //                                    }
        //
        //                                    @Override
        //                                    public void onNotSignedUp() {
        //                                        Toast.makeText(getApplicationContext(), "가입되지 않은 계정입니다. 다시 로그인해 주세요.", Toast.LENGTH_SHORT).show();
        //                                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        //                                        startActivity(intent);
        //                                        finish();
        //                                    }
        //
        //                                    @Override
        //                                    public void onSuccess(Long result) {
        //                                        Toast.makeText(getApplicationContext(), "회원탈퇴에 성공했습니다.", Toast.LENGTH_SHORT).show();
        //                                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        //                                        startActivity(intent);
        //                                        finish();
        //                                    }
        //                                });
        //
        //                                dialog.dismiss();
        //                            }
        //                        })
        //                        .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
        //                            @Override
        //                            public void onClick(DialogInterface dialog, int which) {
        //                                dialog.dismiss();
        //                            }
        //                        }).show();
        //            }
        //        });
        //    }
        //} btnSignout

    } //onCreate

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    // mTextMessage.setText(R.string.title_home);
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_list:
                    startActivity(new Intent(getApplicationContext(), MyHaezzoListActivity.class));
                    finish();
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_mypage:
                    startActivity(new Intent(getApplicationContext(), MypageActivity.class));
                    finish();
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        }
    };


} // MypageActivity