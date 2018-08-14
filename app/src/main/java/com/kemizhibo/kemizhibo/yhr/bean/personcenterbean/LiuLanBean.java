package com.kemizhibo.kemizhibo.yhr.bean.personcenterbean;

import java.util.List;

/**
 * Author: 闫浩然
 * Date: on 2018/8/2.
 * Describe:观看记录实体类
 */

public class LiuLanBean {

    /**
     * code : 0
     * message : success
     * content : {"page":1,"size":10,"total":5,"data":[{"id":"224957149271293952","userId":27782,"course":{"courseId":1004712,"courseName":"视频22222222","isAvaliable":1,"subjectId":404,"logo":"http://192.168.1.101:8080/images/upload/course/20180713/1531477404649.jpg","courseType":"YINGXIANGSUCAI","commentnum":7,"pageViewcount":6496,"duration":null,"teacherName":null,"liveBeginTime":null,"liveEndTime":null,"liveStatus":null,"sequence":null,"isImageText":0,"title":null,"watchTime":72562,"isEnd":0,"imageText":null},"watchTime":"2018-08-09","playPosition":15000},{"id":"224952523096588288","userId":27782,"course":{"courseId":2282,"courseName":"测试直播转点播","isAvaliable":1,"subjectId":404,"logo":"http://39.155.221.165:8080/images/upload/course/20180720/1532051402801.jpg","courseType":"YINGXIANGSUCAI","commentnum":4,"pageViewcount":15831,"duration":null,"teacherName":null,"liveBeginTime":null,"liveEndTime":null,"liveStatus":null,"sequence":null,"isImageText":0,"title":null,"watchTime":0,"isEnd":0,"imageText":null},"watchTime":"2018-08-09","playPosition":155000},{"id":"224952181743157248","userId":27782,"course":{"courseId":1003162,"courseName":"郁波讲堂","isAvaliable":1,"subjectId":407,"logo":"http://192.168.1.101:8080/images/upload/course/20180720/1532051533875.jpg","courseType":"TEACHERCOURSE","commentnum":128,"pageViewcount":80828,"duration":null,"teacherName":null,"liveBeginTime":null,"liveEndTime":null,"liveStatus":null,"sequence":null,"isImageText":0,"title":null,"watchTime":1000000,"isEnd":0,"imageText":null},"watchTime":"2018-08-09","playPosition":5000},{"id":"224951615281430528","userId":27782,"course":{"courseId":2852,"courseName":"123op","isAvaliable":1,"subjectId":404,"logo":"http://39.155.221.165:8080/images/upload/course/20180720/1532051402801.jpg","courseType":"YINGXIANGSUCAI","commentnum":2,"pageViewcount":20226,"duration":null,"teacherName":null,"liveBeginTime":null,"liveEndTime":null,"liveStatus":null,"sequence":null,"isImageText":0,"title":null,"watchTime":0,"isEnd":0,"imageText":null},"watchTime":"2018-08-09","playPosition":635000},{"id":"224941282454142976","userId":27782,"course":{"courseId":1003182,"courseName":"郁波老师","isAvaliable":1,"subjectId":404,"logo":"http://39.155.221.165:8080/images/upload/course/20180720/1532051402801.jpg","courseType":"YINGXIANGSUCAI","commentnum":6,"pageViewcount":18577,"duration":null,"teacherName":null,"liveBeginTime":null,"liveEndTime":null,"liveStatus":null,"sequence":null,"isImageText":0,"title":null,"watchTime":0,"isEnd":0,"imageText":null},"watchTime":"2018-08-09","playPosition":35000}]}
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
         * size : 10
         * total : 5
         * data : [{"id":"224957149271293952","userId":27782,"course":{"courseId":1004712,"courseName":"视频22222222","isAvaliable":1,"subjectId":404,"logo":"http://192.168.1.101:8080/images/upload/course/20180713/1531477404649.jpg","courseType":"YINGXIANGSUCAI","commentnum":7,"pageViewcount":6496,"duration":null,"teacherName":null,"liveBeginTime":null,"liveEndTime":null,"liveStatus":null,"sequence":null,"isImageText":0,"title":null,"watchTime":72562,"isEnd":0,"imageText":null},"watchTime":"2018-08-09","playPosition":15000},{"id":"224952523096588288","userId":27782,"course":{"courseId":2282,"courseName":"测试直播转点播","isAvaliable":1,"subjectId":404,"logo":"http://39.155.221.165:8080/images/upload/course/20180720/1532051402801.jpg","courseType":"YINGXIANGSUCAI","commentnum":4,"pageViewcount":15831,"duration":null,"teacherName":null,"liveBeginTime":null,"liveEndTime":null,"liveStatus":null,"sequence":null,"isImageText":0,"title":null,"watchTime":0,"isEnd":0,"imageText":null},"watchTime":"2018-08-09","playPosition":155000},{"id":"224952181743157248","userId":27782,"course":{"courseId":1003162,"courseName":"郁波讲堂","isAvaliable":1,"subjectId":407,"logo":"http://192.168.1.101:8080/images/upload/course/20180720/1532051533875.jpg","courseType":"TEACHERCOURSE","commentnum":128,"pageViewcount":80828,"duration":null,"teacherName":null,"liveBeginTime":null,"liveEndTime":null,"liveStatus":null,"sequence":null,"isImageText":0,"title":null,"watchTime":1000000,"isEnd":0,"imageText":null},"watchTime":"2018-08-09","playPosition":5000},{"id":"224951615281430528","userId":27782,"course":{"courseId":2852,"courseName":"123op","isAvaliable":1,"subjectId":404,"logo":"http://39.155.221.165:8080/images/upload/course/20180720/1532051402801.jpg","courseType":"YINGXIANGSUCAI","commentnum":2,"pageViewcount":20226,"duration":null,"teacherName":null,"liveBeginTime":null,"liveEndTime":null,"liveStatus":null,"sequence":null,"isImageText":0,"title":null,"watchTime":0,"isEnd":0,"imageText":null},"watchTime":"2018-08-09","playPosition":635000},{"id":"224941282454142976","userId":27782,"course":{"courseId":1003182,"courseName":"郁波老师","isAvaliable":1,"subjectId":404,"logo":"http://39.155.221.165:8080/images/upload/course/20180720/1532051402801.jpg","courseType":"YINGXIANGSUCAI","commentnum":6,"pageViewcount":18577,"duration":null,"teacherName":null,"liveBeginTime":null,"liveEndTime":null,"liveStatus":null,"sequence":null,"isImageText":0,"title":null,"watchTime":0,"isEnd":0,"imageText":null},"watchTime":"2018-08-09","playPosition":35000}]
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
             * id : 224957149271293952
             * userId : 27782
             * course : {"courseId":1004712,"courseName":"视频22222222","isAvaliable":1,"subjectId":404,"logo":"http://192.168.1.101:8080/images/upload/course/20180713/1531477404649.jpg","courseType":"YINGXIANGSUCAI","commentnum":7,"pageViewcount":6496,"duration":null,"teacherName":null,"liveBeginTime":null,"liveEndTime":null,"liveStatus":null,"sequence":null,"isImageText":0,"title":null,"watchTime":72562,"isEnd":0,"imageText":null}
             * watchTime : 2018-08-09
             * playPosition : 15000
             */

            private String id;
            private int userId;
            private CourseBean course;
            private String watchTime;
            private int playPosition;
            //多选框是否选中
            private boolean isSelect;

            public boolean isSelect() {
                return isSelect;
            }

            public void setSelect(boolean isSelect) {
                this.isSelect = isSelect;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public CourseBean getCourse() {
                return course;
            }

            public void setCourse(CourseBean course) {
                this.course = course;
            }

            public String getWatchTime() {
                return watchTime;
            }

            public void setWatchTime(String watchTime) {
                this.watchTime = watchTime;
            }

            public int getPlayPosition() {
                return playPosition;
            }

            public void setPlayPosition(int playPosition) {
                this.playPosition = playPosition;
            }

            public static class CourseBean {
                /**
                 * courseId : 1004712
                 * courseName : 视频22222222
                 * isAvaliable : 1
                 * subjectId : 404
                 * logo : http://192.168.1.101:8080/images/upload/course/20180713/1531477404649.jpg
                 * courseType : YINGXIANGSUCAI
                 * commentnum : 7
                 * pageViewcount : 6496
                 * duration : null
                 * teacherName : null
                 * liveBeginTime : null
                 * liveEndTime : null
                 * liveStatus : null
                 * sequence : null
                 * isImageText : 0
                 * title : null
                 * watchTime : 72562
                 * isEnd : 0
                 * imageText : null
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
                private Object teacherName;
                private Object liveBeginTime;
                private Object liveEndTime;
                private Object liveStatus;
                private Object sequence;
                private int isImageText;
                private Object title;
                private int watchTime;
                private int isEnd;
                private Object imageText;


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

                public Object getTitle() {
                    return title;
                }

                public void setTitle(Object title) {
                    this.title = title;
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

                public Object getImageText() {
                    return imageText;
                }

                public void setImageText(Object imageText) {
                    this.imageText = imageText;
                }
            }

            @Override
            public String toString() {
                return "DataBean{" +
                        "id='" + id + '\'' +
                        ", userId=" + userId +
                        ", course=" + course +
                        ", watchTime='" + watchTime + '\'' +
                        ", playPosition=" + playPosition +
                        ", isSelect=" + isSelect +
                        '}';
            }
        }
    }
}
