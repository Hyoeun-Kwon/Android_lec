package com.aoslec.networkjson_student_re_webview;

public class JsonBean {

    private String name;
    private String image;
    private String info;

    public JsonBean(String name, String img, String info) {
        this.name = name;
        this.image = img;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return image;
    }

    public void setImg(String img) {
        this.image = img;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}//Main
