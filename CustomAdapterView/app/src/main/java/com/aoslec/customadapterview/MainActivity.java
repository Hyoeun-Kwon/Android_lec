package com.aoslec.customadapterview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //AppCompat 없애면 AppBar 안나옴

    private ArrayList<Weather> data = null; //data
    //adapter가 필요하니까 새로 클래스가 필요한거고!
    private WeatherAdapter adapter = null; //adapter
    private ListView listView = null;//adapter view
    //xml에 있는 listview임
    //private은 안써도 됨


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // activity main으로 activity_main.xml 띄운거 임
        //data 만들기
        data = new ArrayList<Weather>();
//        Weather weather = new Weather("월", R.drawable.w1,"맑음");
//        weather.add(weather);
        //생성자를 통해서 데이터를 넣어줌
        data.add(new Weather("월", R.drawable.w1,"맑음"));
        data.add(new Weather("화", R.drawable.w2,"비"));
        data.add(new Weather("수", R.drawable.w3,"맑음뒤비"));
        data.add(new Weather("목", R.drawable.w4,"추움"));
        data.add(new Weather("금", R.drawable.w5,"쾌청"));
        data.add(new Weather("토", R.drawable.w6,"눈"));
        data.add(new Weather("일", R.drawable.w7,"우박"));
        data.add(new Weather("월", R.drawable.w1,"맑음"));
        data.add(new Weather("화", R.drawable.w2,"비"));
        data.add(new Weather("수", R.drawable.w3,"맑음뒤비"));
        data.add(new Weather("목", R.drawable.w4,"추움"));
        data.add(new Weather("금", R.drawable.w5,"쾌청"));
        data.add(new Weather("토", R.drawable.w6,"눈"));
        data.add(new Weather("일", R.drawable.w7,"우박"));

        //Adapter 연결
        //MainActivity -> 여기에 띄우겠다, 만든 layout으로 , data는 위에있는거 (나중에는 db에 연결 할 거임)
        //Adapter에 만들어놓은 컨스트럭터 생성해준것임
        //where ,
        adapter = new WeatherAdapter(MainActivity.this, R.layout.custom_layout, data);

        //ListView연결
        listView = findViewById(R.id.lv_weather); //activity_main의 listview 임
        listView.setAdapter(adapter);//adapter 위에 생성자와 연결한 값 (즉, 생성자를 통해 가져온 값)

    }//
}//