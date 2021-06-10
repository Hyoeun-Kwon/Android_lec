package com.aoslec.fragmenttest;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class TextFragment extends Fragment {

    TextView textView = null;

    //얘는 받는 애라 onCreateView만 하면 됨



    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        Log.v("Message","TextFragment onCreateView");
       //메인에 자기 그림을 올리기
        View view = inflater.inflate(R.layout.fragment_text, container,false);
       //TextView연결
        textView = view.findViewById(R.id.f2_text);
        return view;
    }

    //Main이 준 값을 찍어야함?!
    public  void changeTextProperties(int fontSize,String str){
        Log.v("Message","TextFragment changeTextProperties");
        textView.setTextSize(fontSize);//seekbar값
        textView.setText(str);
    }


}//