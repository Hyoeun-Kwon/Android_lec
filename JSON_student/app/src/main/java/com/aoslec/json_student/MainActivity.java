package com.aoslec.json_student;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    final static  String TAG ="Status";//log에 message 라고 띄우기 싫어서 !


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parser();
    }//onCreate

    private void parser() {
        //raw에 있는 jsonex.json 어떻게 가져오지?
        //jsonex 내용이 정신없는데 저기서 어떻게 id만 가져오지?
        //일단 log 찍자
        Log.v(TAG, "parser()");
        //파일에서 가져오는것이므로 필요 서버에서 가져오는거면 필요없다.
        InputStream inputStream = getResources().openRawResource(R.raw.jsonex);
        //inputstream 을 reader로 넣기
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        StringBuffer stringBuffer = new StringBuffer();
        String line = null;
        //----------inputstream 부터 여기까지 쓰는건 안바뀐다.

        try {
            while ((line = bufferedReader.readLine())!=null){
                stringBuffer.append(line);//1줄씩 들어감 (원래는 다닥 붙어있음)
            }//try
            Log.v(TAG,"StringBuffer : " + stringBuffer.toString());
            //JSON 시작
            //{부터 이므로 object
            JSONObject jsonObject = new JSONObject(stringBuffer.toString());
            //배열 찾자 (member_info 가지고)
            JSONArray jsonArray = new JSONArray(jsonObject.getString("students_info"));
            //
            for(int i=0; i<jsonArray.length();i++) { //json/cathy
                JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                //name으로 찾아옴
                String code = jsonObject1.getString("code");
                Log.v(TAG,"code: "+ code);
                String name = jsonObject1.getString("name");
                Log.v(TAG,"name: "+ name);
                String dept = jsonObject1.getString("dept");
                Log.v(TAG,"dept: "+ dept);
                String phone = jsonObject1.getString("phone");
                Log.v(TAG,"phone: "+ phone);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(inputStream != null) inputStream.close();
                if(inputStreamReader != null) inputStreamReader.close();
                if(bufferedReader != null) bufferedReader.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }//finally

    }//parser
}//Main