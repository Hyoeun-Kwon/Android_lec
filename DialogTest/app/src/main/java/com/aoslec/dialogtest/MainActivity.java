package com.aoslec.dialogtest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.call);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                AlertDialog.Builder bld = new AlertDialog.Builder(MainActivity.this); //어디에 만드냐? 메인에
//                //위의 식으로 틀만든것임
//                bld.setTitle("알립니다.");
//                bld.setMessage("대화 상자를 열었습니다.");
//                bld.setIcon(R.mipmap.ic_launcher);
//                bld.show();//이렇게만은 잘 안씀
                new AlertDialog.Builder(MainActivity.this) //어디에 만드냐? 메인에
                //위의 식으로 틀만든것임
                    .setTitle("알립니다.")
                    .setMessage("대화 상자를 열었습니다.")
                    .setIcon(R.drawable.w4) // icon을 이미지로 넣어도 됨
                    .setCancelable(false) //꼭 버튼을 눌러야만 닫아지게 함 !!!! 꼭 넣기
                    .setPositiveButton("닫기",null)
                    .show();

            }
        });
    }
}