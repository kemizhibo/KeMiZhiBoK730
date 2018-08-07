package com.kemizhibo.kemizhibo.other.preparing_package_detail.bean;

/**
 * Created by asus on 2018/8/3.
 */

public class PreparingWordBen {


    /**
     * code : 0
     * message : success
     * content : {"fileId":null,"type":null,"docId":"doc-ih2ph7uc090hewk","courseId":null,"kpointId":null,"url":"http://kemi.bj.bcebos.com/kemi/1533285332380.doc","fileName":"555.doc","introduce":"","size":null,"userVideoLogo":"","createTime":null,"createName":null}
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
         * fileId : null
         * type : null
         * docId : doc-ih2ph7uc090hewk
         * courseId : null
         * kpointId : null
         * url : http://kemi.bj.bcebos.com/kemi/1533285332380.doc
         * fileName : 555.doc
         * introduce :
         * size : null
         * userVideoLogo :
         * createTime : null
         * createName : null
         */

        private Object fileId;
        private Object type;
        private String docId;
        private Object courseId;
        private Object kpointId;
        private String url;
        private String fileName;
        private String introduce;
        private Object size;
        private String userVideoLogo;
        private Object createTime;
        private Object createName;

        public Object getFileId() {
            return fileId;
        }

        public void setFileId(Object fileId) {
            this.fileId = fileId;
        }

        public Object getType() {
            return type;
        }

        public void setType(Object type) {
            this.type = type;
        }

        public String getDocId() {
            return docId;
        }

        public void setDocId(String docId) {
            this.docId = docId;
        }

        public Object getCourseId() {
            return courseId;
        }

        public void setCourseId(Object courseId) {
            this.courseId = courseId;
        }

        public Object getKpointId() {
            return kpointId;
        }

        public void setKpointId(Object kpointId) {
            this.kpointId = kpointId;
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

        public Object getSize() {
            return size;
        }

        public void setSize(Object size) {
            this.size = size;
        }

        public String getUserVideoLogo() {
            return userVideoLogo;
        }

        public void setUserVideoLogo(String userVideoLogo) {
            this.userVideoLogo = userVideoLogo;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public Object getCreateName() {
            return createName;
        }

        public void setCreateName(Object createName) {
            this.createName = createName;
        }
    }
}
