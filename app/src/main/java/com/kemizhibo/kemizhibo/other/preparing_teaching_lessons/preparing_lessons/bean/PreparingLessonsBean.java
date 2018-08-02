package com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.preparing_lessons.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/8/1.
 */

public class PreparingLessonsBean {

    /**
     * code : 0
     * message : success
     * content : {"page":1,"size":10,"total":2,"data":[{"courseName":"123","semester":"2","moduleId":132,"docName":"123e-2","docType":5,"kpointId":4762,"prepareName":"douhuihui001","createTime":"2018-06-30 16:38:02","gradeName":"一年级","materialName":"教科版","semesterName":"下学期","planIsFinish":1},{"courseName":"123","semester":"2","moduleId":72,"docName":"方案one","docType":3,"docId":"doc-ifrn9c4zgzbtwut","prepareName":"douhuihui001","createTime":"2018-06-20 16:34:02","gradeName":"一年级","materialName":"教科版","semesterName":"下学期","planIsFinish":1}]}
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
         * total : 2
         * data : [{"courseName":"123","semester":"2","moduleId":132,"docName":"123e-2","docType":5,"kpointId":4762,"prepareName":"douhuihui001","createTime":"2018-06-30 16:38:02","gradeName":"一年级","materialName":"教科版","semesterName":"下学期","planIsFinish":1},{"courseName":"123","semester":"2","moduleId":72,"docName":"方案one","docType":3,"docId":"doc-ifrn9c4zgzbtwut","prepareName":"douhuihui001","createTime":"2018-06-20 16:34:02","gradeName":"一年级","materialName":"教科版","semesterName":"下学期","planIsFinish":1}]
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
             * courseName : 123
             * semester : 2
             * moduleId : 132
             * docName : 123e-2
             * docType : 5
             * kpointId : 4762
             * prepareName : douhuihui001
             * createTime : 2018-06-30 16:38:02
             * gradeName : 一年级
             * materialName : 教科版
             * semesterName : 下学期
             * planIsFinish : 1
             * docId : doc-ifrn9c4zgzbtwut
             */

            private String courseName;
            private String semester;
            private int moduleId;
            private String docName;
            private int docType;
            private int kpointId;
            private String prepareName;
            private String createTime;
            private String gradeName;
            private String materialName;
            private String semesterName;
            private int planIsFinish;
            private String docId;

            public String getCourseName() {
                return courseName;
            }

            public void setCourseName(String courseName) {
                this.courseName = courseName;
            }

            public String getSemester() {
                return semester;
            }

            public void setSemester(String semester) {
                this.semester = semester;
            }

            public int getModuleId() {
                return moduleId;
            }

            public void setModuleId(int moduleId) {
                this.moduleId = moduleId;
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

            public int getKpointId() {
                return kpointId;
            }

            public void setKpointId(int kpointId) {
                this.kpointId = kpointId;
            }

            public String getPrepareName() {
                return prepareName;
            }

            public void setPrepareName(String prepareName) {
                this.prepareName = prepareName;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getGradeName() {
                return gradeName;
            }

            public void setGradeName(String gradeName) {
                this.gradeName = gradeName;
            }

            public String getMaterialName() {
                return materialName;
            }

            public void setMaterialName(String materialName) {
                this.materialName = materialName;
            }

            public String getSemesterName() {
                return semesterName;
            }

            public void setSemesterName(String semesterName) {
                this.semesterName = semesterName;
            }

            public int getPlanIsFinish() {
                return planIsFinish;
            }

            public void setPlanIsFinish(int planIsFinish) {
                this.planIsFinish = planIsFinish;
            }

            public String getDocId() {
                return docId;
            }

            public void setDocId(String docId) {
                this.docId = docId;
            }
        }
    }
}
