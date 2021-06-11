package com.aoslec.henim_diary;

public class UserAccount {
    //2개를 저장해 둘거야 ! (일단)
    private String emailId; //이메일 아이디
    private String password; //비밀번호
    //추가
    private String idToken; //firebase Uid(고유 토큰 정보): 키값

    //firebase : 빈 생성자 만들어야 함
    public UserAccount() {
    }

    //getter&setter
    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }
}//
