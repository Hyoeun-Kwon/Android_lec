package com.aoslec.haezzo.Bean;

public class MyHaezzoListBean {
    private String dnumber;
    private String dtitle;
    private String dcontent;
    private String ddate;
    private String dmoney;
    private String dproduct;
    private String dplace;
    private String dstatus;
    private String dimage;
    private String unumber;
    private String uage;
    private String unickname;

    public MyHaezzoListBean(String dnumber, String dtitle, String dcontent, String ddate, String dmoney, String dproduct, String dplace, String dstatus, String dimage, String unumber, String uage, String unickname) {
        this.dnumber = dnumber;
        this.dtitle = dtitle;
        this.dcontent = dcontent;
        this.ddate = ddate;
        this.dmoney = dmoney;
        this.dproduct = dproduct;
        this.dplace = dplace;
        this.dstatus = dstatus;
        this.dimage = dimage;
        this.unumber = unumber;
        this.uage = uage;
        this.unickname = unickname;
    }

    public MyHaezzoListBean(String dnumber, String ddate, String dmoney, String dproduct, String dplace, String dstatus, String dimage, String unickname) {
        this.dnumber = dnumber;
        this.ddate = ddate;
        this.dmoney = dmoney;
        this.dproduct = dproduct;
        this.dplace = dplace;
        this.dstatus = dstatus;
        this.dimage = dimage;
        this.unickname = unickname;
    }

    public String getDnumber() {
        return dnumber;
    }

    public void setDnumber(String dnumber) {
        this.dnumber = dnumber;
    }

    public String getDtitle() {
        return dtitle;
    }

    public void setDtitle(String dtitle) {
        this.dtitle = dtitle;
    }

    public String getDcontent() {
        return dcontent;
    }

    public void setDcontent(String dcontent) {
        this.dcontent = dcontent;
    }

    public String getDdate() {
        return ddate;
    }

    public void setDdate(String ddate) {
        this.ddate = ddate;
    }

    public String getDmoney() {
        return dmoney;
    }

    public void setDmoney(String dmoney) {
        this.dmoney = dmoney;
    }

    public String getDproduct() {
        return dproduct;
    }

    public void setDproduct(String dproduct) {
        this.dproduct = dproduct;
    }

    public String getDplace() {
        return dplace;
    }

    public void setDplace(String dplace) {
        this.dplace = dplace;
    }

    public String getDstatus() {
        return dstatus;
    }

    public void setDstatus(String dstatus) {
        this.dstatus = dstatus;
    }

    public String getDimage() {
        return dimage;
    }

    public void setDimage(String dimage) {
        this.dimage = dimage;
    }

    public String getUnumber() {
        return unumber;
    }

    public void setUnumber(String unumber) {
        this.unumber = unumber;
    }

    public String getUage() {
        return uage;
    }

    public void setUage(String uage) {
        this.uage = uage;
    }

    public String getUnickname() {
        return unickname;
    }

    public void setUnickname(String unickname) {
        this.unickname = unickname;
    }
}//---
