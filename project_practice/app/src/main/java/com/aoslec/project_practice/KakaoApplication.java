package com.aoslec.project_practice;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;

public class KakaoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        KakaoSdk.init(this,"5ff4b4e18cfb50396955ce5c3549997a");
    }
}
