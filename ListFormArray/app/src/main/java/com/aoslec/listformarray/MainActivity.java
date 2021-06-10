package com.aoslec.listformarray;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<CharSequence> Adapter;
        //메소드 쓰는거임 생성자가 아님
        Adapter = ArrayAdapter.createFromResource(this, R.array.country, android.R.layout.simple_list_item_1);

        //여기가 연결해주는 것임
        ListView list = findViewById(R.id.list);
        list.setAdapter(Adapter);
    }
}