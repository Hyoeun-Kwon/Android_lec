package com.aoslec.quiz0;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //버튼타입 , 변수이름 -> 선언하
    Button btnRed1, btnGreen1, btnBlue1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//화면 띄우기

        //xml과 java 연결 (아이디 값으로 이미지를 찾아라
        btnRed1 = findViewById(R.id.btnRed);
        btnGreen1 = findViewById(R.id.btnGreen);
        btnBlue1 = findViewById(R.id.btnBlue);

        // 이벤트  1. 빨강 2. 초록 3. 파랑 할건데, 클릭때 저거로 갈거야
        //버튼 누르면어떻게 할거야 , Listener는 준비, 대기상태
        //준비 상태가 왜 필요? 핸드폰이라 cpu 가 아무리 빨라도 느리기 때문
        btnRed1.setOnClickListener(onClickListener);
        btnGreen1.setOnClickListener(onClickListener);
        btnBlue1.setOnClickListener(onClickListener);

    }//여기 까지가 하나의 메소드임 onCreate // 화면에 띄워 둠
    //new까지 치고 컨트롤 스페이트 !, onClicklistener는 view로 !

    //메소드를 나누는게 무조건 좋다
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        String colorString="";
            // switch 문에서 id 값을 받아 오겟다
            switch (v.getId()) {
                  case R.id.btnRed:
                      colorString="빨간색";
                      break;
                  case R.id.btnGreen:
                      colorString="녹색";
                      break;
                  case R.id.btnBlue:
                      colorString="파랑색";
                      break;
          }
            Toast.makeText(MainActivity.this, colorString+" 버튼을 눌렀습니다", Toast.LENGTH_SHORT).show();
        }
    }; // 변수에 함수 기능이 들어간거를 클로우저 함수? 라고 부름
}