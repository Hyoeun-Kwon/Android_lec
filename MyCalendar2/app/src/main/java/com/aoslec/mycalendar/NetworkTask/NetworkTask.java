package com.aoslec.mycalendar.NetworkTask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.aoslec.mycalendar.Bean.Diary;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class NetworkTask extends AsyncTask<Integer, String, Object> {

    Context context = null;
    // WebServer 위치 확인용
    String mAddr = null;

    ProgressDialog progressDialog = null;
    ArrayList<Diary> diarys;

    // CRUD 구분
    String where = null;

    // Constructor
    public NetworkTask(Context context, String mAddr, String where){
        this.context = context;
        this.mAddr = mAddr;
        this.where = where;
        this.diarys = new ArrayList<Diary>();
    }

    // 로딩 뺑뺑이.
    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context); // "해당 장소에서 띄운다"
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Dialog");
        progressDialog.show();
    }

    // DATA 다 가져왔으면?
    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        progressDialog.dismiss();
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        progressDialog.dismiss();
    }

    // * * * * * Progress END * * * * * //

    @Override
    protected Object doInBackground(Integer... integers) {
        Log.v("Message", "NetworkTask . . . doInbackground");

        // CRUD 공통 사용 정의
        StringBuffer stringBuffer = new StringBuffer();
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;

        // 처리 잘 했나 안했나 여부 확인용.
        String result = null;

        try {
            // URL 정의
            URL url = new URL(mAddr);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(10000);

            // update, delete, insert 는


            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);
                bufferedReader = new BufferedReader(inputStreamReader);

                while (true) {
                    String strline = bufferedReader.readLine(); // 한줄씩 읽자!
                    if (strline == null) break; // 읽을거 없으면 while 끝
                    stringBuffer.append(strline + "\n");
                }//while

                // String where 는 무슨 역할? select는 JSON이 달라서 구분
                if (where.equals("select")) {
                    //return 값 없음.
                    Log.v("Message", "where_select");
//                    parserSelect(stringBuffer.toString());
                } else {
                    // return 값 있음.
                    result = parserAction(stringBuffer.toString());
                }

            } // if(http
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 닫아주기
                if (bufferedReader != null) bufferedReader.close();
                if (inputStreamReader != null) inputStreamReader.close();
                if (inputStream != null) inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (where.equals("select")) {
                return diarys;
            } else {
                return result;
            }
        }
    } // doInBack

    // {"result" : "OK"} 받아오기 위해 만드는 METHOD
    private String parserAction(String str){
        String returnValue = null;
        try{
            JSONObject jsonObject = new JSONObject(str);
            jsonObject.getString("result");
            // 입력이 잘 됐다고 하면 {"result" : "1"} 이 뜰 것이고, 오류면 {"result" : "0"}이 넘어옴.
            returnValue = jsonObject.getString("null");

        }catch (Exception e){
            e.printStackTrace();
        }
        return returnValue;
    }



}// Main
