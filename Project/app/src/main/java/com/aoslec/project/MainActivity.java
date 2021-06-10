package com.aoslec.project;

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

        num1 = Integer.parseInt(edit1.getText().toString());
        num2 = Integer.parseInt(edit2.getText().toString());


        btnadd.setOnClickListener(onClickedListener);
        btnsub.setOnClickListener(onClickedListener);
        btnmul.setOnClickListener(onClickedListener);
        btndiv.setOnClickListener(onClickedListener);

    }//onCreate

    View.OnClickListener onClickedListener = new View.OnClickListener() {
        String str;
        int result;
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btnadd:
                    result = num1 + num2;
                    str=Integer.toString(result);

                    break;
                case R.id.btnsub:
                    result = num1 - num2;
                    str=Integer.toString(result);
                     break;
                case R.id.btnmul:
                    result = num1 * num2;
                    str=Integer.toString(result);
                    break;
                case R.id.btndiv:
                    result = num1 / num2;
                    str=Integer.toString(result);
                    break;
            }
            Toast.makeText(MainActivity.this, str+ "입니다.", Toast.LENGTH_SHORT).show();
        }
    };//listener

}//MainaAtivity