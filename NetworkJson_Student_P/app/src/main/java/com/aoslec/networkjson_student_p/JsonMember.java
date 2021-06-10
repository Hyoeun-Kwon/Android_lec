package com.aoslec.networkjson_student_p;

public class JsonMember {
    //Bean 역할 하는 것

    private String code;
    private String name;
    private String dept;
    private String phone;

    //가져오는거만 Json 이 필요, 삽입 수정 삭제는 필요 없음!


    public JsonMember(String code, String name, String dept, String phone) {
        this.code = code;
        this.name = name;
        this.dept = dept;
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}//
