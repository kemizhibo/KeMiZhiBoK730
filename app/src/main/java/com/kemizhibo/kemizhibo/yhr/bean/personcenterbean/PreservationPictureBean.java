package com.kemizhibo.kemizhibo.yhr.bean.personcenterbean;

/**
 * Author: 闫浩然
 * Date: on 2018/7/27.
 * Describe:保存用户头像
 */

public class PreservationPictureBean {

    /**
     * flag : true
     * code : 0
     * message : 成功
     * content : null
     * page : null
     */

    private boolean flag;
    private int code;
    private String message;
    private Object content;
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
