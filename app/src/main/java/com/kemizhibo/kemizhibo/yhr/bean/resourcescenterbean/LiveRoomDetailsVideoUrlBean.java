package com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean;

/**
 * Author: yhr
 * Date: on 2018/7/12.
 * Describe:科学观察室获取视频播放地址
 */

public class LiveRoomDetailsVideoUrlBean {

    /**
     * code : 0
     * message : success
     * content : http://kemivideotest.kemizhibo.com/ozlBDqOcgSCEaGmKQhUrBhmzVJVTWmnA_hd.m3u8?Expires=1531387289&OSSAccessKeyId=LTAI29ciatcNXACA&Signature=W2nEDbsMRdH1GStnpNBAVwlbmEI%3D&MtsHlsUriToken=52dbd8d4-8727-4e15-b115-bdccfbcf7f61
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
