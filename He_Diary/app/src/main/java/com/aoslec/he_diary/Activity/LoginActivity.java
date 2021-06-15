package com.aoslec.he_diary.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aoslec.he_diary.Bean.Register;
import com.aoslec.he_diary.NetworkTask.NetworkTaskRegister;
import com.aoslec.he_diary.R;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    Intent intent = null;
    Button btnLogin, btnRegister;

    //edittext 값 초기화 및 입력된 값 받아오기(sName,sPwd)
    EditText inputName, inputPwd;
    String sName, sPwd;

    //맨처음 화면 : ip주소 저장
    String myIP = "192.168.35.20";

    //urlAddr :  값을 불러올때 사용 : 이 주소에 있는거 해주세요 (jsp 연결)
    String urlAddr = null;//만들어줄 어드레스
    //Register(빈)에 있는 값을 어레이리스트에 담기
    ArrayList<Register> registers;
    //값을 불러와서 저장해둘 곳 (name, password)
    String dName, dPwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //로그인
        btnLogin = findViewById(R.id.login_btnLogin);
        btnLogin.setOnClickListener(onClickListener);
        //회원가입
        btnRegister = findViewById(R.id.login_btnRegister);
        btnRegister.setOnClickListener(onClickListener);
        //에딧텍스트
        inputName = findViewById(R.id.login_etname);
        inputPwd = findViewById(R.id.login_etpwd);

        //글자 수 입력 제한
        inputName.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
        inputPwd.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});



    }//onCreate

        View.OnClickListener onClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.login_btnLogin:
                        //에딧텍스트에 있는 값 받아오기
                        sName = inputName.getText().toString();
                        sPwd = inputPwd.getText().toString();

                        loginCheck(); // database에서 id, pwd 값 불러오기
                        if(sName.equals(dName) && sPwd.equals(dPwd)){
                            intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("myIP",myIP);
                            // 로그인에 입력된 값(아이디,패스워드) 받아와서 intent에 담기
                            intent.putExtra("userName", sName);
                            intent.putExtra("userPwd", sPwd);
                            Log.v("Message","Login_myIP:"+ myIP);
                            startActivity(intent);
                        }else{
                            Toast.makeText(LoginActivity.this, "회원정보가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.login_btnRegister:
                        intent = new Intent(LoginActivity.this, RegisterActivity.class);
                        intent.putExtra("myIP",myIP);
                        startActivity(intent);
                        break;
                }
            }
        };//onClickListener

    public void loginCheck() {
        Log.v("Message", "METHOD : loginCheck");
        try {
            urlAddr = "http://" + myIP + ":8080/test/diaryLoginCheck.jsp?name=" + sName;
            Log.v("Message", "  - loginCheck sName : " + sName);
            Log.v("Message", "  - loginCheck Url : " + urlAddr);

            NetworkTaskRegister networkTaskRegister = new NetworkTaskRegister(LoginActivity.this, urlAddr, "select");
            Object obj = networkTaskRegister.execute().get();

            registers = (ArrayList<Register>) obj;
            dName = registers.get(0).getName();
            dPwd = registers.get(0).getPassword();
            Log.v("Message", "  - loginCheck _ dName : " + dName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//loginCheck


}//Main