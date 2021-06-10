package com.aoslec.listtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Data 준비
        //클라스, 변수명 = new 컨스트럭터()
        ArrayList<String> arGeneral = new ArrayList<>();
        //arraylist는 data추가를 add
        arGeneral.add("김유신");
        arGeneral.add("이순신");
        arGeneral.add("강감찬");
        arGeneral.add("을지문덕");

        //data가 많아지면 알아서 뷰에 스크롤이 됨 !

        //Adapter 준비
        ArrayAdapter<String> Adapter;

        //생성자임 어레이리스트를 눌러보면 기본 생성자 형태를 알 수 있다.
        //context, layout, object(data)
        Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arGeneral);
        //simplelayout은 하나씩 보이게 하는것 -> 나중엔 우리가 layout 만들어서 써야함

        //Adapter와 View 연결
        ListView list = findViewById(R.id.list);
        list.setAdapter(Adapter);

    }//onCreate
}//MainActivity