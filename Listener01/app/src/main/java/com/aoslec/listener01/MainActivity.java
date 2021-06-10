package com.aoslec.listener01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout cl = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cl = findViewById(R.id.cl01);
        //설정은 set! 받는애는 set없
        cl.setOnClickListener(onClickListener);
        cl.setOnLongClickListener(onLongClickListener);

    }//onCreate

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity.this, "OnClick()", Toast.LENGTH_SHORT).show();
        }
    };

    //onClick은 void, onLongClick은 boolean 이다.
    View.OnLongClickListener onLongClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            Toast.makeText(MainActivity.this, "OnLongClick()", Toast.LENGTH_SHORT).show();
//            return false;
            return true;
        }
    };



}//Main