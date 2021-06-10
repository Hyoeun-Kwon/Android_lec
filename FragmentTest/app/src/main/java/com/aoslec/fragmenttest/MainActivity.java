package com.aoslec.fragmenttest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements ToolBarFragment.ToolbarListener{
    //ToolbarListener는 아직 안만들었음
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("Message","MainActivity onCreate");
    }

    @Override
    public void onButtonClick(int position, String text) {
        //값을 받아서 fragment2로 보냄
        TextFragment textFragment = (TextFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_2);
        Log.v("Message","MainActivity textFragment");
        //textfragment 메소드 만들어주러 가자
        //textfragment에 값 주자
        textFragment.changeTextProperties(position, text);
        Log.v("Message","MainActivity changeTextProperties");
    }
}