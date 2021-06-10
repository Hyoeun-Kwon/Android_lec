package com.aoslec.orderdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.call);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final LinearLayout linear = (LinearLayout) View.inflate(MainActivity.this, R.layout.order,null);
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("주문 정보를 입력하세요")
                        .setIcon(R.mipmap.ic_launcher)
                        .setView(linear)//우리가 만든 레이아웃(여기에 그려짐)
                        //이제 확인버튼 달고, 다이얼 로그에 있는 값 가져와야 함!?
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                               //order에 있는거 가져와야함
                                EditText product = linear.findViewById(R.id.product);
                                EditText number = linear.findViewById(R.id.number);
                                CheckBox paymethod = linear.findViewById(R.id.paymethod);
                                //main의 textview 연결
                                TextView textView = findViewById(R.id.text);
                                textView.setText("주문정보 : " + product.getText() + "상품, :" + number.getText() + "개" + (paymethod.isChecked() ? "착불결재" : ""));///삼항연산자 사용 (check box 가 체크 되어있는지 아닌지 모르므로)

                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Textview를 다시 잡아줘야함
                                TextView textView = findViewById(R.id.text);
                                textView.setText("주문을 취소했습니다.");
                            }
                        })
                        .show(); //dialog는 소프트 키보드 켜도 알아서 올라감
                //나중에 에딧텍스트에서는 소프트키보드 올라갔을때 올리는거 신경써야함 !
            }
        });

    }//onCreate
}