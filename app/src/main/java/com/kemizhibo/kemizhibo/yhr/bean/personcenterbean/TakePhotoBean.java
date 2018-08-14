package com.kemizhibo.kemizhibo.yhr.bean.personcenterbean;

/**
 * Author: 闫浩然
 * Date: on 2018/8/3.
 * Describe: 上传头像
 */

public class TakePhotoBean {


    /**
     * code : 0
     * message : success
     * content : /images/upload/picImg/20180813/13/1534132019151.jpg
     */

    private int code;
    private String message;
    private String content;

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
}
