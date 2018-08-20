package com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean;

/**
 * Author: 闫浩然
 * Date: on 2018/7/22.
 * Describe:图文
 */

public class PictureBean {


    /**
     * code : 0
     * message : success
     * content : {"courseId":1004722,"courseName":"哈哈哈哈","subjectId":404,"context":"哈哈哈哈","title":null,"logo":"http://192.168.1.101:8080/images/upload/course/20180716/1531711701149.jpg","pageViewcount":907,"playCount":null,"commentnum":null,"courseType":"YINGXIANGSUCAI","knowledge":null,"liveStartDuration":null,"videoDuration":null,"liveStatus":"UNKNOWN","teacherName":null,"liveBeginTime":null,"liveEndTime":null,"praiseHistory":0,"favouriteHistory":0,"praiseCount":317,"imageText":"[{\"text\":\"哈哈哈\",\"imgList\":[\"http://192.168.1.101:8080/images/upload/inxedu/20180716/1531711680498.gif\"]},{\"text\":\"哈哈哈哈\",\"imgList\":[\"http://192.168.1.101:8080/images/upload/inxedu/20180716/1531711686070.jpg\",\"http://192.168.1.101:8080/images/upload/inxedu/20180716/1531711692344.jpeg\"]}]","isImageText":1,"addTime":1531711741000,"teacher":null,"startTime":null,"courseSource":null,"courseEdition":null,"videoUrl":null,"materialEdition":"1581"}
     * otherData : null
     */

    private int code;
    private String message;
    private ContentBean content;
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

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public Object getOtherData() {
        return otherData;
    }

    public void setOtherData(Object otherData) {
        this.otherData = otherData;
    }

    public static class ContentBean {
        /**
         * courseId : 1004722
         * courseName : 哈哈哈哈
         * subjectId : 404
         * context : 哈哈哈哈
         * title : null
         * logo : http://192.168.1.101:8080/images/upload/course/20180716/1531711701149.jpg
         * pageViewcount : 907
         * playCount : null
         * commentnum : null
         * courseType : YINGXIANGSUCAI
         * knowledge : null
         * liveStartDuration : null
         * videoDuration : null
         * liveStatus : UNKNOWN
         * teacherName : null
         * liveBeginTime : null
         * liveEndTime : null
         * praiseHistory : 0
         * favouriteHistory : 0
         * praiseCount : 317
         * imageText : [{"text":"哈哈哈","imgList":["http://192.168.1.101:8080/images/upload/inxedu/20180716/1531711680498.gif"]},{"text":"哈哈哈哈","imgList":["http://192.168.1.101:8080/images/upload/inxedu/20180716/1531711686070.jpg","http://192.168.1.101:8080/images/upload/inxedu/20180716/1531711692344.jpeg"]}]
         * isImageText : 1
         * addTime : 1531711741000
         * teacher : null
         * startTime : null
         * courseSource : null
         * courseEdition : null
         * videoUrl : null
         * materialEdition : 1581
         */

        private int courseId;
        private String courseName;
        private int subjectId;
        private String context;
        private Object title;
        private String logo;
        private int pageViewcount;
        private Object playCount;
        private Object commentnum;
        private String courseType;
        private Object knowledge;
        private Object liveStartDuration;
        private Object videoDuration;
        private String liveStatus;
        private Object teacherName;
        private Object liveBeginTime;
        private Object liveEndTime;
        private int praiseHistory;
        private int favouriteHistory;
        private int praiseCount;
        private String imageText;
        private int isImageText;
        private long addTime;
        private Object teacher;
        private Object startTime;
        private Object courseSource;
        private Object courseEdition;
        private Object videoUrl;
        private String materialEdition;

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public int getSubjectId() {
            return subjectId;
        }

        public void setSubjectId(int subjectId) {
            this.subjectId = subjectId;
        }

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }

        public Object getTitle() {
            return title;
        }

        public void setTitle(Object title) {
            this.title = title;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public int getPageViewcount() {
            return pageViewcount;
        }

        public void setPageViewcount(int pageViewcount) {
            this.pageViewcount = pageViewcount;
        }

        public Object getPlayCount() {
            return playCount;
        }

        public void setPlayCount(Object playCount) {
            this.playCount = playCount;
        }

        public Object getCommentnum() {
            return commentnum;
        }

        public void setCommentnum(Object commentnum) {
            this.commentnum = commentnum;
        }

        public String getCourseType() {
            return courseType;
        }

        public void setCourseType(String courseType) {
            this.courseType = courseType;
        }

        public Object getKnowledge() {
            return knowledge;
        }

        public void setKnowledge(Object knowledge) {
            this.knowledge = knowledge;
        }

        public Object getLiveStartDuration() {
            return liveStartDuration;
        }

        public void setLiveStartDuration(Object liveStartDuration) {
            this.liveStartDuration = liveStartDuration;
        }

        public Object getVideoDuration() {
            return videoDuration;
        }

        public void setVideoDuration(Object videoDuration) {
            this.videoDuration = videoDuration;
        }

        public String getLiveStatus() {
            return liveStatus;
        }

        public void setLiveStatus(String liveStatus) {
            this.liveStatus = liveStatus;
        }

        public Object getTeacherName() {
            return teacherName;
        }

        public void setTeacherName(Object teacherName) {
            this.teacherName = teacherName;
        }

        public Object getLiveBeginTime() {
            return liveBeginTime;
        }

        public void setLiveBeginTime(Object liveBeginTime) {
            this.liveBeginTime = liveBeginTime;
        }

        public Object getLiveEndTime() {
            return liveEndTime;
        }

        public void setLiveEndTime(Object liveEndTime) {
            this.liveEndTime = liveEndTime;
        }

        public int getPraiseHistory() {
            return praiseHistory;
        }

        public void setPraiseHistory(int praiseHistory) {
            this.praiseHistory = praiseHistory;
        }

        public int getFavouriteHistory() {
            return favouriteHistory;
        }

        public void setFavouriteHistory(int favouriteHistory) {
            this.favouriteHistory = favouriteHistory;
        }

        public int getPraiseCount() {
            return praiseCount;
        }

        public void setPraiseCount(int praiseCount) {
            this.praiseCount = praiseCount;
        }

        public String getImageText() {
            return imageText;
        }

        public void setImageText(String imageText) {
            this.imageText = imageText;
        }

        public int getIsImageText() {
            return isImageText;
        }

        public void setIsImageText(int isImageText) {
            this.isImageText = isImageText;
        }

        public long getAddTime() {
            return addTime;
        }

        public void setAddTime(long addTime) {
            this.addTime = addTime;
        }

        public Object getTeacher() {
            return teacher;
        }

        public void setTeacher(Object teacher) {
            this.teacher = teacher;
        }

        public Object getStartTime() {
            return startTime;
        }

        public void setStartTime(Object startTime) {
            this.startTime = startTime;
        }

        public Object getCourseSource() {
            return courseSource;
        }

        public void setCourseSource(Object courseSource) {
            this.courseSource = courseSource;
        }

        public Object getCourseEdition() {
            return courseEdition;
        }

        public void setCourseEdition(Object courseEdition) {
            this.courseEdition = courseEdition;
        }

        public Object getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(Object videoUrl) {
            this.videoUrl = videoUrl;
        }

        public String getMaterialEdition() {
            return materialEdition;
        }

        public void setMaterialEdition(String materialEdition) {
            this.materialEdition = materialEdition;
        }

        @Override
        public String toString() {
            return "ContentBean{" +
                    "courseId=" + courseId +
                    ", courseName='" + courseName + '\'' +
                    ", subjectId=" + subjectId +
                    ", context='" + context + '\'' +
                    ", title=" + title +
                    ", logo='" + logo + '\'' +
                    ", pageViewcount=" + pageViewcount +
                    ", playCount=" + playCount +
                    ", commentnum=" + commentnum +
                    ", courseType='" + courseType + '\'' +
                    ", knowledge=" + knowledge +
                    ", liveStartDuration=" + liveStartDuration +
                    ", videoDuration=" + videoDuration +
                    ", liveStatus='" + liveStatus + '\'' +
                    ", teacherName=" + teacherName +
                    ", liveBeginTime=" + liveBeginTime +
                    ", liveEndTime=" + liveEndTime +
                    ", praiseHistory=" + praiseHistory +
                    ", favouriteHistory=" + favouriteHistory +
                    ", praiseCount=" + praiseCount +
                    ", imageText='" + imageText + '\'' +
                    ", isImageText=" + isImageText +
                    ", addTime=" + addTime +
                    ", teacher=" + teacher +
                    ", startTime=" + startTime +
                    ", courseSource=" + courseSource +
                    ", courseEdition=" + courseEdition +
                    ", videoUrl=" + videoUrl +
                    ", materialEdition='" + materialEdition + '\'' +
                    '}';
        }
    }
}
