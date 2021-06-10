package com.aoslec.tablelayoutcal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editnum1, editnum2;
    Button add, sub, mul, div;
    TextView textResult;
    Integer num1, num2;
//    String num1 = editnum1.getText().toString();
//    String num2 = editnum2.getText().toString();
    String result;

    //10개 숫자 버튼 배열
    Button[] numButtons = new Button[10];
    //10개 숫자 버튼의 id 값 배열
    //안드는 R.id -->  다 정수임!
    Integer[] numBtnIDs = {R.id.btn00,R.id.btn01,R.id.btn02,R.id.btn03,R.id.btn04,
            R.id.btn05,R.id.btn06,R.id.btn07,R.id.btn08,R.id.btn09};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("계산기");

        editnum1 =findViewById(R.id.editnum1);
        editnum2 = findViewById(R.id.editnum2);

//        btn00.findViewById(R.id.btn00);
//        btn01.findViewById(R.id.btn01);
//        btn02.findViewById(R.id.btn02);
//        btn03.findViewById(R.id.btn03);
//        btn04.findViewById(R.id.btn04);
//        btn05.findViewById(R.id.btn05);
//        btn06.findViewById(R.id.btn06);
//        btn07.findViewById(R.id.btn07);
//        btn08.findViewById(R.id.btn08);
//        btn09.findViewById(R.id.btn09);

        //+,-, *, / 버튼 연결
        add = findViewById(R.id.add);
        sub = findViewById(R.id.sub);
        mul = findViewById(R.id.mul);
        div = findViewById(R.id.div);
        //결과 보여줄 곳 연결
        textResult = findViewById(R.id.textResult);

        //버튼 보였을때 Listener로 가서 결과 계산 시키기
        add.setOnClickListener(mClickListener);
        sub.setOnClickListener(mClickListener);
        mul.setOnClickListener(mClickListener);
        div.setOnClickListener(mClickListener);

        //0~ 9에 대한 버튼 값을 찾아서 넣어야 함
        //그래서 만든게 배열 numBtnIDs 임
        // (숫자 버튼 10개를 대입 )
        for(int i=0; i<numBtnIDs.length; i++){
            numButtons[i] = findViewById(numBtnIDs[i]);
        }
        //숫자 버튼 10개에 대해서 클릭 이벤트 처리
        for (int i=0; i<numBtnIDs.length; i++){
            //index는 버튼 번호 , 바뀌면 안되니까 final 로 줌
            final int index = i;

            numButtons[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //포커스가 되어 있는 에디트 텍스트에 숫자를 추가하겠다
                    //1 누르고 숫자 눌럿다
                    //2 누르고 숫자 눌렀다
                    // 선택 안하고 숫 자 눌렀다 --> 이렇게 총 3가지 생각

                    if(editnum1.isFocused()== true){
                        num1 = Integer.parseInt(editnum1.getText().toString() + numButtons[index].getText().toString());
                        editnum1.setText(Integer.toString(num1));
                    }else if(editnum2.isFocused()== true){
                        num2 = Integer.parseInt(editnum2.getText().toString() + numButtons[index].getText().toString());
                        editnum2.setText(Integer.toString(num2));
                    }else{
                        Toast.makeText(MainActivity.this, "입력 항목부터 선택하세요!",
                        Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

    }//onCreate : 선언 및 초기화 + Lisener 준비 상태ㅜ


    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            if(editnum1.getText().toString().equals("") || editnum1.getText().toString().equals("")){
                Toast.makeText(MainActivity.this, "값을 먼저 입력하세요!",
                        Toast.LENGTH_SHORT).show();
            }else {
                num1 = Integer.parseInt((editnum1.getText().toString()));
                num2 = Integer.parseInt((editnum2.getText().toString()));

                switch (v.getId()) {
                    case R.id.add:
                        result = Integer.toString(num1 + num2);
                        break;
                    case R.id.sub:
                        result = Integer.toString(num1 - num2);
                        break;
                    case R.id.mul:
                        result = Integer.toString(num1 * num2);
                        break;
                    case R.id.div:
                        //                       result= Integer.parseInt(num1) / Integer.parseInt(num2);
                        result = Double.toString(num1 / (double) num2);
                        break;

                }//swich

                textResult.setText("계산결과: " + result);
            }
        }

    };//onClick end


}//onClickLisener end