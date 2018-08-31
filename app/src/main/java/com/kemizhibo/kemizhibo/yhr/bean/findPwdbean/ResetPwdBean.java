package com.kemizhibo.kemizhibo.yhr.bean.findPwdbean;

/**
 * Author: 闫浩然
 * Date: on 2018/8/30.
 * Describe:重置密码
 */

public class ResetPwdBean {

    /**
     * code : 0
     * message : success
     * content : null
     * page : null
     */

    private int code;
    private String message;
    private Object content;
    private Object page;

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

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public Object getPage() {
        return page;
    }

    public void setPage(Object page) {
        this.page = page;
    }
}
