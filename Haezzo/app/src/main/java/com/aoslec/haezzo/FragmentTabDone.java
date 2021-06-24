package com.aoslec.haezzo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aoslec.haezzo.Adapter.MyHaezzoListDoneAdapter;
import com.aoslec.haezzo.Adapter.MyHaezzoListIngAdapter;
import com.aoslec.haezzo.Bean.MyHaezzoListBean;
import com.aoslec.haezzo.DocumentActivity.DocumentDetailsActivity;
import com.aoslec.haezzo.LoginActivity.KakaoLoginActivity;
import com.aoslec.haezzo.NetworkTask.MyHaezzoListNetworkTask;

import java.util.ArrayList;


public class FragmentTabDone extends Fragment {


    private View view;

    String macIP = ShareVar.macIP;
    String urlAddr = "http://"+macIP+":8080/test/Haezzo/myhaezzoDoneSelectList.jsp?";

    ArrayList<MyHaezzoListBean> myHaezzoListBeans;
    MyHaezzoListDoneAdapter myHaezzoListDoneAdapter;

    RecyclerView recyclerView = null;
    RecyclerView.LayoutManager layoutManager = null;

    Intent intent = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.v("Message", "FragmentTabDone Start");
        // Inflate the layout for this fragment
        // 첫번째 프레그먼트 연결작업
        view =  inflater.inflate(R.layout.fragment_done_tab, container, false);
        recyclerView = view.findViewById(R.id.myhaezzoListDone_rvLists);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        //method
        connectGetData();

        return view;
    }

    private void connectGetData(){
        Log.v("Message", "METHOD : fragment2_connectGetData Start");

        try{
            Log.v("Message", " fragment2 - Before start NetworkTask");

            MyHaezzoListNetworkTask networkTask = new MyHaezzoListNetworkTask(getActivity(), urlAddr, "select");
            Object obj = networkTask.execute().get();
            myHaezzoListBeans = (ArrayList<MyHaezzoListBean>) obj;
            Log.v("Message", " fragment2 - myhaezzo(arraylist) : " + myHaezzoListBeans);

            myHaezzoListDoneAdapter = new MyHaezzoListDoneAdapter(getActivity(), R.layout.myhaezzolist_custom_done_layout, myHaezzoListBeans);
            //클릭시 연결
            myHaezzoListDoneAdapter.setOnItemClickListener(adapterClick);
            Log.v("Message", " fragment2 - adapter is... : " + myHaezzoListDoneAdapter);
            recyclerView.setAdapter(myHaezzoListDoneAdapter);

        }catch(Exception e){
            e.printStackTrace();
        }

    }//connectGetData

    // 어댑터 클릭시 값 넘기면서 다음 화면 넘어가기
    MyHaezzoListDoneAdapter.OnItemClickListener adapterClick = new MyHaezzoListDoneAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            intent = new Intent(getActivity(), DocumentDetailsActivity.class);
            String dnumber = myHaezzoListBeans.get(position).getDnumber();
            intent.putExtra("dnumber",dnumber);
            String dstatus = myHaezzoListBeans.get(position).getDstatus();
            ShareVar.Document_dstatus = dstatus;
            Log.v("Message","dnumber = " + dnumber);
            startActivity(intent);
        }
    };



}//--------