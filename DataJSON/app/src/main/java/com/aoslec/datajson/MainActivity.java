package com.aoslec.datajson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {


    final static  String TAG ="Status";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //parser라는 메소드를 만들어서 실행시킬거임
        parser();

    }//onCreate

    private void parser(){
        //raw에 있는 jsonex.json 어떻게 가져오지?
        //jsonex 내용이 정신없는데 저기서 어떻게 id만 가져오지?
        //일단 log 찍자
        Log.v(TAG,"parser()");
        //파일에서 가져오는것이므로 필요 서버에서 가져오는거면 필요없다.
        InputStream inputStream = getResources()
                .openRawResource(R.raw.jsonex);
        //inputstream 을 reader로 넣기
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        StringBuffer stringBuffer = new StringBuffer();
        String line = null;
        //----------inputstream 부터 여기까지 쓰는건 안바뀐다.

        try{
            while ((line = bufferedReader.readLine())!=null){
                stringBuffer.append(line);//1줄씩 들어감 (원래는 다닥 붙어있음)
            }//try
            Log.v(TAG,"StringBuffer : " + stringBuffer.toString());

            //여기서부터 json
            //{ 오브젝트, [어레이
            //중괄호, 대활호가 먼지 파악
            JSONObject jsonObject = new JSONObject(stringBuffer.toString());
            //배열 찾자 (member_info 가지고)
            JSONArray jsonArray = new JSONArray(jsonObject.getString("member_info"));
            //
            for(int i=0; i<jsonArray.length();i++){ //json/cathy
                JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                //name으로 찾아옴
                String name = jsonObject1.getString("name");
                Log.v(TAG,"name: "+ name);
                int age = jsonObject1.getInt("age");
                Log.v(TAG,"age: "+ age);

                //hobbies는 어레이네?
                JSONArray jsonArray1 = jsonObject1.getJSONArray("hobbies");
                for(int j=0; j<jsonArray1.length(); j++){
                    String hobby = jsonArray1.getString(j);
                    Log.v(TAG,"hobby: " + hobby);
                }

                //또 오브젝트네{
                JSONObject jsonObject2 = jsonObject1.getJSONObject("info");
                int no = jsonObject2.getInt("no");
                Log.v(TAG,"no: " + no);
                String id = jsonObject2.getString("id");
                Log.v(TAG,"id: " + id);
                String pw = jsonObject2.getString("pw");
                Log.v(TAG,"pw: " + pw);
            }//jsonArray

        }catch (Exception e){
            e.printStackTrace();
            //---------------json 읽어오는 준비단계
        }finally {
            try {
              if(inputStream != null) inputStream.close();
              if(inputStreamReader != null) inputStreamReader.close();
              if(bufferedReader != null) bufferedReader.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }//finally

    }//parser


}//Main