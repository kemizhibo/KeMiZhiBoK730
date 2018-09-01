package com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean;

/**
 * Author: 闫浩然
 * Date: on 2018/7/22.
 * Describe: 点赞评论
 */

public class GetLikeBean {

    /**
     * code : 0
     * message : success
     * content : null
     * otherData : null
     */

    private int code;
    private String message;
    private String content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Object getOtherData() {
        return otherData;
    }

    public void setOtherData(Object otherData) {
        this.otherData = otherData;
    }
}
