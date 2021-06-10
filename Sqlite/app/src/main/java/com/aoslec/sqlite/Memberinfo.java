package com.aoslec.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Memberinfo extends SQLiteOpenHelper {

    public Memberinfo(Context context){
        //얘는 본인이 화면이 없으니, main꺼 불러올거임 : context
        super(context,"MemberInfo.db",null,1);
        //super에 name 은 DB 이름임

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //제일 먼저 할일 : 테이블 만들기
        //앱을 제일 처음 쓸때 한번만 만듬
        String query = "CREATE TABLE member(id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, userid TEXT, passwd INTEGER);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //upgrade 이므로 전에있던 테이블 버리고 다시 생성
        String query = "DROP TABLE IF EXISTS member";
        db.execSQL(query);
        onCreate(db);
    }
}
