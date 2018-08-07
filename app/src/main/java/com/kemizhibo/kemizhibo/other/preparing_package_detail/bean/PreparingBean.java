package com.kemizhibo.kemizhibo.other.preparing_package_detail.bean;

/**
 * Created by asus on 2018/8/1.
 */

public class PreparingBean {
    private int code;
    private String message;
    private  String content;

    public PreparingBean(int code, String message, String content) {
        this.code = code;
        this.message = message;
        this.content = content;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
