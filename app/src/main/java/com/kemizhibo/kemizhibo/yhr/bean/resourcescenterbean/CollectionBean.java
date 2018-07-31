package com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean;

/**
 * Author: yhr
 * Date: on 2018/7/16.
 * Describe:收藏的bean类
 */

public class CollectionBean {

    /**
     * code : 0
     * message : 收藏成功
     * content : null
     * otherData : null
     */

    private int code;
    private String message;
    private String content;
    private String otherData;

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

    public String getOtherData() {
        return otherData;
    }

    public void setOtherData(String otherData) {
        this.otherData = otherData;
    }


    @Override
    public String toString() {
        return "CollectionBean{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", content=" + content +
                ", otherData=" + otherData +
                '}';
    }
}
