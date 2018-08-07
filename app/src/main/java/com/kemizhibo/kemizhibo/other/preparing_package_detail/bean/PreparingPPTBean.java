package com.kemizhibo.kemizhibo.other.preparing_package_detail.bean;

/**
 * Created by asus on 2018/8/2.
 */

public class PreparingPPTBean {

    /**
     * code : 0
     * message : success
     * content : {"docId":"ifrn9c4zgzbtwut","url":"http://kemi.bj.bcebos.com/kemi/1529998948904.pptx","fileName":"Appium自动化测试框架详解.pptx","introduce":"用户上传ppt"}
     * otherData : 3
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
         * docId : ifrn9c4zgzbtwut
         * url : http://kemi.bj.bcebos.com/kemi/1529998948904.pptx
         * fileName : Appium自动化测试框架详解.pptx
         * introduce : 用户上传ppt
         */

        private String docId;
        private String url;
        private String fileName;
        private String introduce;

        public String getDocId() {
            return docId;
        }

        public void setDocId(String docId) {
            this.docId = docId;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }
    }
}
