package com.kemizhibo.kemizhibo.other.preparing_package_detail.bean;

import java.util.List;

/**
 * Created by asus on 2018/8/2.
 */

public class PreparingDocBean {

    /**
     * code : 0
     * message : null
     * content : {"docName":"2018-06-30 16:34:023422","planIsFinish":1,"prepareStatus":1,"planJson":"{ratio:{width:1600,height:900}}","online":[{"onlineId":null,"moduleId":null,"pageNum":1,"createTime":null,"updateTime":null,"createName":null,"updateName":null,"contentJson":null},{"onlineId":null,"moduleId":null,"pageNum":2,"createTime":null,"updateTime":null,"createName":null,"updateName":null,"contentJson":"{ratio:{width:1600,height:900}}"},{"onlineId":null,"moduleId":null,"pageNum":3,"createTime":null,"updateTime":null,"createName":null,"updateName":null,"contentJson":"{id:1001,url:\"/img.png\"}"}]}
     * otherData : 7
     */

    private int code;
    private Object message;
    private ContentBean content;
    private int otherData;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public int getOtherData() {
        return otherData;
    }

    public void setOtherData(int otherData) {
        this.otherData = otherData;
    }

    public static class ContentBean {
        /**
         * docName : 2018-06-30 16:34:023422
         * planIsFinish : 1
         * prepareStatus : 1
         * planJson : {ratio:{width:1600,height:900}}
         * online : [{"onlineId":null,"moduleId":null,"pageNum":1,"createTime":null,"updateTime":null,"createName":null,"updateName":null,"contentJson":null},{"onlineId":null,"moduleId":null,"pageNum":2,"createTime":null,"updateTime":null,"createName":null,"updateName":null,"contentJson":"{ratio:{width:1600,height:900}}"},{"onlineId":null,"moduleId":null,"pageNum":3,"createTime":null,"updateTime":null,"createName":null,"updateName":null,"contentJson":"{id:1001,url:\"/img.png\"}"}]
         */

        private String docName;
        private int planIsFinish;
        private int prepareStatus;
        private String planJson;
        private List<OnlineBean> online;

        public String getDocName() {
            return docName;
        }

        public void setDocName(String docName) {
            this.docName = docName;
        }

        public int getPlanIsFinish() {
            return planIsFinish;
        }

        public void setPlanIsFinish(int planIsFinish) {
            this.planIsFinish = planIsFinish;
        }

        public int getPrepareStatus() {
            return prepareStatus;
        }

        public void setPrepareStatus(int prepareStatus) {
            this.prepareStatus = prepareStatus;
        }

        public String getPlanJson() {
            return planJson;
        }

        public void setPlanJson(String planJson) {
            this.planJson = planJson;
        }

        public List<OnlineBean> getOnline() {
            return online;
        }

        public void setOnline(List<OnlineBean> online) {
            this.online = online;
        }

        public static class OnlineBean {
            /**
             * onlineId : null
             * moduleId : null
             * pageNum : 1
             * createTime : null
             * updateTime : null
             * createName : null
             * updateName : null
             * contentJson : null
             */

            private Object onlineId;
            private Object moduleId;
            private int pageNum;
            private Object createTime;
            private Object updateTime;
            private Object createName;
            private Object updateName;
            private Object contentJson;

            public Object getOnlineId() {
                return onlineId;
            }

            public void setOnlineId(Object onlineId) {
                this.onlineId = onlineId;
            }

            public Object getModuleId() {
                return moduleId;
            }

            public void setModuleId(Object moduleId) {
                this.moduleId = moduleId;
            }

            public int getPageNum() {
                return pageNum;
            }

            public void setPageNum(int pageNum) {
                this.pageNum = pageNum;
            }

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }

            public Object getCreateName() {
                return createName;
            }

            public void setCreateName(Object createName) {
                this.createName = createName;
            }

            public Object getUpdateName() {
                return updateName;
            }

            public void setUpdateName(Object updateName) {
                this.updateName = updateName;
            }

            public Object getContentJson() {
                return contentJson;
            }

            public void setContentJson(Object contentJson) {
                this.contentJson = contentJson;
            }
        }
    }
}
