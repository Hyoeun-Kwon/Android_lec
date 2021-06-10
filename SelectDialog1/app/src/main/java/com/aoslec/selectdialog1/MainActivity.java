package com.aoslec.selectdialog1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int mSelect = 0;
    boolean[] mSelectMulti = {false, false, false, false};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.call);
        //레디오 버튼
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("음식을 선택하세요")
                        .setIcon(R.mipmap.ic_launcher)
                        .setMultiChoiceItems(R.array.foods, mSelectMulti,
                                new DialogInterface.OnMultiChoiceClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                        mSelectMulti[which] = isChecked;
                                    }
                                }
                        )
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String[] foods = getResources().getStringArray(R.array.foods); //data 넣기
                                TextView textView = findViewById(R.id.text);
                                String result = "선택한 음식 ";

                                for(int i=0; i<mSelectMulti.length; i++){
                                    if(mSelectMulti[i]){//true 면
                                        result +=foods[i] + " / ";

                                    }
                                }//for
                                textView.setText(result);
                            }
                        })
                        .setNegativeButton("취소",null)
                        .show();
            }
        });



        //다른 방법
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new AlertDialog.Builder(MainActivity.this)
//                        .setTitle("음식을 선택하세요")
//                        .setIcon(R.mipmap.ic_launcher)
//                        //가만히 보면 이런게 어댑터 만들어 진거임
//                        .setSingleChoiceItems(R.array.foods, mSelect,
//                                new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        mSelect = which;//몇번째 했냐만 알자
//
//                                    }
//                                }
//
//                        )
//                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                String[] foods = getResources().getStringArray(R.array.foods);
//                                TextView textView = findViewById(R.id.text);
//                                textView.setText("선택한 음식 :" + foods[mSelect]);
//                            }
//                        })
//                        .setNegativeButton("취소",null)
//                        .show();
//            }
//        });


//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new AlertDialog.Builder(MainActivity.this)
//                        .setTitle("음식을 선택하세요 ")
//                        .setIcon(R.mipmap.ic_launcher)
//                        .setItems(R.array.foods,
//                                //얘를 클릭했을때 다이얼로그를 띄워주기
//                                new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {//which: 몇번째냐
//                                        //xml에 값이 있으므로 배열로 받아서 가져옴
//                                        String[] foods = getResources().getStringArray(R.array.foods);
//                                        TextView textView = findViewById(R.id.text);
//                                        textView.setText("선택한 음식 " + foods[which]);
//                                    }
//                                }
//                        )
//                        .setNegativeButton("취소",null)
//                        .show();
//            }
//        });

    }//onCreate
}