package com.aoslec.putextra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SecondActivity extends AppCompatActivity {

    TextView textView = null;
    Button button = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textView = findViewById(R.id.tv_second);
        button = findViewById(R.id.btn_second);

        //처음에 값을 받아야 함
        Intent intent = getIntent();
        String userid = intent.getStringExtra("userid");
        int passwd = intent.getIntExtra("passwd", 0); //안들어오면 0을 찍겟다
        textView.setText("UserId :" + userid + " / "+ "Password :"+ passwd);

        button.setOnClickListener(onClickListener);


    }//onCreate

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}//Main