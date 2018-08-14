package com.kemizhibo.kemizhibo.yhr.bean.personcenterbean;

import java.util.List;

/**
 * Author: 闫浩然
 * Date: on 2018/7/31.
 * Describe:收藏夹实体类
 */

public class CollectionBoxBean {

    /**
     * code : 0
     * message : success
     * content : {"page":1,"size":8,"total":7,"data":[{"id":217012,"course":{"courseId":1004712,"courseName":"视频22222222","isAvaliable":1,"subjectId":404,"logo":"http://192.168.1.101:8080/images/upload/course/20180713/1531477404649.jpg","courseType":"YINGXIANGSUCAI","commentnum":1,"pageViewcount":40,"duration":null,"teacherName":"","liveBeginTime":null,"liveEndTime":null,"liveStatus":null,"sequence":null,"isImageText":0,"title":"22222222222222","imageText":"[]"},"userId":27782,"addTime":"2018-07-31"},{"id":216722,"course":{"courseId":2852,"courseName":"123op","isAvaliable":1,"subjectId":404,"logo":"http://39.155.221.165:8080/images/upload/course/20180720/1532051402801.jpg","courseType":"YINGXIANGSUCAI","commentnum":2,"pageViewcount":19776,"duration":null,"teacherName":"林奇","liveBeginTime":null,"liveEndTime":null,"liveStatus":null,"sequence":null,"isImageText":0,"title":"123","imageText":null},"userId":27782,"addTime":"2018-07-31"},{"id":214362,"course":{"courseId":1004702,"courseName":"图文","isAvaliable":1,"subjectId":404,"logo":"http://39.155.221.165:8080/images/upload/course/20180720/1532051402801.jpg","courseType":"YINGXIANGSUCAI","commentnum":0,"pageViewcount":20050,"duration":null,"teacherName":"","liveBeginTime":null,"liveEndTime":null,"liveStatus":null,"sequence":null,"isImageText":1,"title":"3456789威尔与","imageText":"[{\"text\":\"了大好人的故事如果输入法个人\",\"imgList\":[\"http://192.168.1.101:8080/images/upload/inxedu/20180713/1531477384963.jpg\"]}]"},"userId":27782,"addTime":"2018-07-30"},{"id":214162,"course":{"courseId":1003032,"courseName":"12312","isAvaliable":1,"subjectId":405,"logo":"http://39.155.221.165:8080/images/upload/course/20180720/1532051402801.jpg","courseType":"YINGXIANGSUCAI","commentnum":4,"pageViewcount":13886,"duration":null,"teacherName":"李立","liveBeginTime":null,"liveEndTime":null,"liveStatus":null,"sequence":null,"isImageText":0,"title":"123","imageText":null},"userId":27782,"addTime":"2018-07-30"},{"id":213792,"course":{"courseId":2282,"courseName":"测试直播转点播","isAvaliable":1,"subjectId":404,"logo":"http://39.155.221.165:8080/images/upload/course/20180720/1532051402801.jpg","courseType":"YINGXIANGSUCAI","commentnum":4,"pageViewcount":14958,"duration":null,"teacherName":"李小梅","liveBeginTime":null,"liveEndTime":null,"liveStatus":null,"sequence":null,"isImageText":0,"title":"测试课程","imageText":null},"userId":27782,"addTime":"2018-07-30"},{"id":127472,"course":{"courseId":1003182,"courseName":"郁波老师","isAvaliable":1,"subjectId":404,"logo":"http://39.155.221.165:8080/images/upload/course/20180720/1532051402801.jpg","courseType":"YINGXIANGSUCAI","commentnum":6,"pageViewcount":17956,"duration":null,"teacherName":"郁波","liveBeginTime":null,"liveEndTime":null,"liveStatus":null,"sequence":null,"isImageText":0,"title":"郁波老师讲科学","imageText":null},"userId":27782,"addTime":"2018-07-24"},{"id":56632,"course":{"courseId":1003722,"courseName":"uuu","isAvaliable":1,"subjectId":407,"logo":"http://39.155.221.165:8080/images/upload/course/20180720/1532051402801.jpg","courseType":"TEACHERCOURSE","commentnum":14,"pageViewcount":92940,"duration":null,"teacherName":"测试","liveBeginTime":null,"liveEndTime":null,"liveStatus":null,"sequence":null,"isImageText":1,"title":"uuuuu","imageText":"[{\"text\":\"哈哈哈\",\"imgList\":[\"http://192.168.1.101:8080/images/upload/course/20180713/1531477389117.jpg\"]}]"},"userId":27782,"addTime":"2018-07-22"}]}
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
         * page : 1
         * size : 8
         * total : 7
         * data : [{"id":217012,"course":{"courseId":1004712,"courseName":"视频22222222","isAvaliable":1,"subjectId":404,"logo":"http://192.168.1.101:8080/images/upload/course/20180713/1531477404649.jpg","courseType":"YINGXIANGSUCAI","commentnum":1,"pageViewcount":40,"duration":null,"teacherName":"","liveBeginTime":null,"liveEndTime":null,"liveStatus":null,"sequence":null,"isImageText":0,"title":"22222222222222","imageText":"[]"},"userId":27782,"addTime":"2018-07-31"},{"id":216722,"course":{"courseId":2852,"courseName":"123op","isAvaliable":1,"subjectId":404,"logo":"http://39.155.221.165:8080/images/upload/course/20180720/1532051402801.jpg","courseType":"YINGXIANGSUCAI","commentnum":2,"pageViewcount":19776,"duration":null,"teacherName":"林奇","liveBeginTime":null,"liveEndTime":null,"liveStatus":null,"sequence":null,"isImageText":0,"title":"123","imageText":null},"userId":27782,"addTime":"2018-07-31"},{"id":214362,"course":{"courseId":1004702,"courseName":"图文","isAvaliable":1,"subjectId":404,"logo":"http://39.155.221.165:8080/images/upload/course/20180720/1532051402801.jpg","courseType":"YINGXIANGSUCAI","commentnum":0,"pageViewcount":20050,"duration":null,"teacherName":"","liveBeginTime":null,"liveEndTime":null,"liveStatus":null,"sequence":null,"isImageText":1,"title":"3456789威尔与","imageText":"[{\"text\":\"了大好人的故事如果输入法个人\",\"imgList\":[\"http://192.168.1.101:8080/images/upload/inxedu/20180713/1531477384963.jpg\"]}]"},"userId":27782,"addTime":"2018-07-30"},{"id":214162,"course":{"courseId":1003032,"courseName":"12312","isAvaliable":1,"subjectId":405,"logo":"http://39.155.221.165:8080/images/upload/course/20180720/1532051402801.jpg","courseType":"YINGXIANGSUCAI","commentnum":4,"pageViewcount":13886,"duration":null,"teacherName":"李立","liveBeginTime":null,"liveEndTime":null,"liveStatus":null,"sequence":null,"isImageText":0,"title":"123","imageText":null},"userId":27782,"addTime":"2018-07-30"},{"id":213792,"course":{"courseId":2282,"courseName":"测试直播转点播","isAvaliable":1,"subjectId":404,"logo":"http://39.155.221.165:8080/images/upload/course/20180720/1532051402801.jpg","courseType":"YINGXIANGSUCAI","commentnum":4,"pageViewcount":14958,"duration":null,"teacherName":"李小梅","liveBeginTime":null,"liveEndTime":null,"liveStatus":null,"sequence":null,"isImageText":0,"title":"测试课程","imageText":null},"userId":27782,"addTime":"2018-07-30"},{"id":127472,"course":{"courseId":1003182,"courseName":"郁波老师","isAvaliable":1,"subjectId":404,"logo":"http://39.155.221.165:8080/images/upload/course/20180720/1532051402801.jpg","courseType":"YINGXIANGSUCAI","commentnum":6,"pageViewcount":17956,"duration":null,"teacherName":"郁波","liveBeginTime":null,"liveEndTime":null,"liveStatus":null,"sequence":null,"isImageText":0,"title":"郁波老师讲科学","imageText":null},"userId":27782,"addTime":"2018-07-24"},{"id":56632,"course":{"courseId":1003722,"courseName":"uuu","isAvaliable":1,"subjectId":407,"logo":"http://39.155.221.165:8080/images/upload/course/20180720/1532051402801.jpg","courseType":"TEACHERCOURSE","commentnum":14,"pageViewcount":92940,"duration":null,"teacherName":"测试","liveBeginTime":null,"liveEndTime":null,"liveStatus":null,"sequence":null,"isImageText":1,"title":"uuuuu","imageText":"[{\"text\":\"哈哈哈\",\"imgList\":[\"http://192.168.1.101:8080/images/upload/course/20180713/1531477389117.jpg\"]}]"},"userId":27782,"addTime":"2018-07-22"}]
         */

        private int page;
        private int size;
        private int total;
        private List<DataBean> data;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * id : 217012
             * course : {"courseId":1004712,"courseName":"视频22222222","isAvaliable":1,"subjectId":404,"logo":"http://192.168.1.101:8080/images/upload/course/20180713/1531477404649.jpg","courseType":"YINGXIANGSUCAI","commentnum":1,"pageViewcount":40,"duration":null,"teacherName":"","liveBeginTime":null,"liveEndTime":null,"liveStatus":null,"sequence":null,"isImageText":0,"title":"22222222222222","imageText":"[]"}
             * userId : 27782
             * addTime : 2018-07-31
             */

            private int id;
            private CourseBean course;
            private int userId;
            private String addTime;
            //多选框是否选中
            private boolean isSelect;

            public boolean isSelect() {
                return isSelect;
            }

            public void setSelect(boolean isSelect) {
                this.isSelect = isSelect;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public CourseBean getCourse() {
                return course;
            }

            public void setCourse(CourseBean course) {
                this.course = course;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getAddTime() {
                return addTime;
            }

            public void setAddTime(String addTime) {
                this.addTime = addTime;
            }

            public static class CourseBean {
                /**
                 * courseId : 1004712
                 * courseName : 视频22222222
                 * isAvaliable : 1
                 * subjectId : 404
                 * logo : http://192.168.1.101:8080/images/upload/course/20180713/1531477404649.jpg
                 * courseType : YINGXIANGSUCAI
                 * commentnum : 1
                 * pageViewcount : 40
                 * duration : null
                 * teacherName :
                 * liveBeginTime : null
                 * liveEndTime : null
                 * liveStatus : null
                 * sequence : null
                 * isImageText : 0
                 * title : 22222222222222
                 * imageText : []
                 */

                private int courseId;
                private String courseName;
                private int isAvaliable;
                private int subjectId;
                private String logo;
                private String courseType;
                private int commentnum;
                private int pageViewcount;
                private Object duration;
                private String teacherName;
                private Object liveBeginTime;
                private Object liveEndTime;
                private Object liveStatus;
                private Object sequence;
                private int isImageText;
                private String title;
                private String imageText;

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

                public int getIsAvaliable() {
                    return isAvaliable;
                }

                public void setIsAvaliable(int isAvaliable) {
                    this.isAvaliable = isAvaliable;
                }

                public int getSubjectId() {
                    return subjectId;
                }

                public void setSubjectId(int subjectId) {
                    this.subjectId = subjectId;
                }

                public String getLogo() {
                    return logo;
                }

                public void setLogo(String logo) {
                    this.logo = logo;
                }

                public String getCourseType() {
                    return courseType;
                }

                public void setCourseType(String courseType) {
                    this.courseType = courseType;
                }

                public int getCommentnum() {
                    return commentnum;
                }

                public void setCommentnum(int commentnum) {
                    this.commentnum = commentnum;
                }

                public int getPageViewcount() {
                    return pageViewcount;
                }

                public void setPageViewcount(int pageViewcount) {
                    this.pageViewcount = pageViewcount;
                }

                public Object getDuration() {
                    return duration;
                }

                public void setDuration(Object duration) {
                    this.duration = duration;
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

                public Object getLiveStatus() {
                    return liveStatus;
                }

                public void setLiveStatus(Object liveStatus) {
                    this.liveStatus = liveStatus;
                }

                public Object getSequence() {
                    return sequence;
                }

                public void setSequence(Object sequence) {
                    this.sequence = sequence;
                }

                public int getIsImageText() {
                    return isImageText;
                }

                public void setIsImageText(int isImageText) {
                    this.isImageText = isImageText;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getImageText() {
                    return imageText;
                }

                public void setImageText(String imageText) {
                    this.imageText = imageText;
                }
            }
        }
    }
}
