package com.aoslec.haezzo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.aoslec.haezzo.Adapter.StudentsAdapter;
import com.aoslec.haezzo.Bean.Students;
import com.aoslec.haezzo.NetworkTask.NetworkTask;
import com.aoslec.haezzo.R;

import java.util.ArrayList;


public class FragmentTab1 extends Fragment {

    Button btn_insert;
    EditText et_ip;

    String urlAddr = null;
    ArrayList<Students> members;
    StudentsAdapter adapter;
    ListView listView;
    String macIP;

    Context ct, cxt;

    public FragmentTab1(){
        Log.v("Message", "Tab1Fragment");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.v("Message", "onCreateView");

        View view = inflater.inflate(R.layout.fragment_tab1, container, false); // added
        ct = container.getContext(); // added

        btn_insert = view.findViewById(R.id.btnInsert); // container added
        et_ip = view.findViewById(R.id.et_ip_main); // container added
        btn_insert.setOnClickListener(onClickListener);

        listView = view.findViewById(R.id.lv_student_select); // container added

        Intent intent = getActivity().getIntent(); // getActivity added
        macIP = et_ip.getText().toString();
//        macIP = intent.getStringExtra("macIP");
        urlAddr = "http://"+macIP+":8080/test/student_query_all.jsp";
        Log.v("Message",urlAddr);


        return inflater.inflate(R.layout.fragment_tab1, container, false);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            Intent intent = new Intent(SelectAllActivity.this, InsertActivity.class);
//            intent.putExtra("macIP", macIP);
//            startActivity(intent);
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        //데이터를 갱신할 수 있는 LIFECYCLE
        connectGetData();


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        cxt = context;
    }

    private  void connectGetData(){
        try{
            Log.v("check", "check");
//            NetworkTask networkTask = new NetworkTask(, urlAddr, "select"); // ct added
//            Object obj = networkTask.execute().get();
//            members = (ArrayList<Students>) obj;
//
//            adapter = new StudentsAdapter(, R.layout.student_layout, members); // ct added
//            listView.setAdapter(adapter);
//            listView.setOnItemClickListener(onItemClickListener);
//            listView.setOnItemLongClickListener(onItemLongClickListener);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        Intent intent = null;
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            intent = new Intent(SelectAllActivity.this, UpdateActivity.class);
//            intent.putExtra("code", members.get(position).getScode());
//            intent.putExtra("name", members.get(position).getSname());
//            intent.putExtra("dept", members.get(position).getSdept());
//            intent.putExtra("phone", members.get(position).getSphone());
//            intent.putExtra("macIP", macIP);
//            startActivity(intent);
        }
    };

    AdapterView.OnItemLongClickListener onItemLongClickListener = new AdapterView.OnItemLongClickListener() {
        Intent intent = null;
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//            intent = new Intent(SelectAllActivity.this, DeleteActivity.class);
//            intent.putExtra("code", members.get(position).getScode());
//            intent.putExtra("name", members.get(position).getSname());
//            intent.putExtra("dept", members.get(position).getSdept());
//            intent.putExtra("phone", members.get(position).getSphone());
//            intent.putExtra("macIP", macIP);
//            startActivity(intent);
            return false;
        }
    };

}