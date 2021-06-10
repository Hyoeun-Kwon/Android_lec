package com.aoslec.frametest2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Button button1, button2;
    LinearLayout linear1, linear2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.mainButton1);
        button2 = findViewById(R.id.mainbutton2);
        linear1 = findViewById(R.id.linear1);
        linear2 = findViewById(R.id.linear2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if(linear1.getVisibility() == v.VISIBLE){
//                    linear1.setVisibility(v.INVISIBLE);
//                    linear2.setVisibility(v.VISIBLE);
//                }else {
//                    linear1.setVisibility(v.VISIBLE);
//                    linear2.setVisibility(v.INVISIBLE);
//                }

                linear1.setVisibility(v.VISIBLE);
                linear2.setVisibility(v.INVISIBLE);
            }
        });

        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if(linear2.getVisibility() == v.VISIBLE){
                    linear2.setVisibility(v.INVISIBLE);
                    linear1.setVisibility(v.VISIBLE);
                }else {
                    linear2.setVisibility(v.VISIBLE);
                    linear1.setVisibility(v.INVISIBLE);
                }
            }


        });

    }
}