package com.kemizhibo.kemizhibo.yhr.bean.forteachbean;

/**
 * Author: 闫浩然
 * Date: on 2018/9/6.
 * Describe:一键授课的视屏播放地址
 */

public class ForTeachPlayUrlBean {

    /**
     * code : 0
     * message : success
     * content : http://kemivideotest.kemizhibo.com/aldRrTxWOupQtcXqhxRqtXNSHUKBIQEK_hd.m3u8?Expires=1536231591&OSSAccessKeyId=LTAI29ciatcNXACA&Signature=G6vOEr1aDLDOYkc7BEiVlFff5aM%3D&MtsHlsUriToken=200997e6-6ff2-4f68-98f4-304c5c6efa04
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
