package com.kemizhibo.kemizhibo.other.preparing_package_detail.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/7/31.
 */

public class PreparingPackageDetailBean {

    /**
     * code : 0
     * message : success
     * content : {"other":[],"material":[{"moduleId":12,"courseId":2832,"docName":"观察鱼","docType":6,"dataSource":2,"subjectSort":2,"contentIds":"22","planIsFinish":1,"planFinishTime":"","prepareStatus":3,"createTime":"2018-06-30 16:14:02","updateTime":"2018-07-03 13:45:21","isRepeatAdd":1},{"moduleId":22,"courseId":2832,"userId":3422,"schoolId":132833755583741952,"docName":"观察鱼","docType":5,"dataSource":1,"subjectSort":2,"contentIds":"32","planIsFinish":1,"planFinishTime":"","prepareStatus":3,"createName":"","createTime":"2018-06-30 16:24:02","updateTime":"2018-07-03 12:45:21","updateName":"","isRepeatAdd":1},{"moduleId":32,"courseId":2832,"docName":"动物","docType":5,"dataSource":1,"subjectSort":2,"contentIds":"12","planFinishTime":"","prepareStatus":1,"createName":"","createTime":"2018-06-30 16:37:02","updateTime":"","updateName":"","isRepeatAdd":2}],"plan":[{"moduleId":62,"courseId":2832,"userId":3422,"schoolId":132833755583741952,"docName":"2018-06-30 16:34:023422","docType":7,"dataSource":1,"subjectSort":3,"contentIds":"11,12,13","planIsFinish":1,"planFinishTime":"","planIsDel":1,"prepareStatus":3,"isApplication":1,"createName":"douhuihui001","createTime":"2018-06-20 16:33:02","updateTime":"2018-06-23 17:15:21"},{"moduleId":72,"courseId":2832,"userId":3422,"schoolId":132833755583741952,"docName":"方案one","docType":3,"dataSource":1,"subjectSort":3,"contentIds":"12","planIsFinish":1,"prepareStatus":3,"createTime":"2018-06-20 16:34:02"}],"oneKey":[{"moduleId":2,"courseId":2832,"userId":3422,"schoolId":132833755583741952,"docName":"观察鱼-科米","docType":3,"dataSource":2,"subjectSort":1,"contentIds":"1","prepareStatus":1,"createTime":"2018-06-30 11:34:02","isRepeatAdd":1}]}
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
        private List<?> other;
        private List<MaterialBean> material;
        private List<PlanBean> plan;
        private List<OneKeyBean> oneKey;

        public List<?> getOther() {
            return other;
        }

        public void setOther(List<?> other) {
            this.other = other;
        }

        public List<MaterialBean> getMaterial() {
            return material;
        }

        public void setMaterial(List<MaterialBean> material) {
            this.material = material;
        }

        public List<PlanBean> getPlan() {
            return plan;
        }

        public void setPlan(List<PlanBean> plan) {
            this.plan = plan;
        }

        public List<OneKeyBean> getOneKey() {
            return oneKey;
        }

        public void setOneKey(List<OneKeyBean> oneKey) {
            this.oneKey = oneKey;
        }

        public static class MaterialBean {
            /**
             * moduleId : 12
             * courseId : 2832
             * docName : 观察鱼
             * docType : 6
             * dataSource : 2
             * subjectSort : 2
             * contentIds : 22
             * planIsFinish : 1
             * planFinishTime :
             * prepareStatus : 3
             * createTime : 2018-06-30 16:14:02
             * updateTime : 2018-07-03 13:45:21
             * isRepeatAdd : 1
             * userId : 3422
             * schoolId : 132833755583741952
             * createName :
             * updateName :
             */

            private int moduleId;
            private int courseId;
            private String docName;
            private int docType;
            private int dataSource;
            private int subjectSort;
            private String contentIds;
            private int planIsFinish;
            private String planFinishTime;
            private int prepareStatus;
            private String createTime;
            private String updateTime;
            private int isRepeatAdd;
            private int userId;
            private long schoolId;
            private String createName;
            private String updateName;

            public int getModuleId() {
                return moduleId;
            }

            public void setModuleId(int moduleId) {
                this.moduleId = moduleId;
            }

            public int getCourseId() {
                return courseId;
            }

            public void setCourseId(int courseId) {
                this.courseId = courseId;
            }

            public String getDocName() {
                return docName;
            }

            public void setDocName(String docName) {
                this.docName = docName;
            }

            public int getDocType() {
                return docType;
            }

            public void setDocType(int docType) {
                this.docType = docType;
            }

            public int getDataSource() {
                return dataSource;
            }

            public void setDataSource(int dataSource) {
                this.dataSource = dataSource;
            }

            public int getSubjectSort() {
                return subjectSort;
            }

            public void setSubjectSort(int subjectSort) {
                this.subjectSort = subjectSort;
            }

            public String getContentIds() {
                return contentIds;
            }

            public void setContentIds(String contentIds) {
                this.contentIds = contentIds;
            }

            public int getPlanIsFinish() {
                return planIsFinish;
            }

            public void setPlanIsFinish(int planIsFinish) {
                this.planIsFinish = planIsFinish;
            }

            public String getPlanFinishTime() {
                return planFinishTime;
            }

            public void setPlanFinishTime(String planFinishTime) {
                this.planFinishTime = planFinishTime;
            }

            public int getPrepareStatus() {
                return prepareStatus;
            }

            public void setPrepareStatus(int prepareStatus) {
                this.prepareStatus = prepareStatus;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public int getIsRepeatAdd() {
                return isRepeatAdd;
            }

            public void setIsRepeatAdd(int isRepeatAdd) {
                this.isRepeatAdd = isRepeatAdd;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public long getSchoolId() {
                return schoolId;
            }

            public void setSchoolId(long schoolId) {
                this.schoolId = schoolId;
            }

            public String getCreateName() {
                return createName;
            }

            public void setCreateName(String createName) {
                this.createName = createName;
            }

            public String getUpdateName() {
                return updateName;
            }

            public void setUpdateName(String updateName) {
                this.updateName = updateName;
            }
        }

        public static class PlanBean {
            /**
             * moduleId : 62
             * courseId : 2832
             * userId : 3422
             * schoolId : 132833755583741952
             * docName : 2018-06-30 16:34:023422
             * docType : 7
             * dataSource : 1
             * subjectSort : 3
             * contentIds : 11,12,13
             * planIsFinish : 1
             * planFinishTime :
             * planIsDel : 1
             * prepareStatus : 3
             * isApplication : 1
             * createName : douhuihui001
             * createTime : 2018-06-20 16:33:02
             * updateTime : 2018-06-23 17:15:21
             */

            private int moduleId;
            private int courseId;
            private int userId;
            private long schoolId;
            private String docName;
            private int docType;
            private int dataSource;
            private int subjectSort;
            private String contentIds;
            private int planIsFinish;
            private String planFinishTime;
            private int planIsDel;
            private int prepareStatus;
            private int isApplication;
            private String createName;
            private String createTime;
            private String updateTime;

            public int getModuleId() {
                return moduleId;
            }

            public void setModuleId(int moduleId) {
                this.moduleId = moduleId;
            }

            public int getCourseId() {
                return courseId;
            }

            public void setCourseId(int courseId) {
                this.courseId = courseId;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public long getSchoolId() {
                return schoolId;
            }

            public void setSchoolId(long schoolId) {
                this.schoolId = schoolId;
            }

            public String getDocName() {
                return docName;
            }

            public void setDocName(String docName) {
                this.docName = docName;
            }

            public int getDocType() {
                return docType;
            }

            public void setDocType(int docType) {
                this.docType = docType;
            }

            public int getDataSource() {
                return dataSource;
            }

            public void setDataSource(int dataSource) {
                this.dataSource = dataSource;
            }

            public int getSubjectSort() {
                return subjectSort;
            }

            public void setSubjectSort(int subjectSort) {
                this.subjectSort = subjectSort;
            }

            public String getContentIds() {
                return contentIds;
            }

            public void setContentIds(String contentIds) {
                this.contentIds = contentIds;
            }

            public int getPlanIsFinish() {
                return planIsFinish;
            }

            public void setPlanIsFinish(int planIsFinish) {
                this.planIsFinish = planIsFinish;
            }

            public String getPlanFinishTime() {
                return planFinishTime;
            }

            public void setPlanFinishTime(String planFinishTime) {
                this.planFinishTime = planFinishTime;
            }

            public int getPlanIsDel() {
                return planIsDel;
            }

            public void setPlanIsDel(int planIsDel) {
                this.planIsDel = planIsDel;
            }

            public int getPrepareStatus() {
                return prepareStatus;
            }

            public void setPrepareStatus(int prepareStatus) {
                this.prepareStatus = prepareStatus;
            }

            public int getIsApplication() {
                return isApplication;
            }

            public void setIsApplication(int isApplication) {
                this.isApplication = isApplication;
            }

            public String getCreateName() {
                return createName;
            }

            public void setCreateName(String createName) {
                this.createName = createName;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }
        }

        public static class OneKeyBean {
            /**
             * moduleId : 2
             * courseId : 2832
             * userId : 3422
             * schoolId : 132833755583741952
             * docName : 观察鱼-科米
             * docType : 3
             * dataSource : 2
             * subjectSort : 1
             * contentIds : 1
             * prepareStatus : 1
             * createTime : 2018-06-30 11:34:02
             * isRepeatAdd : 1
             */

            private int moduleId;
            private int courseId;
            private int userId;
            private long schoolId;
            private String docName;
            private int docType;
            private int dataSource;
            private int subjectSort;
            private String contentIds;
            private int prepareStatus;
            private String createTime;
            private int isRepeatAdd;

            public int getModuleId() {
                return moduleId;
            }

            public void setModuleId(int moduleId) {
                this.moduleId = moduleId;
            }

            public int getCourseId() {
                return courseId;
            }

            public void setCourseId(int courseId) {
                this.courseId = courseId;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public long getSchoolId() {
                return schoolId;
            }

            public void setSchoolId(long schoolId) {
                this.schoolId = schoolId;
            }

            public String getDocName() {
                return docName;
            }

            public void setDocName(String docName) {
                this.docName = docName;
            }

            public int getDocType() {
                return docType;
            }

            public void setDocType(int docType) {
                this.docType = docType;
            }

            public int getDataSource() {
                return dataSource;
            }

            public void setDataSource(int dataSource) {
                this.dataSource = dataSource;
            }

            public int getSubjectSort() {
                return subjectSort;
            }

            public void setSubjectSort(int subjectSort) {
                this.subjectSort = subjectSort;
            }

            public String getContentIds() {
                return contentIds;
            }

            public void setContentIds(String contentIds) {
                this.contentIds = contentIds;
            }

            public int getPrepareStatus() {
                return prepareStatus;
            }

            public void setPrepareStatus(int prepareStatus) {
                this.prepareStatus = prepareStatus;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getIsRepeatAdd() {
                return isRepeatAdd;
            }

            public void setIsRepeatAdd(int isRepeatAdd) {
                this.isRepeatAdd = isRepeatAdd;
            }
        }
    }
}
