package com.aoslec.customadapterview;

public class Weather { //Bean
    private String day;
    //안드는 다 정수다!
    private int icon;
    private String comment;

    //getter & setter
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    //생성자
    public Weather(String day, int icon, String comment) {
        this.day = day;
        this.icon = icon;
        this.comment = comment;

    }



}
