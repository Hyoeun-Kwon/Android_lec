package com.aoslec.tablayouttest;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Tab1Fragment extends Fragment {
    //얘가 쉬우니까 탭바부터 정의하자
    public Tab1Fragment(){
        //빈생성자
    }


    //fragment 제일 먼저는 onCreateView 다
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       //fragment1 로 오면 onCreateView부터 들어오고
        return inflater.inflate(R.layout.fragment_tab1,container,false);

    }
}//