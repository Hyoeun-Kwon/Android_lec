package com.aoslec.listener02quiz_touch_long;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tv_hello;
    Button btn_gray;
    ImageView iv_dog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_hello = findViewById(R.id.tv_hello);
        btn_gray = findViewById(R.id.btn_gray);
        iv_dog = findViewById(R.id.iv_dog);

        //리스너에 연결
        tv_hello.setOnTouchListener(onTouchListener);
        btn_gray.setOnLongClickListener(onLongClickListener);
        iv_dog.setOnTouchListener(onTouchListener);


    }//

    //onTouch
    View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            switch(v.getId()){
                case R.id.tv_hello:
                    Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.iv_dog:
                    Toast.makeText(MainActivity.this, "Cute Dog", Toast.LENGTH_SHORT).show();
                    break;
            }

            return true;
        }
    };

    //Long
    View.OnLongClickListener onLongClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            switch (v.getId()){
                case R.id.btn_gray:
                    Toast.makeText(MainActivity.this, "grey가 great", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    };


}//