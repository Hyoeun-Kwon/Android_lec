package com.aoslec.haezzo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.aoslec.haezzo.R;


public class FragmentTab2 extends Fragment {

    public FragmentTab2(){
        Log.v("Message", "Tab2Fragment");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.v("Message", "onCreateView");
        return inflater.inflate(R.layout.fragment_tab2, container, false);
    }
}