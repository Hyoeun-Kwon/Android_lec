package com.aoslec.henim_diary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth;     //파이어베이 인증 처리
    private DatabaseReference mDatabaseRef; //실시간 데이터베이스
    private EditText mEtemail,mEt_pwd;       //회원가입 입력필드(이메일,패스워드)
    private Button mBtnRegister; //문자인증 버튼 및 회원가입 버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("HenimDiary");
        //초기화
        mEtemail = findViewById(R.id.reg_etEmail);
        mEt_pwd = findViewById(R.id.reg_etPwd);
        mBtnRegister = findViewById(R.id.reg_btnRegister);
        //회원가입 클릭리스너 연결
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //회원가입 처리 시작
                String strEmail = mEtemail.getText().toString();
                String strPwd = mEt_pwd.getText().toString();

                //Firebase auth 진행
                mFirebaseAuth.createUserWithEmailAndPassword(strEmail,strPwd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    //인증처리가 완료가 된 부분
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if(task.isSuccessful()) //정상적으로 가져와서 가입 성공을 했으면
                        {
                            FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser(); //현재의 로그인된 유저를 가져온다
                            //웹 가서, 인증- 이메일/비밀번호 사용 , realtimebase
                            UserAccount account = new UserAccount();
                            account.setIdToken(firebaseUser.getUid());
                            account.setEmailId(firebaseUser.getEmail());//로그인된 유저 정확히 가져오기 위함
                            account.setPassword(strPwd);

                            //setValue : database에 insert 행위
                            mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).setValue(account);
                            //--------------여기까지 회원가입 성공
                            //메세지
                            Toast.makeText(RegisterActivity.this, "회원가입에 성공하였습니다.", Toast.LENGTH_SHORT).show();


                        }else {
                            Toast.makeText(RegisterActivity.this, "회원가입에 실패하였습니다.", Toast.LENGTH_SHORT).show();

                        }


                    }
                });


            }
        });

    }//
}//