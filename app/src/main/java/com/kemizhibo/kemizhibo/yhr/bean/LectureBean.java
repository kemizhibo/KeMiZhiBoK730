package com.kemizhibo.kemizhibo.yhr.bean;

/**
 * Author: 闫浩然
 * Date: on 2018/8/15.
 * Describe: 完成授课保存授课记录
 */

public class LectureBean {

    /**
     * code : 0
     * message : success
     * content : {"recordId":362}
     * otherData : 1
     */

    private int code;
    private String message;
    private ContentBean content;
    private int otherData;

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

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public int getOtherData() {
        return otherData;
    }

    public void setOtherData(int otherData) {
        this.otherData = otherData;
    }

    public static class ContentBean {
        /**
         * recordId : 362
         */

        private int recordId;

        public int getRecordId() {
            return recordId;
        }

        public void setRecordId(int recordId) {
            this.recordId = recordId;
        }
    }
}
