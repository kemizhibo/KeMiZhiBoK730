package com.kemizhibo.kemizhibo.yhr.bean;

/**
 * Created by 17600 on 2018/5/18.
 */

public class ResetPwdBean {

    /**
     * flag : true
     * code : 0
     * message : 成功
     * content : d4b36ad56ea14da1a44bda7177b6cc49
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
