package com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean;

/**
 * Author: yhr
 * Date: on 2018/7/16.
 * Describe:收藏的bean类
 */

public class CollectionBean {


    /**
     * code : 0
     * message : 添加收藏成功
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
