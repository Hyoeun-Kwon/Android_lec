package com.aoslec.haezzo;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.aoslec.haezzo.Adapter.MyHaezzoListIngAdapter;
import com.aoslec.haezzo.Bean.MyHaezzoListBean;
import com.aoslec.haezzo.DocumentActivity.DocumentDetailsActivity;
import com.aoslec.haezzo.LoginActivity.KakaoLoginActivity;
import com.aoslec.haezzo.NetworkTask.MyHaezzoListNetworkTask;

import java.util.ArrayList;



public class FragmentTabIng extends Fragment {

    private View view;

    String macIP = ShareVar.macIP;
    String urlAddr = "http://"+macIP+":8080/test/Haezzo/myhaezzoIngSelectList.jsp?";

    ArrayList<MyHaezzoListBean> myHaezzoListBeans;
    MyHaezzoListIngAdapter myHaezzoListIngAdapter;

    RecyclerView recyclerView = null;
    RecyclerView.LayoutManager layoutManager = null;

    Intent intent = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }//onCreate

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.v("Message", "FragmentTabIng Start");
        // Inflate the layout for this fragment
        // 첫번째 프레그먼트 연결작업
        view =  inflater.inflate(R.layout.fragment_ing_tab, container, false);
        recyclerView = view.findViewById(R.id.myhaezzo_rvLists);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        //method
        connectGetData();

        return view;
    }

    private void connectGetData(){
        Log.v("Message", "METHOD : fragment1_connectGetData Start");

        try{
            Log.v("Message", " fragment1 - Before start NetworkTask");

            MyHaezzoListNetworkTask networkTask = new MyHaezzoListNetworkTask(getActivity(), urlAddr, "select");
            Object obj = networkTask.execute().get();
            myHaezzoListBeans = (ArrayList<MyHaezzoListBean>) obj;
            Log.v("Message", " fragment - myhaezzo(arraylist) : " + myHaezzoListBeans);

            myHaezzoListIngAdapter = new MyHaezzoListIngAdapter(getActivity(), R.layout.myhaezzolist_custom_layout, myHaezzoListBeans);
            //클릭시 연결
            myHaezzoListIngAdapter.setOnItemClickListener(adapterClick);
            Log.v("Message", " fragment - adapter is... : " + myHaezzoListIngAdapter);
            recyclerView.setAdapter(myHaezzoListIngAdapter);

        }catch(Exception e){
            e.printStackTrace();
        }

    }//connectGetData


    // 어댑터 클릭시 값 넘기면서 다음 화면 넘어가기
    MyHaezzoListIngAdapter.OnItemClickListener adapterClick = new MyHaezzoListIngAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            intent = new Intent(getActivity(), DocumentDetailsActivity.class);
            String dnumber = myHaezzoListBeans.get(position).getDnumber();
            String dstatus = myHaezzoListBeans.get(position).getDstatus();
            ShareVar.Document_dstatus = dstatus;
            intent.putExtra("dnumber",dnumber);
            Log.v("Message","dnumber = " + dnumber);
            startActivity(intent);
        }
    };


}