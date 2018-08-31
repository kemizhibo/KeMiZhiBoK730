package com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean;

import java.util.List;

/**
 * Author: 闫浩然
 * Date: on 2018/8/29.
 * Describe:备授课所搜
 */

public class ForTeachSearchBean {

    /**
     * code : 0
     * message : success
     * content : {"page":1,"size":10,"total":0,"data":[{"courseId":2832,"courseName":"课程-2","isavaliable":null,"subjectId":null,"addTime":null,"sourcePrice":null,"currentPrice":null,"title":null,"context":null,"courseKpoint":null,"lessionNum":null,"logo":"http://39.155.221.165:8080/images/upload/course/20180720/1532051402801.jpg","updateTime":null,"limitCount":null,"bogusBuycount":null,"pageBuycount":null,"commentNum":null,"whetherToPay":null,"pageViewcount":null,"endTime":null,"loseType":null,"loseTime":null,"sellType":null,"liveStatus":null,"liveBeginTime":null,"liveEndTime":null,"nearestLiveBeginTime":null,"nearestLiveEndTime":null,"playTime":null,"videoUrl":null,"videoType":null,"studyPercent":null,"teacherList":null,"courseList":null,"courseKpointList":null,"courseStudyhistory":null,"memberCourseId":null,"location":null,"kpointLiveStatus":null,"knowledgeId":null,"knowledgeName":null,"praiseCount":null,"isApproval":null,"addLoginName":null,"procInstId":null,"imageText":null,"isImageText":null,"imageTextList":null,"semester":null,"chapter":null,"isRecommend":null,"courseSource":null,"courseEdition":null,"materialEdition":null,"watchType":null,"courseType":null,"sequence":0,"startTime":null,"roomEnvironment":null,"roomEnvironmentList":null,"moduleId":null,"docName":null,"docType":null,"dataSource":null,"userVideoLogo":null,"userUrl":null,"teacherName":null,"fileType":null,"docId":null,"kpointId":null,"prepareStatus":null,"prepareName":null,"prepareRealName":null,"showName":null,"userId":null,"isManagerData":null,"subjectSort":null,"introduce":null,"createTime":null,"prepareUpdateTime":null,"gradeName":null,"materialName":null,"semesterName":null,"chapterName":null,"planFinishTime":null,"planIsFinish":null,"recordId":null}]}
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
         * total : 0
         * data : [{"courseId":2832,"courseName":"课程-2","isavaliable":null,"subjectId":null,"addTime":null,"sourcePrice":null,"currentPrice":null,"title":null,"context":null,"courseKpoint":null,"lessionNum":null,"logo":"http://39.155.221.165:8080/images/upload/course/20180720/1532051402801.jpg","updateTime":null,"limitCount":null,"bogusBuycount":null,"pageBuycount":null,"commentNum":null,"whetherToPay":null,"pageViewcount":null,"endTime":null,"loseType":null,"loseTime":null,"sellType":null,"liveStatus":null,"liveBeginTime":null,"liveEndTime":null,"nearestLiveBeginTime":null,"nearestLiveEndTime":null,"playTime":null,"videoUrl":null,"videoType":null,"studyPercent":null,"teacherList":null,"courseList":null,"courseKpointList":null,"courseStudyhistory":null,"memberCourseId":null,"location":null,"kpointLiveStatus":null,"knowledgeId":null,"knowledgeName":null,"praiseCount":null,"isApproval":null,"addLoginName":null,"procInstId":null,"imageText":null,"isImageText":null,"imageTextList":null,"semester":null,"chapter":null,"isRecommend":null,"courseSource":null,"courseEdition":null,"materialEdition":null,"watchType":null,"courseType":null,"sequence":0,"startTime":null,"roomEnvironment":null,"roomEnvironmentList":null,"moduleId":null,"docName":null,"docType":null,"dataSource":null,"userVideoLogo":null,"userUrl":null,"teacherName":null,"fileType":null,"docId":null,"kpointId":null,"prepareStatus":null,"prepareName":null,"prepareRealName":null,"showName":null,"userId":null,"isManagerData":null,"subjectSort":null,"introduce":null,"createTime":null,"prepareUpdateTime":null,"gradeName":null,"materialName":null,"semesterName":null,"chapterName":null,"planFinishTime":null,"planIsFinish":null,"recordId":null}]
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
             * courseId : 2832
             * courseName : 课程-2
             * isavaliable : null
             * subjectId : null
             * addTime : null
             * sourcePrice : null
             * currentPrice : null
             * title : null
             * context : null
             * courseKpoint : null
             * lessionNum : null
             * logo : http://39.155.221.165:8080/images/upload/course/20180720/1532051402801.jpg
             * updateTime : null
             * limitCount : null
             * bogusBuycount : null
             * pageBuycount : null
             * commentNum : null
             * whetherToPay : null
             * pageViewcount : null
             * endTime : null
             * loseType : null
             * loseTime : null
             * sellType : null
             * liveStatus : null
             * liveBeginTime : null
             * liveEndTime : null
             * nearestLiveBeginTime : null
             * nearestLiveEndTime : null
             * playTime : null
             * videoUrl : null
             * videoType : null
             * studyPercent : null
             * teacherList : null
             * courseList : null
             * courseKpointList : null
             * courseStudyhistory : null
             * memberCourseId : null
             * location : null
             * kpointLiveStatus : null
             * knowledgeId : null
             * knowledgeName : null
             * praiseCount : null
             * isApproval : null
             * addLoginName : null
             * procInstId : null
             * imageText : null
             * isImageText : null
             * imageTextList : null
             * semester : null
             * chapter : null
             * isRecommend : null
             * courseSource : null
             * courseEdition : null
             * materialEdition : null
             * watchType : null
             * courseType : null
             * sequence : 0
             * startTime : null
             * roomEnvironment : null
             * roomEnvironmentList : null
             * moduleId : null
             * docName : null
             * docType : null
             * dataSource : null
             * userVideoLogo : null
             * userUrl : null
             * teacherName : null
             * fileType : null
             * docId : null
             * kpointId : null
             * prepareStatus : null
             * prepareName : null
             * prepareRealName : null
             * showName : null
             * userId : null
             * isManagerData : null
             * subjectSort : null
             * introduce : null
             * createTime : null
             * prepareUpdateTime : null
             * gradeName : null
             * materialName : null
             * semesterName : null
             * chapterName : null
             * planFinishTime : null
             * planIsFinish : null
             * recordId : null
             */

            private int courseId;
            private String courseName;
            private Object isavaliable;
            private Object subjectId;
            private Object addTime;
            private Object sourcePrice;
            private Object currentPrice;
            private Object title;
            private Object context;
            private Object courseKpoint;
            private Object lessionNum;
            private String logo;
            private Object updateTime;
            private Object limitCount;
            private Object bogusBuycount;
            private Object pageBuycount;
            private Object commentNum;
            private Object whetherToPay;
            private Object pageViewcount;
            private Object endTime;
            private Object loseType;
            private Object loseTime;
            private Object sellType;
            private Object liveStatus;
            private Object liveBeginTime;
            private Object liveEndTime;
            private Object nearestLiveBeginTime;
            private Object nearestLiveEndTime;
            private Object playTime;
            private Object videoUrl;
            private Object videoType;
            private Object studyPercent;
            private Object teacherList;
            private Object courseList;
            private Object courseKpointList;
            private Object courseStudyhistory;
            private Object memberCourseId;
            private Object location;
            private Object kpointLiveStatus;
            private Object knowledgeId;
            private Object knowledgeName;
            private Object praiseCount;
            private Object isApproval;
            private Object addLoginName;
            private Object procInstId;
            private Object imageText;
            private Object isImageText;
            private Object imageTextList;
            private Object semester;
            private Object chapter;
            private Object isRecommend;
            private Object courseSource;
            private Object courseEdition;
            private Object materialEdition;
            private Object watchType;
            private Object courseType;
            private int sequence;
            private Object startTime;
            private Object roomEnvironment;
            private Object roomEnvironmentList;
            private Object moduleId;
            private Object docName;
            private Object docType;
            private Object dataSource;
            private Object userVideoLogo;
            private Object userUrl;
            private Object teacherName;
            private Object fileType;
            private Object docId;
            private Object kpointId;
            private Object prepareStatus;
            private Object prepareName;
            private Object prepareRealName;
            private Object showName;
            private Object userId;
            private Object isManagerData;
            private Object subjectSort;
            private Object introduce;
            private Object createTime;
            private Object prepareUpdateTime;
            private Object gradeName;
            private Object materialName;
            private Object semesterName;
            private Object chapterName;
            private Object planFinishTime;
            private Object planIsFinish;
            private Object recordId;

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

            public Object getIsavaliable() {
                return isavaliable;
            }

            public void setIsavaliable(Object isavaliable) {
                this.isavaliable = isavaliable;
            }

            public Object getSubjectId() {
                return subjectId;
            }

            public void setSubjectId(Object subjectId) {
                this.subjectId = subjectId;
            }

            public Object getAddTime() {
                return addTime;
            }

            public void setAddTime(Object addTime) {
                this.addTime = addTime;
            }

            public Object getSourcePrice() {
                return sourcePrice;
            }

            public void setSourcePrice(Object sourcePrice) {
                this.sourcePrice = sourcePrice;
            }

            public Object getCurrentPrice() {
                return currentPrice;
            }

            public void setCurrentPrice(Object currentPrice) {
                this.currentPrice = currentPrice;
            }

            public Object getTitle() {
                return title;
            }

            public void setTitle(Object title) {
                this.title = title;
            }

            public Object getContext() {
                return context;
            }

            public void setContext(Object context) {
                this.context = context;
            }

            public Object getCourseKpoint() {
                return courseKpoint;
            }

            public void setCourseKpoint(Object courseKpoint) {
                this.courseKpoint = courseKpoint;
            }

            public Object getLessionNum() {
                return lessionNum;
            }

            public void setLessionNum(Object lessionNum) {
                this.lessionNum = lessionNum;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }

            public Object getLimitCount() {
                return limitCount;
            }

            public void setLimitCount(Object limitCount) {
                this.limitCount = limitCount;
            }

            public Object getBogusBuycount() {
                return bogusBuycount;
            }

            public void setBogusBuycount(Object bogusBuycount) {
                this.bogusBuycount = bogusBuycount;
            }

            public Object getPageBuycount() {
                return pageBuycount;
            }

            public void setPageBuycount(Object pageBuycount) {
                this.pageBuycount = pageBuycount;
            }

            public Object getCommentNum() {
                return commentNum;
            }

            public void setCommentNum(Object commentNum) {
                this.commentNum = commentNum;
            }

            public Object getWhetherToPay() {
                return whetherToPay;
            }

            public void setWhetherToPay(Object whetherToPay) {
                this.whetherToPay = whetherToPay;
            }

            public Object getPageViewcount() {
                return pageViewcount;
            }

            public void setPageViewcount(Object pageViewcount) {
                this.pageViewcount = pageViewcount;
            }

            public Object getEndTime() {
                return endTime;
            }

            public void setEndTime(Object endTime) {
                this.endTime = endTime;
            }

            public Object getLoseType() {
                return loseType;
            }

            public void setLoseType(Object loseType) {
                this.loseType = loseType;
            }

            public Object getLoseTime() {
                return loseTime;
            }

            public void setLoseTime(Object loseTime) {
                this.loseTime = loseTime;
            }

            public Object getSellType() {
                return sellType;
            }

            public void setSellType(Object sellType) {
                this.sellType = sellType;
            }

            public Object getLiveStatus() {
                return liveStatus;
            }

            public void setLiveStatus(Object liveStatus) {
                this.liveStatus = liveStatus;
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

            public Object getNearestLiveBeginTime() {
                return nearestLiveBeginTime;
            }

            public void setNearestLiveBeginTime(Object nearestLiveBeginTime) {
                this.nearestLiveBeginTime = nearestLiveBeginTime;
            }

            public Object getNearestLiveEndTime() {
                return nearestLiveEndTime;
            }

            public void setNearestLiveEndTime(Object nearestLiveEndTime) {
                this.nearestLiveEndTime = nearestLiveEndTime;
            }

            public Object getPlayTime() {
                return playTime;
            }

            public void setPlayTime(Object playTime) {
                this.playTime = playTime;
            }

            public Object getVideoUrl() {
                return videoUrl;
            }

            public void setVideoUrl(Object videoUrl) {
                this.videoUrl = videoUrl;
            }

            public Object getVideoType() {
                return videoType;
            }

            public void setVideoType(Object videoType) {
                this.videoType = videoType;
            }

            public Object getStudyPercent() {
                return studyPercent;
            }

            public void setStudyPercent(Object studyPercent) {
                this.studyPercent = studyPercent;
            }

            public Object getTeacherList() {
                return teacherList;
            }

            public void setTeacherList(Object teacherList) {
                this.teacherList = teacherList;
            }

            public Object getCourseList() {
                return courseList;
            }

            public void setCourseList(Object courseList) {
                this.courseList = courseList;
            }

            public Object getCourseKpointList() {
                return courseKpointList;
            }

            public void setCourseKpointList(Object courseKpointList) {
                this.courseKpointList = courseKpointList;
            }

            public Object getCourseStudyhistory() {
                return courseStudyhistory;
            }

            public void setCourseStudyhistory(Object courseStudyhistory) {
                this.courseStudyhistory = courseStudyhistory;
            }

            public Object getMemberCourseId() {
                return memberCourseId;
            }

            public void setMemberCourseId(Object memberCourseId) {
                this.memberCourseId = memberCourseId;
            }

            public Object getLocation() {
                return location;
            }

            public void setLocation(Object location) {
                this.location = location;
            }

            public Object getKpointLiveStatus() {
                return kpointLiveStatus;
            }

            public void setKpointLiveStatus(Object kpointLiveStatus) {
                this.kpointLiveStatus = kpointLiveStatus;
            }

            public Object getKnowledgeId() {
                return knowledgeId;
            }

            public void setKnowledgeId(Object knowledgeId) {
                this.knowledgeId = knowledgeId;
            }

            public Object getKnowledgeName() {
                return knowledgeName;
            }

            public void setKnowledgeName(Object knowledgeName) {
                this.knowledgeName = knowledgeName;
            }

            public Object getPraiseCount() {
                return praiseCount;
            }

            public void setPraiseCount(Object praiseCount) {
                this.praiseCount = praiseCount;
            }

            public Object getIsApproval() {
                return isApproval;
            }

            public void setIsApproval(Object isApproval) {
                this.isApproval = isApproval;
            }

            public Object getAddLoginName() {
                return addLoginName;
            }

            public void setAddLoginName(Object addLoginName) {
                this.addLoginName = addLoginName;
            }

            public Object getProcInstId() {
                return procInstId;
            }

            public void setProcInstId(Object procInstId) {
                this.procInstId = procInstId;
            }

            public Object getImageText() {
                return imageText;
            }

            public void setImageText(Object imageText) {
                this.imageText = imageText;
            }

            public Object getIsImageText() {
                return isImageText;
            }

            public void setIsImageText(Object isImageText) {
                this.isImageText = isImageText;
            }

            public Object getImageTextList() {
                return imageTextList;
            }

            public void setImageTextList(Object imageTextList) {
                this.imageTextList = imageTextList;
            }

            public Object getSemester() {
                return semester;
            }

            public void setSemester(Object semester) {
                this.semester = semester;
            }

            public Object getChapter() {
                return chapter;
            }

            public void setChapter(Object chapter) {
                this.chapter = chapter;
            }

            public Object getIsRecommend() {
                return isRecommend;
            }

            public void setIsRecommend(Object isRecommend) {
                this.isRecommend = isRecommend;
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

            public Object getMaterialEdition() {
                return materialEdition;
            }

            public void setMaterialEdition(Object materialEdition) {
                this.materialEdition = materialEdition;
            }

            public Object getWatchType() {
                return watchType;
            }

            public void setWatchType(Object watchType) {
                this.watchType = watchType;
            }

            public Object getCourseType() {
                return courseType;
            }

            public void setCourseType(Object courseType) {
                this.courseType = courseType;
            }

            public int getSequence() {
                return sequence;
            }

            public void setSequence(int sequence) {
                this.sequence = sequence;
            }

            public Object getStartTime() {
                return startTime;
            }

            public void setStartTime(Object startTime) {
                this.startTime = startTime;
            }

            public Object getRoomEnvironment() {
                return roomEnvironment;
            }

            public void setRoomEnvironment(Object roomEnvironment) {
                this.roomEnvironment = roomEnvironment;
            }

            public Object getRoomEnvironmentList() {
                return roomEnvironmentList;
            }

            public void setRoomEnvironmentList(Object roomEnvironmentList) {
                this.roomEnvironmentList = roomEnvironmentList;
            }

            public Object getModuleId() {
                return moduleId;
            }

            public void setModuleId(Object moduleId) {
                this.moduleId = moduleId;
            }

            public Object getDocName() {
                return docName;
            }

            public void setDocName(Object docName) {
                this.docName = docName;
            }

            public Object getDocType() {
                return docType;
            }

            public void setDocType(Object docType) {
                this.docType = docType;
            }

            public Object getDataSource() {
                return dataSource;
            }

            public void setDataSource(Object dataSource) {
                this.dataSource = dataSource;
            }

            public Object getUserVideoLogo() {
                return userVideoLogo;
            }

            public void setUserVideoLogo(Object userVideoLogo) {
                this.userVideoLogo = userVideoLogo;
            }

            public Object getUserUrl() {
                return userUrl;
            }

            public void setUserUrl(Object userUrl) {
                this.userUrl = userUrl;
            }

            public Object getTeacherName() {
                return teacherName;
            }

            public void setTeacherName(Object teacherName) {
                this.teacherName = teacherName;
            }

            public Object getFileType() {
                return fileType;
            }

            public void setFileType(Object fileType) {
                this.fileType = fileType;
            }

            public Object getDocId() {
                return docId;
            }

            public void setDocId(Object docId) {
                this.docId = docId;
            }

            public Object getKpointId() {
                return kpointId;
            }

            public void setKpointId(Object kpointId) {
                this.kpointId = kpointId;
            }

            public Object getPrepareStatus() {
                return prepareStatus;
            }

            public void setPrepareStatus(Object prepareStatus) {
                this.prepareStatus = prepareStatus;
            }

            public Object getPrepareName() {
                return prepareName;
            }

            public void setPrepareName(Object prepareName) {
                this.prepareName = prepareName;
            }

            public Object getPrepareRealName() {
                return prepareRealName;
            }

            public void setPrepareRealName(Object prepareRealName) {
                this.prepareRealName = prepareRealName;
            }

            public Object getShowName() {
                return showName;
            }

            public void setShowName(Object showName) {
                this.showName = showName;
            }

            public Object getUserId() {
                return userId;
            }

            public void setUserId(Object userId) {
                this.userId = userId;
            }

            public Object getIsManagerData() {
                return isManagerData;
            }

            public void setIsManagerData(Object isManagerData) {
                this.isManagerData = isManagerData;
            }

            public Object getSubjectSort() {
                return subjectSort;
            }

            public void setSubjectSort(Object subjectSort) {
                this.subjectSort = subjectSort;
            }

            public Object getIntroduce() {
                return introduce;
            }

            public void setIntroduce(Object introduce) {
                this.introduce = introduce;
            }

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }

            public Object getPrepareUpdateTime() {
                return prepareUpdateTime;
            }

            public void setPrepareUpdateTime(Object prepareUpdateTime) {
                this.prepareUpdateTime = prepareUpdateTime;
            }

            public Object getGradeName() {
                return gradeName;
            }

            public void setGradeName(Object gradeName) {
                this.gradeName = gradeName;
            }

            public Object getMaterialName() {
                return materialName;
            }

            public void setMaterialName(Object materialName) {
                this.materialName = materialName;
            }

            public Object getSemesterName() {
                return semesterName;
            }

            public void setSemesterName(Object semesterName) {
                this.semesterName = semesterName;
            }

            public Object getChapterName() {
                return chapterName;
            }

            public void setChapterName(Object chapterName) {
                this.chapterName = chapterName;
            }

            public Object getPlanFinishTime() {
                return planFinishTime;
            }

            public void setPlanFinishTime(Object planFinishTime) {
                this.planFinishTime = planFinishTime;
            }

            public Object getPlanIsFinish() {
                return planIsFinish;
            }

            public void setPlanIsFinish(Object planIsFinish) {
                this.planIsFinish = planIsFinish;
            }

            public Object getRecordId() {
                return recordId;
            }

            public void setRecordId(Object recordId) {
                this.recordId = recordId;
            }
        }
    }
}
