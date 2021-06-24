package com.aoslec.haezzo;

import com.aoslec.haezzo.HelperApplyActivity.HelperApplyAccountActivity;
import com.aoslec.haezzo.HelperApplyActivity.HelperApplyIdCardActivity;
import com.aoslec.haezzo.HelperApplyActivity.HelperApplyProfileActivity;
import com.aoslec.haezzo.HelperApplyActivity.HelperApplyProfileImageActivity;

public class ShareVar {

    public static String macIP = "192.168.36.130";
    public static String urlAddr = "http://" + macIP + ":8080/test/Haezzo/";

    // 헬퍼 최종 지원 전 임시 값 저장
    public static String hAccountImage = HelperApplyAccountActivity.hAccountImage;
    public static String hName = HelperApplyAccountActivity.hName;
    public static String hBank = HelperApplyAccountActivity.hBank;
    public static String hAccount = HelperApplyAccountActivity.hAccount;
    public static String hIdCardImage = HelperApplyIdCardActivity.hIdCardImage;
    public static String hProfileImage = HelperApplyProfileImageActivity.hProfileImage;
    public static String hSelf = HelperApplyProfileActivity.hSelf;
    public static String hGaGa = HelperApplyProfileActivity.hGaGa;
    public static String hRating = null;

    //진행중/ 진행완료 구분 값(따라 상세보기의 버튼이 달라짐)
    public static String Document_dstatus = null;



}
