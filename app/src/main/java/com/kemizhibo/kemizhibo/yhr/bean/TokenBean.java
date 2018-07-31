package com.kemizhibo.kemizhibo.yhr.bean;

/**
 * Author: yhr
 * Date: on 2018/7/13.
 * Describe:
 */

public class TokenBean {

    /**
     * flag : true
     * code : 0
     * message : 成功
     * content : eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqdGkiLCJpYXQiOjE1MzE0NDk0OTMsInN1YiI6IjI3NzgyIiwiaXNzIjoia216YiIsImV4cCI6MTUzMTQ1MTI5M30.-i92pgxjzjnw_vUM8-fDNSNd-_uJFPMCu7ZuL-a1ksU
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
