package com.aoslec.calculationlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnc;
    TextView viewPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //btnc
        btnc = findViewById(R.id.btnc);
        btnc.setOnClickListener(onClickListener);
        //textview
        viewPage = findViewById(R.id.viewPage);


    }//onCreate
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            viewPage.setText("0");
        }
    };
}//main