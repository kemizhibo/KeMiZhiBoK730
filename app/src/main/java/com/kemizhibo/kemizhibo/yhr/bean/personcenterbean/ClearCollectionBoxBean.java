package com.kemizhibo.kemizhibo.yhr.bean.personcenterbean;

/**
 * Author: 闫浩然
 * Date: on 2018/8/14.
 * Describe: 清空收藏夹
 */

public class ClearCollectionBoxBean {

    /**
     * code : 0
     * message : 取消收藏成功
     * content : null
     * otherData : null
     */

    private int code;
    private String message;
    private Object content;
    private Object otherData;

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

    public Object getOtherData() {
        return otherData;
    }

    public void setOtherData(Object otherData) {
        this.otherData = otherData;
    }
}
