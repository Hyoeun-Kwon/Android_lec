package com.aoslec.project2;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnadd, btnsub, btnmul, btndiv;
    EditText edit1, edit2;
    Integer num1, num2;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnadd = findViewById(R.id.btnadd);
        btnsub = findViewById(R.id.btnsub);
        btnmul = findViewById(R.id.btnmul);
        btndiv = findViewById(R.id.btndiv);

        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);




        btnadd.setOnClickListener(onClickedListener);
        btnsub.setOnClickListener(onClickedListener);
        btnmul.setOnClickListener(onClickedListener);
        btndiv.setOnClickListener(onClickedListener);

    }//onCreate

    View.OnClickListener onClickedListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            if(edit1.getText().toString().equals("")||edit2.getText().toString().equals("")){
                Toast.makeText(MainActivity.this, "숫자를 입력해주세요", Toast.LENGTH_SHORT).show();
            }else {
                num1 = Integer.parseInt((edit1.getText().toString()));
                num2 = Integer.parseInt((edit2.getText().toString()));

                switch (v.getId()) {
                    case R.id.btnadd:
                        result = Integer.toString(num1 + num2);
                        break;
                    case R.id.btnsub:
                        result = Integer.toString(num1 - num2);
                        break;
                    case R.id.btnmul:
                        result = Integer.toString(num1 * num2);
                        break;
                    case R.id.btndiv:
                        if (edit1.getText().toString().equals("0") || edit2.getText().toString().equals("0")) {
                            Toast.makeText(MainActivity.this, "0외의 값을 입력해주세요", Toast.LENGTH_SHORT).show();
                        } else {
                            result = Integer.toString(num1 / num2);
                        }
                        break;
                }
                Toast.makeText(MainActivity.this, result + "입니다.", Toast.LENGTH_SHORT).show();
            }
        }
    };//listener

}//MainaAtivity