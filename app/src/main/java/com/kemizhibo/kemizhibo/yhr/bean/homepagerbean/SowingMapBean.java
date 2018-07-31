package com.kemizhibo.kemizhibo.yhr.bean.homepagerbean;

import java.util.List;

/**
 * Author: yhr
 * Date: on 2018/7/19.
 * Describe:首页轮播图
 */

public class SowingMapBean {

    /**
     * code : 0
     * message : success
     * content : [{"imagesUrl":"http://192.168.1.101:8080/images/upload/image/20170608/1496906679701.png","TYPE_ID":28,"imageId":352,"previewUrl":"","COLOR":"","typeName":"欢迎banner","linkAddress":"/","title":"welcome3","DESCRIBE":"welcome3","seriesNumber":1},{"imagesUrl":"http://192.168.1.101:8080/images/upload/image/20170608/1496906658127.png","TYPE_ID":28,"imageId":351,"previewUrl":"","COLOR":"","typeName":"欢迎banner","linkAddress":"/","title":"welcome2","DESCRIBE":"welcome2","seriesNumber":2},{"imagesUrl":"http://192.168.1.101:8080/images/upload/image/20170608/1496906633897.png","TYPE_ID":28,"imageId":350,"previewUrl":"","COLOR":"","typeName":"欢迎banner","linkAddress":"/","title":"welcome1","DESCRIBE":"welcome1","seriesNumber":3},{"imagesUrl":"http://192.168.1.101:8080/images/upload/image/20170608/1496906596107.jpg","TYPE_ID":28,"imageId":349,"previewUrl":"","COLOR":"","typeName":"欢迎banner","linkAddress":"/","title":"welcome0","DESCRIBE":"welcome0","seriesNumber":4}]
     * otherData : null
     */

    private int code;
    private String message;
    private Object otherData;
    private List<ContentBean> content;

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

    public Object getOtherData() {
        return otherData;
    }

    public void setOtherData(Object otherData) {
        this.otherData = otherData;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * imagesUrl : http://192.168.1.101:8080/images/upload/image/20170608/1496906679701.png
         * TYPE_ID : 28
         * imageId : 352
         * previewUrl :
         * COLOR :
         * typeName : 欢迎banner
         * linkAddress : /
         * title : welcome3
         * DESCRIBE : welcome3
         * seriesNumber : 1
         */

        private String imagesUrl;
        private int TYPE_ID;
        private int imageId;
        private String previewUrl;
        private String COLOR;
        private String typeName;
        private String linkAddress;
        private String title;
        private String DESCRIBE;
        private int seriesNumber;

        public String getImagesUrl() {
            return imagesUrl;
        }

        public void setImagesUrl(String imagesUrl) {
            this.imagesUrl = imagesUrl;
        }

        public int getTYPE_ID() {
            return TYPE_ID;
        }

        public void setTYPE_ID(int TYPE_ID) {
            this.TYPE_ID = TYPE_ID;
        }

        public int getImageId() {
            return imageId;
        }

        public void setImageId(int imageId) {
            this.imageId = imageId;
        }

        public String getPreviewUrl() {
            return previewUrl;
        }

        public void setPreviewUrl(String previewUrl) {
            this.previewUrl = previewUrl;
        }

        public String getCOLOR() {
            return COLOR;
        }

        public void setCOLOR(String COLOR) {
            this.COLOR = COLOR;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getLinkAddress() {
            return linkAddress;
        }

        public void setLinkAddress(String linkAddress) {
            this.linkAddress = linkAddress;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDESCRIBE() {
            return DESCRIBE;
        }

        public void setDESCRIBE(String DESCRIBE) {
            this.DESCRIBE = DESCRIBE;
        }

        public int getSeriesNumber() {
            return seriesNumber;
        }

        public void setSeriesNumber(int seriesNumber) {
            this.seriesNumber = seriesNumber;
        }
    }
}
