package com.aoslec.tab_practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Tab2Fragment extends Fragment {

    public Tab2Fragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_tab2_fragment, container, false);
        //inflater ? : 화면과 연결
        //layout 어떤거고, container로 위치 잡고!
    }
}