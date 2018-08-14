package com.kemizhibo.kemizhibo.yhr.bean.homepagerbean;

/**
 * Author: 闫浩然
 * Date: on 2018/8/12.
 * Describe: 获取版本信息
 */

public class VersionInformationBean {

    /**
     * code : 0
     * message : success
     * content : {"fileId":2,"fileName":"科米课堂","fileSize":1000,"fileType":1,"filePath":"/apk/app-release.apk","versionNo":"1.0.0","imprint":"","appType":1,"isAvailable":1,"gmtCreate":1533799469000,"gmtModified":1533801384000}
     * page : null
     */

    private int code;
    private String message;
    private ContentBean content;
    private Object page;

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

    public Object getPage() {
        return page;
    }

    public void setPage(Object page) {
        this.page = page;
    }

    public static class ContentBean {
        /**
         * fileId : 2
         * fileName : 科米课堂
         * fileSize : 1000
         * fileType : 1
         * filePath : /apk/app-release.apk
         * versionNo : 1.0.0
         * imprint :
         * appType : 1
         * isAvailable : 1
         * gmtCreate : 1533799469000
         * gmtModified : 1533801384000
         */

        private int fileId;
        private String fileName;
        private int fileSize;
        private int fileType;
        private String filePath;
        private String versionNo;
        private String imprint;
        private int appType;
        private int isAvailable;
        private long gmtCreate;
        private long gmtModified;

        public int getFileId() {
            return fileId;
        }

        public void setFileId(int fileId) {
            this.fileId = fileId;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public int getFileSize() {
            return fileSize;
        }

        public void setFileSize(int fileSize) {
            this.fileSize = fileSize;
        }

        public int getFileType() {
            return fileType;
        }

        public void setFileType(int fileType) {
            this.fileType = fileType;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public String getVersionNo() {
            return versionNo;
        }

        public void setVersionNo(String versionNo) {
            this.versionNo = versionNo;
        }

        public String getImprint() {
            return imprint;
        }

        public void setImprint(String imprint) {
            this.imprint = imprint;
        }

        public int getAppType() {
            return appType;
        }

        public void setAppType(int appType) {
            this.appType = appType;
        }

        public int getIsAvailable() {
            return isAvailable;
        }

        public void setIsAvailable(int isAvailable) {
            this.isAvailable = isAvailable;
        }

        public long getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(long gmtCreate) {
            this.gmtCreate = gmtCreate;
        }

        public long getGmtModified() {
            return gmtModified;
        }

        public void setGmtModified(long gmtModified) {
            this.gmtModified = gmtModified;
        }
    }
}
