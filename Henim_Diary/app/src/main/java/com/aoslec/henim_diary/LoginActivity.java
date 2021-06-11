package com.aoslec.henim_diary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;     //파이어베이 인증 처리
    private DatabaseReference mDatabaseRef; //실시간 데이터베이스
    private EditText mEtemail,mEt_pwd;       //로그인 입력필드(이메일,패스워드)
    Button login_btnRegister,login_btnLogin;    //로그인 회원가입 버튼
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);//하나의 화면만 연결됨

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("HenimDiary");
        //초기화
        mEtemail = findViewById(R.id.login_etemail);
        mEt_pwd = findViewById(R.id.login_etpwd);


        //로그인
        login_btnLogin = findViewById(R.id.login_btnLogin);
        login_btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //로그인 요청 버튼
                String strEmail = mEtemail.getText().toString();
                String strPwd = mEt_pwd.getText().toString();

                mFirebaseAuth.signInWithEmailAndPassword(strEmail,strPwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                          //로그인 성공
                          Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                          startActivity(intent);
                          finish();//현재 액티비티 파괴
                        }else{
                            Toast.makeText(LoginActivity.this, "로그인이 실패하였습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });







        //회원가입
        login_btnRegister = findViewById(R.id.login_btnRegister);
        login_btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //회원가입 화면으로 이동
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }//
}//