package com.aoslec.customadapterpractice;

public class BTS {
    private String name;
    private int icon;
    private String age;


    //---------

    public BTS(String name, int icon, String age) {
        this.name = name;
        this.icon = icon;
        this.age = age;
    }

    //-----------


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}//
