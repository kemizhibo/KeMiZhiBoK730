package com.kemizhibo.kemizhibo.other.preparing_online.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/7/24.
 */

public class PreparingOnlineBean {

    /**
     * code : 0
     * message : success
     * content : {"page":1,"size":10,"total":3,"data":[{"courseId":2142,"courseName":"天文","logo":"http://192.168.1.101:8080/images/upload/course/20171220/1513760534238.png","gradeName":"一年级","materialName":"人教版","semesterName":"下学期","chapterName":"太阳系"},{"courseId":1004192,"courseName":"小小少年","logo":"http://192.168.1.101:8080/images/upload/course/20180612/1528795984756.jpg","gradeName":"一年级","materialName":"人教版","semesterName":"下学期","chapterName":"新建专业"},{"courseId":1004202,"courseName":"测试改版","logo":"http://192.168.1.101:8080/images/upload/course/20180613/1528856961085.jpg","gradeName":"一年级","materialName":"教科版","semesterName":"下学期","chapterName":"动物"}]}
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
         * total : 3
         * data : [{"courseId":2142,"courseName":"天文","logo":"http://192.168.1.101:8080/images/upload/course/20171220/1513760534238.png","gradeName":"一年级","materialName":"人教版","semesterName":"下学期","chapterName":"太阳系"},{"courseId":1004192,"courseName":"小小少年","logo":"http://192.168.1.101:8080/images/upload/course/20180612/1528795984756.jpg","gradeName":"一年级","materialName":"人教版","semesterName":"下学期","chapterName":"新建专业"},{"courseId":1004202,"courseName":"测试改版","logo":"http://192.168.1.101:8080/images/upload/course/20180613/1528856961085.jpg","gradeName":"一年级","materialName":"教科版","semesterName":"下学期","chapterName":"动物"}]
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
             * courseId : 2142
             * courseName : 天文
             * logo : http://192.168.1.101:8080/images/upload/course/20171220/1513760534238.png
             * gradeName : 一年级
             * materialName : 人教版
             * semesterName : 下学期
             * chapterName : 太阳系
             */

            private int courseId;
            private String courseName;
            private String logo;
            private String gradeName;
            private String materialName;
            private String semesterName;
            private String chapterName;

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

            public String getChapterName() {
                return chapterName;
            }

            public void setChapterName(String chapterName) {
                this.chapterName = chapterName;
            }
        }
    }
}
