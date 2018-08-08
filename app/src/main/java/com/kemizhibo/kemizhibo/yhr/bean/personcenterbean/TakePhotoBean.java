package com.kemizhibo.kemizhibo.yhr.bean.personcenterbean;

/**
 * Author: 闫浩然
 * Date: on 2018/8/3.
 * Describe: 上传头像
 */

public class TakePhotoBean {

    /**
     * code : 500
     * message : inner server fail : com.sun.proxy.$Proxy196 cannot be cast to org.springframework.web.multipart.MultipartHttpServletRequest
     */

    private int code;
    private String message;

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
}
