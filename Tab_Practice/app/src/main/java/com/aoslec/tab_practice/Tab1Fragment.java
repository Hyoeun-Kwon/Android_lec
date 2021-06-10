package com.aoslec.tab_practice;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Tab1Fragment extends Fragment {
    //빈생성자
    public Tab1Fragment(){

    }

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_tab1_fragment, container, false);
        //inflater ? : 화면과 연결
        //layout 어떤거고, container로 위치 잡고!
    }
}//