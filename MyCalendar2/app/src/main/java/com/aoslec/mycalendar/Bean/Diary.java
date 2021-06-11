package com.aoslec.mycalendar.Bean;

public class Diary {

    // Field
    private String date;
    private String title;
    private String detail;
    private String status;

    // Constructor
    public Diary(String date, String title, String detail, String status) {
        this.date = date;
        this.title = title;
        this.detail = detail;
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
