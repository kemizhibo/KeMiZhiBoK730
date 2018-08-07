package com.kemizhibo.kemizhibo.other.preparing_package_detail.bean;

/**
 * Created by asus on 2018/8/2.
 */

public class PreparingPicBean {

    /**
     * code : 0
     * message : success
     * content : {"url":"http://192.168.1.101:8080/images/upload/focusing/20171219/1513648598579.jpg","fileName":"观察鱼-图片-1","introduce":"上传1"}
     * otherData : 6
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
         * url : http://192.168.1.101:8080/images/upload/focusing/20171219/1513648598579.jpg
         * fileName : 观察鱼-图片-1
         * introduce : 上传1
         */

        private String url;
        private String fileName;
        private String introduce;

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
