package com.aoslec.customadapterview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

//상속받아 쓰면 편하다 ! 상속시키기
//extends BaseAdapter 까지 써주면 전구가 나오고 연결시키면 된다.
//전구가 안뜨면 alt + enter
public class WeatherAdapter extends BaseAdapter {
    //Adapter를 처음부터 쓸수 없으므로 BaseAdapter를 상속받아 쓰는거임
    //--> 아 abstract 구나,

    //지금 data가 들어오는 박스 만드는 중 !
    //field 가 필요 (여기에 쓰는 필드값은 꼭 있어야 함 )
    //이 부분은 우리가 만드는 부분임(생성자) : 우리가 필요한 것!
    //생성자를 만들어야하니까 그로인해 필요한 필드값을 정해주는 거임 (거꾸로임)
    private Context mContext = null; // = Activity다 -> Main에서 연결했는지 Second에서 연결했는지 ...
    private int layout =0;//번호
    private ArrayList<Weather> data = null; //여기서 Weather는 Bean 임
    private LayoutInflater inflater = null; //view 로 되어있음

    //생성자 만들기
    public WeatherAdapter(Context mContext, int layout, ArrayList<Weather> data) {
        this.mContext = mContext;
        this.layout = layout;
        this.data = data;
        //여기에 inflater 넣어주기
        //xml 파일 통째로 들어가는것을 inflater라고 생각하면 됨
        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //inflater -> 허가! (1줄씩 넣으려면 꼭 필요!!! )
        //한줄의 값(3개와) , 여러 줄 을 연결 ! —-> inflater
        //모든게 쌓이는 거임
    }//생성자

    //기본적으로 이런 메소드가 있는데 내가 바꿔서 사용 가능하다!--> 상속 후 연결시키면 자동 생성 됨
    //getter/ setter 에 있는거 데려옴
    @Override
    public int getCount() {
//        return 0;
        //위 생성자의 size가 들어올거다 -> 이거만큼 데이타가 방 만들고 쌓아줄 거임
        //데이터갯수만큼 리스트 뷰가 만들어짐 (사실 리스트뷰는 보여주는게 없고 어댑터로 들어와야 보여줌)
        //data몇개야
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        //click 했을때 사용!
//        return null;
        //day ---> getter & setter에 있는 값임 //!?
        return data.get(position).getDay();
    }

    @Override
    //click 했을때 사용!
    public long getItemId(int position) {
        //click
//        return 0;
        //번호 알려주면 되니까
        return position;
    }

   //얘가 중요!! view --> view 집어넣는거!
    @Override
    //view 를 리턴해달래
    //getView 는
    public View getView(int position, View convertView, ViewGroup parent) {
        //position(한줄)이 0(몇번째냐 ) ->>> 쭉쭉

        //처음에는 데이터가 없겟지
        //convertview는 인플레이터(올리겠다)임 ,
        if(convertView == null){

            //없으면 inflater를 통해 데이터를 쌓아주자 (방 만듬 )
            //땅을 닦아줌
            convertView = inflater.inflate(this.layout, parent, false);
        }

        //연결 시켜주는 것임
        //View 이름이 convertview 란 ? 빈 하나하나? 가 컨버트 뷰다 ( 이 컨버트 뷰가 우리가 만든 커스텀레이아웃임)
        //custom_layout과 데이터를 연결해주기 위한 작업!
        TextView tv_day = convertView.findViewById(R.id.tv_day);
        ImageView iv_icon = convertView.findViewById(R.id.iv_weather);
        TextView tv_comment = convertView.findViewById(R.id.tv_comment);

        //data 넣기
        tv_day.setText(data.get(position).getDay()+ " ");//arraylist의 첫번째 줄에 월-금 넣어준 거임
        iv_icon.setImageResource(data.get(position).getIcon());
        tv_comment.setText(data.get(position).getComment());

        if(position % 2 ==1 ){
            convertView.setBackgroundColor(0x5000ff00); // 아 컨버트뷰가 한 셀(한줄)이구나!
            //backgroundColor -> 16진수, 0x는 #과 같은 의미
        }else {
            convertView.setBackgroundColor(0x2000ff00);
        }


        return convertView;//convertView!!! // View의 변수명이 convertView라서 convertView임
    }
}
