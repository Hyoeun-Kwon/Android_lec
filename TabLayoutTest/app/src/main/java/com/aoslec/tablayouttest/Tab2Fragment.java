package com.aoslec.tablayouttest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Tab2Fragment extends Fragment {

    public Tab2Fragment(){
        //빈생성자
    }


    //fragment 제일 먼저는 onCreateView 다
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_tab2,container,false);
        //tab2를 띄울거야

    }
}