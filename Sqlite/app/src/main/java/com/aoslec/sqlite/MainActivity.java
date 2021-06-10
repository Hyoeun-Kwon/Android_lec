package com.aoslec.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnInsert, btnUpgrade, btnDelete,btnSelect;
    TextView tvResult;
    Memberinfo memberinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Memberinfo instance 생성 --> 하면 알아서 memberinfo내에 onCreate와 onUpgrade 구동 됨



    }//onCreate
}//MainActivity