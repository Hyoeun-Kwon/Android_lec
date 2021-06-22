package com.aoslec.haezzo.Bean;

public class Students {
    private  String scode;
    private  String sname;
    private  String sdept;
    private  String sphone;

    public Students(String scode, String sname, String sdept, String sphone) {
        this.scode = scode;
        this.sname = sname;
        this.sdept = sdept;
        this.sphone = sphone;
    }

    public String getScode() {
        return scode;
    }

    public void setScode(String scode) {
        this.scode = scode;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSdept() {
        return sdept;
    }

    public void setSdept(String sdept) {
        this.sdept = sdept;
    }

    public String getSphone() {
        return sphone;
    }

    public void setSphone(String sphone) {
        this.sphone = sphone;
    }
}
