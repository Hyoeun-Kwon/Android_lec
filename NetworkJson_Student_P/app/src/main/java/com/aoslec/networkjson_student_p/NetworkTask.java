package com.aoslec.networkjson_student_p;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class NetworkTask extends AsyncTask<Integer, String, Object> {
    //object -> back data 가져오는거
    //integer/string : 뱅뱅 돌고 있음 :progress dialog

    //뱅뱅이 도는것과, 서버에서 데이터 가져오는게 동시에 이뤄지는 거임!
    //Async 는 동시에
    final static  String TAG ="Status";

    Context context = null;
    String mAddr = null; //address 값 (ip어드레스 값의 몇번애 뭘 가져와라)
    ProgressDialog progressDialog = null;
    ArrayList<JsonMember> members; // JsonMember는 Bean // 얘는 리턴 값있고, 파서는 없다.

    //생성자
    //Mainacrivity 에서 new 해서 쓸건데 이때 쓸거임
    public NetworkTask(Context context, String mAddr){
        //NETSORKTAST는 액티비티가 아님 (그림까지 같이잇어야 액티비티다)
        //mainactivity가 준걸 내꺼로 만들기.
        this.context = context;
        this.mAddr = mAddr; // --> http://~~~~. json
        this.members = new ArrayList<JsonMember>();
    }



    @Override
    protected void onPreExecute() {
        //뱅뱅이 도는 메시지
        //어디서 돌거냐?
        //화면에 뜬건 메인 액티비티, 얘는 안보이는거임 (액티비티가 아니라 돌릴 수 없음)
        progressDialog = new ProgressDialog(context);//main에서 받아온 context를 넣어줌
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Dialog");
        progressDialog.setMessage("downloading...");
        progressDialog.show();
        Log.v(TAG, "onPreExecute");
    }



    // data 가져오는 거 끝났으면 프로그레스 다이얼로그에 알려줘야지
    @Override //String
    //진행중일때 숫자표시하겠다 이럴때 쓰는거임
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override //끝났다는 신호
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        //끝났으면 다이얼로그 없애줘~
        progressDialog.dismiss();
    }

    //만약 에러등으로 캔슬 되었다면?
    @Override
    protected void onCancelled() {
        super.onCancelled();
    }









    @Override
    protected Object doInBackground(Integer... integers) {
        StringBuffer stringBuffer = new StringBuffer();
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        Log.v(TAG, "doInBackground()");
        try{
            //URL 타입 : 어디로 받겠다 까지 정해줌
            URL url = new URL(mAddr);//main에서 컨스트럭터를 통해 들어올거임
            //http타입 프로토콜이다. (옛날 언어라 적어줘야함)
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            //10초 내로 연결 안되면? 에러걸림 000빼고 보기
            httpURLConnection.setConnectTimeout(10000);

            //연결 잘 됐으면 신호 보내기
            //http
            if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                inputStream = httpURLConnection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);
                bufferedReader = new BufferedReader(inputStreamReader);

                while (true){//무조건 반복한다. --> break 생각해야함
                    String strline = bufferedReader.readLine();//한줄씩 읽자
                    //읽을게 없어? 그럼 멈춰.
                    if(strline == null) break;
                    stringBuffer.append(strline + "\n");

                }
                parser(stringBuffer.toString()); //파싱할 함수
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                //정리할때는 거꾸러 정리하는 거임
                //정리안하면 JSON 파일 2개 부른다하면 2번째때, 첫번째께 포함되어 있음
                if(bufferedReader != null) bufferedReader.close();
                if(inputStreamReader != null) inputStreamReader.close();
                if(inputStream != null) inputStream.close();

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  members;
    }

    //parser
    //str?: json 파일 : 대표이름 ? members_info
    private void parser(String str){
        Log.v(TAG, "parser()");

        try{
            //jsonobject에 받은걸 넣어주고
            JSONObject jsonObject = new JSONObject(str);
            //members info 임을 체크
            JSONArray jsonArray = new JSONArray(jsonObject.getString("students_info"));
            //혹시 하다가 다른 json 눌렀을때 같이 보일까봐.(파싱전에 아무데나! 사용)
            members.clear(); //arraylist 지울려고 사용

            //arraylist에 넣자
            for(int i=0; i<jsonArray.length(); i++) { //json/cathy
                JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                //name으로 찾아옴
                String code = jsonObject1.getString("code");
                Log.v(TAG, "code: " + code);
                String name = jsonObject1.getString("name");
                Log.v(TAG, "name: " + name);
                String dept = jsonObject1.getString("dept");
                Log.v(TAG, "dept: " + dept);
                String phone = jsonObject1.getString("phone");
                Log.v(TAG, "phone: " + phone);


                //가져온걸 bean에다가 정의
                JsonMember member = new JsonMember(code, name, dept, phone); //bean으로 받아서
                members.add(member);//어레이리스트에 넣어줌

                }//for

            } catch (Exception e) {
            e.printStackTrace();
        }

    }//parser





}//End...
