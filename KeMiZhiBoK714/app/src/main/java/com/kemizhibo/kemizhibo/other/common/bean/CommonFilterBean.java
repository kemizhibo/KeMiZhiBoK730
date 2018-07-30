package com.kemizhibo.kemizhibo.other.common.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/7/30.
 */

public class CommonFilterBean {

    /**
     * code : 0
     * message : success
     * content : {"material":[{"subjectId":1581,"subjectName":"人教版","type":"material"},{"subjectId":1591,"subjectName":"教科版","type":"material"}],"imgScience":[{"subjectId":651,"subjectName":"生命科学","type":"shortvideo"},{"subjectId":661,"subjectName":"物质科学","type":"shortvideo"},{"subjectId":671,"subjectName":"技术与工程","type":"shortvideo"},{"subjectId":681,"subjectName":"地球与宇宙","type":"shortvideo"},{"subjectId":2152,"subjectName":"其他","type":"shortvideo"}],"grade":[{"subjectId":404,"subjectName":"一年级","type":"course"},{"subjectId":405,"subjectName":"二年级","type":"course"},{"subjectId":406,"subjectName":"三年级","type":"course"},{"subjectId":407,"subjectName":"四年级","type":"course"},{"subjectId":408,"subjectName":"五年级","type":"course"},{"subjectId":409,"subjectName":"六年级","type":"course"}],"semester":[{"subjectId":1,"subjectName":"上学期","type":"semester"},{"subjectId":2,"subjectName":"下学期","type":"semester"}]}
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
        private List<MaterialBean> material;
        private List<ImgScienceBean> imgScience;
        private List<GradeBean> grade;
        private List<SemesterBean> semester;

        public List<MaterialBean> getMaterial() {
            return material;
        }

        public void setMaterial(List<MaterialBean> material) {
            this.material = material;
        }

        public List<ImgScienceBean> getImgScience() {
            return imgScience;
        }

        public void setImgScience(List<ImgScienceBean> imgScience) {
            this.imgScience = imgScience;
        }

        public List<GradeBean> getGrade() {
            return grade;
        }

        public void setGrade(List<GradeBean> grade) {
            this.grade = grade;
        }

        public List<SemesterBean> getSemester() {
            return semester;
        }

        public void setSemester(List<SemesterBean> semester) {
            this.semester = semester;
        }

        public static class MaterialBean {
            /**
             * subjectId : 1581
             * subjectName : 人教版
             * type : material
             */

            private int subjectId;
            private String subjectName;
            private String type;

            public int getSubjectId() {
                return subjectId;
            }

            public void setSubjectId(int subjectId) {
                this.subjectId = subjectId;
            }

            public String getSubjectName() {
                return subjectName;
            }

            public void setSubjectName(String subjectName) {
                this.subjectName = subjectName;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        public static class ImgScienceBean {
            /**
             * subjectId : 651
             * subjectName : 生命科学
             * type : shortvideo
             */

            private int subjectId;
            private String subjectName;
            private String type;

            public int getSubjectId() {
                return subjectId;
            }

            public void setSubjectId(int subjectId) {
                this.subjectId = subjectId;
            }

            public String getSubjectName() {
                return subjectName;
            }

            public void setSubjectName(String subjectName) {
                this.subjectName = subjectName;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        public static class GradeBean {
            /**
             * subjectId : 404
             * subjectName : 一年级
             * type : course
             */

            private int subjectId;
            private String subjectName;
            private String type;

            public int getSubjectId() {
                return subjectId;
            }

            public void setSubjectId(int subjectId) {
                this.subjectId = subjectId;
            }

            public String getSubjectName() {
                return subjectName;
            }

            public void setSubjectName(String subjectName) {
                this.subjectName = subjectName;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        public static class SemesterBean {
            /**
             * subjectId : 1
             * subjectName : 上学期
             * type : semester
             */

            private int subjectId;
            private String subjectName;
            private String type;

            public int getSubjectId() {
                return subjectId;
            }

            public void setSubjectId(int subjectId) {
                this.subjectId = subjectId;
            }

            public String getSubjectName() {
                return subjectName;
            }

            public void setSubjectName(String subjectName) {
                this.subjectName = subjectName;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
