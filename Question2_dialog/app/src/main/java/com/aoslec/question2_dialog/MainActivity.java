package com.aoslec.question2_dialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnQuestion;
    int num1 , num2 , result;
    TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnQuestion = findViewById(R.id.btnQuestion);
        btnQuestion.setOnClickListener(onClickListener);

        tv_result = findViewById(R.id.tv_result);


    }//onCreate
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("질문")
                        .setMessage("좌변을 선택하십시오")
                        .setCancelable(false)
                        .setNegativeButton("4", num1Click)
                        .setPositiveButton("3",num1Click)
                        .show();
            }
        };

    DialogInterface.OnClickListener num1Click = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if(which == DialogInterface.BUTTON_NEGATIVE){
                num1 = 4;
            }
            if(which == DialogInterface.BUTTON_POSITIVE){
                num2 = 3;
            }
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("질문")
                    .setMessage("우변을 선택하십시오")
                    .setCancelable(false)
                    .setNegativeButton("6", num2Click)
                    .setPositiveButton("5",num2Click)
                    .show();
        }
    };

    DialogInterface.OnClickListener num2Click = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if(which == DialogInterface.BUTTON_NEGATIVE){
                num1 = 6;
            }
            if(which == DialogInterface.BUTTON_POSITIVE){
                num2 = 5;
            }
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("질문")
                    .setMessage("어떤 연산을 하시겠습니까")
                    .setCancelable(false)
                    .setNegativeButton("곱셈", calClick)
                    .setPositiveButton("덧셈",calClick)
                    .show();
        }
    };

    DialogInterface.OnClickListener calClick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if(which == DialogInterface.BUTTON_NEGATIVE){
                result = num1 * num2;
            }
            if(which == DialogInterface.BUTTON_POSITIVE){
                result = num1 + num2;
            }

            tv_result.setText(Integer.toString(result));
        }
    };

}//Main