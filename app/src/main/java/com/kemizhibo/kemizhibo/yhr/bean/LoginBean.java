package com.kemizhibo.kemizhibo.yhr.bean;

import java.io.Serializable;

/**
 * Created by 17600 on 2018/5/16.
 */

public class LoginBean implements Serializable {


    /**
     * flag : true
     * code : 0
     * message : 成功
     * content : eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqdGkiLCJpYXQiOjE1MzIxNDY4NDMsInN1YiI6IjI3NzgyIiwiaXNzIjoia216YiIsImV4cCI6MTUzMjE0ODY0M30.joAd4WNAKTgC1PPY-WbKKHsrG9pmfjpM48dy3twLQWA
     * page : null
     */

    private boolean flag;
    private int code;
    private String message;
    private String content;
    private Object page;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
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

    public Object getPage() {
        return page;
    }

    public void setPage(Object page) {
        this.page = page;
    }
}
