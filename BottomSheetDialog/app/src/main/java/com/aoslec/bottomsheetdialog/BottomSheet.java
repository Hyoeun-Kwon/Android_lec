package com.aoslec.bottomsheetdialog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

//Bottom은 액티비티가 아님
public class BottomSheet extends BottomSheetDialogFragment {

  //fragement 시작점은 onCreateView다! (onCreate 아님)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {


        return inflater.inflate(R.layout.activity_bottom_sheet,container,false);
    }

    //click시 이걸 구성해 줄거임
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //아무데서나 부를수 있음
        //종료버튼 설정
        getView().findViewById(R.id.button_bottom_sheet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //클릭시 종료해라
                dismiss();
            }
        });


    }
}//BottomSheet