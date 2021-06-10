package com.aoslec.dbcrud.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.aoslec.dbcrud.Adapter.StudentAdapter;
import com.aoslec.dbcrud.Bean.Student;
import com.aoslec.dbcrud.NetworkTask.NetworkTask;
import com.aoslec.dbcrud.R;

import java.util.ArrayList;

public class SelectAllActivity extends AppCompatActivity {

    String urlAddr = null;//만들어줄 어드레스
    ArrayList<Student> members;
    StudentAdapter adapter;
    ListView listView;
    String macIP; //가져온 어드레스


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_all);

        listView = findViewById(R.id.lv_student);

        Intent intent = getIntent();
        macIP = intent.getStringExtra("macIP");
        urlAddr = "http://"+ macIP + ":8080/test/student_query_all.jsp?";

        //리스트 뷰에서 학번 1 -> 수정 -- finish 없어지고 다시 리스트뷰 화면으로 넘어와서 그 화면에서 바뀐 장면 보여줘야 함 
        //아래에 있는 뷰에 정보를 줘야함
        //화면이 뜰때: resume , 다음화면에 있다가 다시 와도 resume 부터 한다.
        //data 갱신에는 resume에 있음 !!!


    }//

    @Override//*********중요!!!
    protected void onResume() {
        super.onResume();
        //수정된 정보를 부르기 위해
        //맨처음에도 실행, 위에 뭐가 얹혀졌다가 사라져도 실행 되기 위하여
        connectGetData();
    }

    private void connectGetData(){
        //검색하는 화면
        try {
            NetworkTask networkTask = new NetworkTask(SelectAllActivity.this, urlAddr,"select");
            //networktask 가 구동이 되서
            Object obj = networkTask.execute().get();//프로그레스바 돌고, 데이터 가지러가고
            members = (ArrayList<Student>) obj;

            adapter = new StudentAdapter(SelectAllActivity.this, R.layout.student_layout, members);
            listView.setAdapter(adapter);

            //클릭할거냐 롱클릭할거냐는 listView에 속성이 들어가야 함
            listView.setOnItemClickListener(onItemClickListener);
            listView.setOnItemLongClickListener(onItemLongClickListener);

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    //짧게 눌렀을 때
    AdapterView.OnItemClickListener onItemClickListener =new AdapterView.OnItemClickListener() {
        Intent intent = null;
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
           //click 했으니 intent를 타고서 클릭리스너 수행!?

            intent = new Intent(SelectAllActivity.this, UpdateActivity.class);
            intent.putExtra("code", members.get(position).getCode());
            intent.putExtra("name", members.get(position).getName());
            intent.putExtra("dept", members.get(position).getDept());
            intent.putExtra("phone", members.get(position).getPhone());
            intent.putExtra("macIP", macIP);
            startActivity(intent);

        }
    };
    //길게 눌렀을 때
    AdapterView.OnItemLongClickListener onItemLongClickListener = new AdapterView.OnItemLongClickListener() {
        Intent intent = null;
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

            intent = new Intent(SelectAllActivity.this, DeleteActivity.class);
            intent.putExtra("code", members.get(position).getCode());
            intent.putExtra("name", members.get(position).getName());
            intent.putExtra("dept", members.get(position).getDept());
            intent.putExtra("phone", members.get(position).getPhone());
            intent.putExtra("macIP", macIP);
            startActivity(intent);
            return true;
        }
    };

}//