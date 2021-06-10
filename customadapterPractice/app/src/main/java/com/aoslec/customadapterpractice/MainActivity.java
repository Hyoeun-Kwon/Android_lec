package com.aoslec.customadapterpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    private ArrayList<BTS> data = null;
    private BTSAdapter adapter = null;
    private ListView listView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = new ArrayList<BTS>();

        data.add(new BTS("정국", R.drawable.jungkuk, "25"));
        data.add(new BTS("RM", R.drawable.rm, "28"));
        data.add(new BTS("진", R.drawable.jin, "30"));
        data.add(new BTS("슈가", R.drawable.sugar, "29"));
        data.add(new BTS("제이홉", R.drawable.jhop, "28"));
        data.add(new BTS("지민", R.drawable.jimin, "25"));
        data.add(new BTS("뷔", R.drawable.byui, "25"));

        //Adapter 연결
        adapter = new BTSAdapter(MainActivity.this, R.layout.custom_layout, data);

        //ListView 연결
        listView = findViewById(R.id.lv_bts);
        listView.setAdapter(adapter);


    }//
}//