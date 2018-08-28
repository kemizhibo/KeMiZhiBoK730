package com.kemizhibo.kemizhibo.other.preparing_center.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/7/24.
 */

public class PreparingCenterBean {

    /**
     * code : 0
     * message : success
     * content : {"page":1,"size":10,"total":2,"data":[{"courseId":2832,"courseName":"123t"},{"courseId":2822,"courseName":"123e","backImg":"http://39.155.221.165:8080/images/upload/focusing/20171219/1513648598579.jpg"}]}
     * otherData : {"courseId":2832,"courseName":"123t","subjectId":404,"semester":"1","materialEdition":"1581","moduleId":62,"docType":7,"createTime":"2018-06-20 16:33:02","prepareUpdateTime":"2018-06-23 17:15:21","gradeName":"一年级","materialName":"人教版","semesterName":"上学期"}
     */

    private int code;
    private String message;
    private ContentBean content;
    private OtherDataBean otherData;

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

    public OtherDataBean getOtherData() {
        return otherData;
    }

    public void setOtherData(OtherDataBean otherData) {
        this.otherData = otherData;
    }

    public static class ContentBean {
        /**
         * page : 1
         * size : 10
         * total : 2
         * data : [{"courseId":2832,"courseName":"123t"},{"courseId":2822,"courseName":"123e","backImg":"http://39.155.221.165:8080/images/upload/focusing/20171219/1513648598579.jpg"}]
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
             * courseName : 123t
             * backImg : http://39.155.221.165:8080/images/upload/focusing/20171219/1513648598579.jpg
             */

            private int courseId;
            private String courseName;
            private String logo;

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

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }
        }
    }

    public static class OtherDataBean {
        /**
         * courseId : 2832
         * courseName : 123t
         * subjectId : 404
         * semester : 1
         * materialEdition : 1581
         * moduleId : 62
         * docType : 7
         * createTime : 2018-06-20 16:33:02
         * prepareUpdateTime : 2018-06-23 17:15:21
         * gradeName : 一年级
         * materialName : 人教版
         * semesterName : 上学期
         */

        private int courseId;
        private String courseName;
        private int subjectId;
        private String semester;
        private String materialEdition;
        private int moduleId;
        private int docType;
        private String createTime;
        private String prepareUpdateTime;
        private String gradeName;
        private String materialName;
        private String semesterName;

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

        public String getSemester() {
            return semester;
        }

        public void setSemester(String semester) {
            this.semester = semester;
        }

        public String getMaterialEdition() {
            return materialEdition;
        }

        public void setMaterialEdition(String materialEdition) {
            this.materialEdition = materialEdition;
        }

        public int getModuleId() {
            return moduleId;
        }

        public void setModuleId(int moduleId) {
            this.moduleId = moduleId;
        }

        public int getDocType() {
            return docType;
        }

        public void setDocType(int docType) {
            this.docType = docType;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getPrepareUpdateTime() {
            return prepareUpdateTime;
        }

        public void setPrepareUpdateTime(String prepareUpdateTime) {
            this.prepareUpdateTime = prepareUpdateTime;
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
    }
}
