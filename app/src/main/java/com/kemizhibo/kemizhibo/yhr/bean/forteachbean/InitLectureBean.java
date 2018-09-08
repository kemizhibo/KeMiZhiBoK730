package com.kemizhibo.kemizhibo.yhr.bean.forteachbean;

/**
 * Author: 闫浩然
 * Date: on 2018/9/6.
 * Describe:一件授课预览接口
 */

public class InitLectureBean {

    /**
     * code : 0
     * message : success
     * content : {"fileId":null,"type":5,"docId":null,"courseId":2822,"kpointId":5922,"url":null,"fileName":"课程1-视频","introduce":null,"size":null,"userVideoLogo":null,"createTime":null,"createName":null,"isApplicationPlan":1}
     * otherData : 5
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
         * type : 5
         * docId : null
         * courseId : 2822
         * kpointId : 5922
         * url : null
         * fileName : 课程1-视频
         * introduce : null
         * size : null
         * userVideoLogo : null
         * createTime : null
         * createName : null
         * isApplicationPlan : 1
         */

        private Object fileId;
        private int type;
        private Object docId;
        private int courseId;
        private int kpointId;
        private Object url;
        private String fileName;
        private Object introduce;
        private Object size;
        private Object userVideoLogo;
        private Object createTime;
        private Object createName;
        private int isApplicationPlan;

        public Object getFileId() {
            return fileId;
        }

        public void setFileId(Object fileId) {
            this.fileId = fileId;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public Object getDocId() {
            return docId;
        }

        public void setDocId(Object docId) {
            this.docId = docId;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public int getKpointId() {
            return kpointId;
        }

        public void setKpointId(int kpointId) {
            this.kpointId = kpointId;
        }

        public Object getUrl() {
            return url;
        }

        public void setUrl(Object url) {
            this.url = url;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public Object getIntroduce() {
            return introduce;
        }

        public void setIntroduce(Object introduce) {
            this.introduce = introduce;
        }

        public Object getSize() {
            return size;
        }

        public void setSize(Object size) {
            this.size = size;
        }

        public Object getUserVideoLogo() {
            return userVideoLogo;
        }

        public void setUserVideoLogo(Object userVideoLogo) {
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

        public int getIsApplicationPlan() {
            return isApplicationPlan;
        }

        public void setIsApplicationPlan(int isApplicationPlan) {
            this.isApplicationPlan = isApplicationPlan;
        }
    }
}
