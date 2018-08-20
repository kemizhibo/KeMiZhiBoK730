package com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean;

/**
 * Author: yhr
 * Date: on 2018/7/3.
 * Describe: 视频详情
 */

public class YingXiangDetailsVideoBean {

    /**
     * code : 0
     * message : success
     * content : {"courseId":1003162,"courseName":"郁波讲堂","subjectId":407,"context":"我看你文档写的title是简介我看你文档写的title是简介我看你文档写的title是简介我看你文档写的title是简介我看你文档写的title是简介我看你文档写的title是简介我看你文档写的title是简介我看你文档写的title是简介我看你文档写的title是简介我看你文档写的title是简介我看你文档写的title是简介我看你文档写的title是简介我看你文档写的title是简介我看你文档写的title是简介我看你文档写的title是简介我看你文档写的title是简介我看你文档写的title是简介","title":"","logo":"http://192.168.1.101:8080/images/upload/course/20180720/1532051533875.jpg","pageViewcount":84230,"playCount":15901,"commentnum":null,"courseType":"TEACHERCOURSE","knowledge":"新建知识点","liveStartDuration":null,"videoDuration":null,"liveStatus":"UNKNOWN","teacherName":"郁波","liveBeginTime":null,"liveEndTime":null,"praiseHistory":0,"favouriteHistory":0,"praiseCount":416,"imageText":null,"isImageText":0,"addTime":1524192412000,"teacher":{"picPath":"/images/upload/teacher/20150915/1442297919077.jpg","career":"为中国科学教育的振兴而努力","education":"科学讲师","isStar":true,"name":"郁波","id":171},"startTime":null,"courseSource":"1672","courseEdition":"1652","videoUrl":"","materialEdition":"1591","watchTime":0,"isEnd":0}
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
         * courseId : 1003162
         * courseName : 郁波讲堂
         * subjectId : 407
         * context : 我看你文档写的title是简介我看你文档写的title是简介我看你文档写的title是简介我看你文档写的title是简介我看你文档写的title是简介我看你文档写的title是简介我看你文档写的title是简介我看你文档写的title是简介我看你文档写的title是简介我看你文档写的title是简介我看你文档写的title是简介我看你文档写的title是简介我看你文档写的title是简介我看你文档写的title是简介我看你文档写的title是简介我看你文档写的title是简介我看你文档写的title是简介
         * title :
         * logo : http://192.168.1.101:8080/images/upload/course/20180720/1532051533875.jpg
         * pageViewcount : 84230
         * playCount : 15901
         * commentnum : null
         * courseType : TEACHERCOURSE
         * knowledge : 新建知识点
         * liveStartDuration : null
         * videoDuration : null
         * liveStatus : UNKNOWN
         * teacherName : 郁波
         * liveBeginTime : null
         * liveEndTime : null
         * praiseHistory : 0
         * favouriteHistory : 0
         * praiseCount : 416
         * imageText : null
         * isImageText : 0
         * addTime : 1524192412000
         * teacher : {"picPath":"/images/upload/teacher/20150915/1442297919077.jpg","career":"为中国科学教育的振兴而努力","education":"科学讲师","isStar":true,"name":"郁波","id":171}
         * startTime : null
         * courseSource : 1672
         * courseEdition : 1652
         * videoUrl :
         * materialEdition : 1591
         * watchTime : 0
         * isEnd : 0
         */

        private int courseId;
        private String courseName;
        private int subjectId;
        private String context;
        private String title;
        private String logo;
        private int pageViewcount;
        private int playCount;
        private Object commentnum;
        private String courseType;
        private String knowledge;
        private Object liveStartDuration;
        private Object videoDuration;
        private String liveStatus;
        private String teacherName;
        private Object liveBeginTime;
        private Object liveEndTime;
        private int praiseHistory;
        private int favouriteHistory;
        private int praiseCount;
        private Object imageText;
        private int isImageText;
        private long addTime;
        private TeacherBean teacher;
        private Object startTime;
        private String courseSource;
        private String courseEdition;
        private String videoUrl;
        private String materialEdition;
        private int watchTime;
        private int isEnd;

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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
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

        public int getPlayCount() {
            return playCount;
        }

        public void setPlayCount(int playCount) {
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

        public String getKnowledge() {
            return knowledge;
        }

        public void setKnowledge(String knowledge) {
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

        public String getTeacherName() {
            return teacherName;
        }

        public void setTeacherName(String teacherName) {
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

        public Object getImageText() {
            return imageText;
        }

        public void setImageText(Object imageText) {
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

        public TeacherBean getTeacher() {
            return teacher;
        }

        public void setTeacher(TeacherBean teacher) {
            this.teacher = teacher;
        }

        public Object getStartTime() {
            return startTime;
        }

        public void setStartTime(Object startTime) {
            this.startTime = startTime;
        }

        public String getCourseSource() {
            return courseSource;
        }

        public void setCourseSource(String courseSource) {
            this.courseSource = courseSource;
        }

        public String getCourseEdition() {
            return courseEdition;
        }

        public void setCourseEdition(String courseEdition) {
            this.courseEdition = courseEdition;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public String getMaterialEdition() {
            return materialEdition;
        }

        public void setMaterialEdition(String materialEdition) {
            this.materialEdition = materialEdition;
        }

        public int getWatchTime() {
            return watchTime;
        }

        public void setWatchTime(int watchTime) {
            this.watchTime = watchTime;
        }

        public int getIsEnd() {
            return isEnd;
        }

        public void setIsEnd(int isEnd) {
            this.isEnd = isEnd;
        }

        public static class TeacherBean {
            /**
             * picPath : /images/upload/teacher/20150915/1442297919077.jpg
             * career : 为中国科学教育的振兴而努力
             * education : 科学讲师
             * isStar : true
             * name : 郁波
             * id : 171
             */

            private String picPath;
            private String career;
            private String education;
            private boolean isStar;
            private String name;
            private int id;

            public String getPicPath() {
                return picPath;
            }

            public void setPicPath(String picPath) {
                this.picPath = picPath;
            }

            public String getCareer() {
                return career;
            }

            public void setCareer(String career) {
                this.career = career;
            }

            public String getEducation() {
                return education;
            }

            public void setEducation(String education) {
                this.education = education;
            }

            public boolean isIsStar() {
                return isStar;
            }

            public void setIsStar(boolean isStar) {
                this.isStar = isStar;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            @Override
            public String toString() {
                return "TeacherBean{" +
                        "picPath='" + picPath + '\'' +
                        ", career='" + career + '\'' +
                        ", education='" + education + '\'' +
                        ", isStar=" + isStar +
                        ", name='" + name + '\'' +
                        ", id=" + id +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "ContentBean{" +
                    "courseId=" + courseId +
                    ", courseName='" + courseName + '\'' +
                    ", subjectId=" + subjectId +
                    ", context='" + context + '\'' +
                    ", title='" + title + '\'' +
                    ", logo='" + logo + '\'' +
                    ", pageViewcount=" + pageViewcount +
                    ", playCount=" + playCount +
                    ", commentnum=" + commentnum +
                    ", courseType='" + courseType + '\'' +
                    ", knowledge='" + knowledge + '\'' +
                    ", liveStartDuration=" + liveStartDuration +
                    ", videoDuration=" + videoDuration +
                    ", liveStatus='" + liveStatus + '\'' +
                    ", teacherName='" + teacherName + '\'' +
                    ", liveBeginTime=" + liveBeginTime +
                    ", liveEndTime=" + liveEndTime +
                    ", praiseHistory=" + praiseHistory +
                    ", favouriteHistory=" + favouriteHistory +
                    ", praiseCount=" + praiseCount +
                    ", imageText=" + imageText +
                    ", isImageText=" + isImageText +
                    ", addTime=" + addTime +
                    ", teacher=" + teacher +
                    ", startTime=" + startTime +
                    ", courseSource='" + courseSource + '\'' +
                    ", courseEdition='" + courseEdition + '\'' +
                    ", videoUrl='" + videoUrl + '\'' +
                    ", materialEdition='" + materialEdition + '\'' +
                    ", watchTime=" + watchTime +
                    ", isEnd=" + isEnd +
                    '}';
        }
    }
}
