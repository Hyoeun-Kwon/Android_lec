package com.aoslec.listadddel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ArrayList<String> items;// 목록이네!
    ArrayAdapter<String> adapter;

    ListView list; //activity main에서 만든애랑 연결하려


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //data가 3개 뿐이라서 여기서 작업
        items = new ArrayList<String>();
        items.add("First");
        items.add("Second");
        items.add("Third");

        //adapter
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice,items);//레디오같은 모양으로 만들어준것임
        //android ---> 자체 디폴트에 없음 외부에 layout이 있어서 android 쓴것

       //연결시켜 주기
        list = findViewById(R.id.list);
        //넣어주기
        list.setAdapter(adapter);
        //data-> adapter -> 연결시켜주기 -> adapter넣어줘서 화면 구현

        //list에 클릭시 색상 넣어주자!(레디오버튼 누르듯! )
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        //아이템 클릭시 리스너에 연결해줄거임
        list.setOnItemClickListener(mItemClickListener);//---> mItemClickListener 만들어야 겠다.

        //밑 작업 한 뒤..
        //버튼 작업 해주기
        findViewById(R.id.btnadd).setOnClickListener(mClickListener);
        findViewById(R.id.btndel).setOnClickListener(mClickListener);

    }//onCreate


   AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
       @Override
       public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
           String message;
           message = "Select Item = " + items.get(position); //items.get(position)이 뭘 클릭했는지 알고있음
                                                            //이를통해 뭔가 눌렀을때 화면이 넘어가든지 함

           Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
           //onCreate 바깥일때는 꼭 MainActivity.this 로 해줘야함!!
           //onCreate 안일때는 this만 해도 됨
           //context target임 ....-> ?
       }
   };

   View.OnClickListener mClickListener = new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           //edittext 연결
           EditText editText = findViewById(R.id.newItem);
           //add일경우 연결
           switch(v.getId()){
               case R.id.btnadd:
                   String text = editText.getText().toString();
                   if(text.length() != 0){
                       items.add(text);//arraylist에 넣어줘
                       editText.setText("");
                       adapter.notifyDataSetChanged();//arraylist를 다시 재구성하여 읽어줘 라는 명령어임
                   }
                   break;
               case R.id.btndel:
                   int id;
                   id = list.getCheckedItemPosition(); //뭐를 눌렀냐
                   if(id != ListView.INVALID_POSITION){
                       //딴데 누르고 삭제버튼 누른거 아니라면
                       items.remove(id);//선택한거 어레이리스트에서 지워줘
                       //지웠는데 선택한걸 기억하고 있는 문제가 있음
                       list.clearChoices(); // 선택한걸 지워주는 메소드
                       adapter.notifyDataSetChanged();
                   }
                   break;
           }//switch

       }
   };


}//Main