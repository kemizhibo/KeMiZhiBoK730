package com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean;

/**
 * Author: yhr
 * Date: on 2018/7/11.
 * Describe:教师培训视频的地址
 */

public class TeacherTrainingDetailsVideoUrlBean {

    /**
     * code : 0
     * message : success
     * content : http://kemivideotest.kemizhibo.com/OytzHrDXppqyGlUncItwMaWCrKSOrVwt_hd.m3u8?Expires=1531283959&OSSAccessKeyId=LTAI29ciatcNXACA&Signature=1bIb1irNfwqx5HfxpMAw714ronI%3D&MtsHlsUriToken=f3ce2ded-bd51-4615-9ccc-72822f49de25
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
