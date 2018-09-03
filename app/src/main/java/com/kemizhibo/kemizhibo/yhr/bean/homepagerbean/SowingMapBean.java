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
     * content : [{"imagesUrl":"http://kemivideouploadtest.oss-cn-beijing.aliyuncs.com/courseimage/image/1534929895398.jpg???¶m=image","TYPE_ID":62,"courseType":"TEACHERCOURSE","imageId":452,"previewUrl":"","COLOR":"","typeName":"app端2.2首页轮播图","linkAddress":"1003732","title":"app首页轮播图-1","DESCRIBE":"app","courseId":"1003732","seriesNumber":0},{"imagesUrl":"http://kemivideouploadtest.oss-cn-beijing.aliyuncs.com/courseimage/image/1534929909706.jpg???¶m=image","TYPE_ID":62,"courseType":"TEACHERCOURSE","imageId":462,"previewUrl":"","COLOR":"","typeName":"app端2.2首页轮播图","linkAddress":"1003712","title":"app首页轮播图-2","DESCRIBE":"app","courseId":"1003712","seriesNumber":1}]
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
         * imagesUrl : http://kemivideouploadtest.oss-cn-beijing.aliyuncs.com/courseimage/image/1534929895398.jpg???¶m=image
         * TYPE_ID : 62
         * courseType : TEACHERCOURSE
         * imageId : 452
         * previewUrl :
         * COLOR :
         * typeName : app端2.2首页轮播图
         * linkAddress : 1003732
         * title : app首页轮播图-1
         * DESCRIBE : app
         * courseId : 1003732
         * seriesNumber : 0
         */

        private String imagesUrl;
        private int TYPE_ID;
        private String courseType;
        private int imageId;
        private String previewUrl;
        private String COLOR;
        private String typeName;
        private String linkAddress;
        private String title;
        private String DESCRIBE;
        private String courseId;
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

        public String getCourseType() {
            return courseType;
        }

        public void setCourseType(String courseType) {
            this.courseType = courseType;
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

        public String getCourseId() {
            return courseId;
        }

        public void setCourseId(String courseId) {
            this.courseId = courseId;
        }

        public int getSeriesNumber() {
            return seriesNumber;
        }

        public void setSeriesNumber(int seriesNumber) {
            this.seriesNumber = seriesNumber;
        }
    }
}
