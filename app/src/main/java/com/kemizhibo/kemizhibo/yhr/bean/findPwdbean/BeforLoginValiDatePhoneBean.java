package com.kemizhibo.kemizhibo.yhr.bean.findPwdbean;

/**
 * Author: 闫浩然
 * Date: on 2018/8/28.
 * Describe: //登录前找回密码验证手机号
 */

public class BeforLoginValiDatePhoneBean {

    /**
     * code : 0
     * message :
     * content : 215b58b8aecd40e8ae157a2492442ba1
     * page : null
     */

    private int code;
    private String message;
    private String content;
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
