package com.aoslec.fragmenttest;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;


public class ToolBarFragment extends Fragment {
    //edit에 글씨를 쓰고, seekbar 조절하고 버튼을 누르면 글씨크기가 그에 맞게 크기가 변함!

    //다 지워주고 시작, 만들어둔 3개
    EditText editText = null;
    Button button = null;
    SeekBar seekBar = null;
    int seekValue = 10;//seekbar 시작시 0 아니고 10 으로

    //Main연결 후
    ToolbarListener activityCallback;


   //------------------------
    // MainActivity와의 통신을 위해 interface를 사용
    // MainActivity에서는 implements로 사용
    //------------------------
    //-> implements ToolBarFragment.ToolbarListener
    //ToolbarListener를 만들어줌

    //interface는 선언자 - > log 필요없음 (안됨)
    public  interface  ToolbarListener{
        //interface는 메소드 이름만 주는것이므로 이렇게 해둔
        public  void onButtonClick(int position, String text);//position은 seekbar 값

        //Main에서 이제 얘를 정의해줘야 버튼을 쓸수잇음
    }

    //-----------
    //Fragment에서는 onAttach()가 제일 처음으로 실행
    //-----------


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //toolbar 리스너에대해 정의해줘야 함
        try {
            //이 기능이 context야!
            activityCallback = (ToolbarListener) context; //activityCallback은 위의 ToolbarListener 선언자임
            Log.v("Message","ToolBarFragment onAttach");
            //onAttach가 있으면 Main에서 무슨 작업을 하든 나를 통해서 해라! -main은 거쳐감

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //savedInstanceState의 경우 프래그먼트가 재생성되기 이전의 상태를 저장하고 있는 변수
        //container
        Log.v("Message","ToolBarFragment onCreateView");
        //fragment위에 inflater로 그림 올리겠다.
        View view = inflater.inflate(R.layout.fragment_toolbar, container, false);
        editText = view.findViewById(R.id.f1_edit);
        button = view.findViewById(R.id.f1_button);
        seekBar = view.findViewById(R.id.f1_seekbar);

        //1. onButtonClick-> interface(mClicklistener, mSeekBarChangedListener)
        //2. onButtonClick-> changeTextProperties
        //ToolbarFragment에서 결정된 값을 MainActivity가 전달받아 TextFragment로 전달한다
        button.setOnClickListener(mClickListener);
        seekBar.setOnSeekBarChangeListener(mSeekBarChangedListener);

        return view;
    }

    //seekBar부터
    SeekBar.OnSeekBarChangeListener mSeekBarChangedListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
         //변했다 seekValue는 int값, progress가 알고있
         seekValue = progress;
            Log.v("Message","ToolBarFragment mSeekBarChangedListener");
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        //지금은 안줌
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    //버튼
    //------
    //실질적 MainActivity의 onButtonClick()에서 실행
    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //버튼에 대해 잘 생각
            //얘가 있지만 메인액티비티가 누를거임
            //얘는 interface에 신호를 주면 됨

                //onButtonClick은 메인에 있는 것
                    //얘가 메인에 값을 주는것
                activityCallback.onButtonClick(seekValue, editText.getText().toString());
                 Log.v("Message","ToolBarFragment mClickListener");

        }
    };



}//