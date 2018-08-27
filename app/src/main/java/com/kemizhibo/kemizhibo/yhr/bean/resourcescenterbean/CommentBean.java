package com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean;

import java.util.List;

/**
 * Author: yhr
 * Date: on 2018/7/4.
 * Describe:
 */

public class CommentBean {


    /**
     * code : 0
     * message : success
     * content : {"page":1,"size":10,"total":3,"data":[{"commentId":81650370,"otherId":1005262,"parentCommentId":0,"content":"66565565","addtime":"2018-08-26 16:15:55","praiseCount":0,"replyCount":0,"replyList":[],"userId":27782,"userHead":"http://192.168.1.101:8080/static/inxweb/img/defaultUser.png","praiseHistory":0,"page":1,"size":10,"type":null,"userName":"yanhaoran001","showName":"北京清华大学","flag":0},{"commentId":80003250,"otherId":1005262,"parentCommentId":0,"content":"大家分工及违法开始的覅哦hi","addtime":"2018-08-24 18:16:29","praiseCount":0,"replyCount":0,"replyList":[],"userId":3101,"userHead":"http://192.168.1.101:8080/images/upload/picImg/20180821/1534845800880.png","praiseHistory":0,"page":1,"size":10,"type":null,"userName":"xueyutong001","showName":"测试","flag":0},{"commentId":79912133,"otherId":1005262,"parentCommentId":0,"content":"哥和我他问我GETTG","addtime":"2018-08-24 18:16:13","praiseCount":0,"replyCount":0,"replyList":[],"userId":3101,"userHead":"http://192.168.1.101:8080/images/upload/picImg/20180821/1534845800880.png","praiseHistory":0,"page":1,"size":10,"type":null,"userName":"xueyutong001","showName":"测试","flag":0}]}
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
         * data : [{"commentId":81650370,"otherId":1005262,"parentCommentId":0,"content":"66565565","addtime":"2018-08-26 16:15:55","praiseCount":0,"replyCount":0,"replyList":[],"userId":27782,"userHead":"http://192.168.1.101:8080/static/inxweb/img/defaultUser.png","praiseHistory":0,"page":1,"size":10,"type":null,"userName":"yanhaoran001","showName":"北京清华大学","flag":0},{"commentId":80003250,"otherId":1005262,"parentCommentId":0,"content":"大家分工及违法开始的覅哦hi","addtime":"2018-08-24 18:16:29","praiseCount":0,"replyCount":0,"replyList":[],"userId":3101,"userHead":"http://192.168.1.101:8080/images/upload/picImg/20180821/1534845800880.png","praiseHistory":0,"page":1,"size":10,"type":null,"userName":"xueyutong001","showName":"测试","flag":0},{"commentId":79912133,"otherId":1005262,"parentCommentId":0,"content":"哥和我他问我GETTG","addtime":"2018-08-24 18:16:13","praiseCount":0,"replyCount":0,"replyList":[],"userId":3101,"userHead":"http://192.168.1.101:8080/images/upload/picImg/20180821/1534845800880.png","praiseHistory":0,"page":1,"size":10,"type":null,"userName":"xueyutong001","showName":"测试","flag":0}]
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
             * commentId : 81650370
             * otherId : 1005262
             * parentCommentId : 0
             * content : 66565565
             * addtime : 2018-08-26 16:15:55
             * praiseCount : 0
             * replyCount : 0
             * replyList : []
             * userId : 27782
             * userHead : http://192.168.1.101:8080/static/inxweb/img/defaultUser.png
             * praiseHistory : 0
             * page : 1
             * size : 10
             * type : null
             * userName : yanhaoran001
             * showName : 北京清华大学
             * flag : 0
             */

            private int commentId;
            private int otherId;
            private int parentCommentId;
            private String content;
            private String addtime;
            private int praiseCount;
            private int replyCount;
            private int userId;
            private String userHead;
            private int praiseHistory;
            private int page;
            private int size;
            private Object type;
            private String userName;
            private String showName;
            private int flag;
            private List<?> replyList;

            public int getCommentId() {
                return commentId;
            }

            public void setCommentId(int commentId) {
                this.commentId = commentId;
            }

            public int getOtherId() {
                return otherId;
            }

            public void setOtherId(int otherId) {
                this.otherId = otherId;
            }

            public int getParentCommentId() {
                return parentCommentId;
            }

            public void setParentCommentId(int parentCommentId) {
                this.parentCommentId = parentCommentId;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public int getPraiseCount() {
                return praiseCount;
            }

            public void setPraiseCount(int praiseCount) {
                this.praiseCount = praiseCount;
            }

            public int getReplyCount() {
                return replyCount;
            }

            public void setReplyCount(int replyCount) {
                this.replyCount = replyCount;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getUserHead() {
                return userHead;
            }

            public void setUserHead(String userHead) {
                this.userHead = userHead;
            }

            public int getPraiseHistory() {
                return praiseHistory;
            }

            public void setPraiseHistory(int praiseHistory) {
                this.praiseHistory = praiseHistory;
            }

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

            public Object getType() {
                return type;
            }

            public void setType(Object type) {
                this.type = type;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getShowName() {
                return showName;
            }

            public void setShowName(String showName) {
                this.showName = showName;
            }

            public int getFlag() {
                return flag;
            }

            public void setFlag(int flag) {
                this.flag = flag;
            }

            public List<?> getReplyList() {
                return replyList;
            }

            public void setReplyList(List<?> replyList) {
                this.replyList = replyList;
            }

            @Override
            public String toString() {
                return "DataBean{" +
                        "commentId=" + commentId +
                        ", otherId=" + otherId +
                        ", parentCommentId=" + parentCommentId +
                        ", content='" + content + '\'' +
                        ", addtime='" + addtime + '\'' +
                        ", praiseCount=" + praiseCount +
                        ", replyCount=" + replyCount +
                        ", userId=" + userId +
                        ", userHead='" + userHead + '\'' +
                        ", praiseHistory=" + praiseHistory +
                        ", page=" + page +
                        ", size=" + size +
                        ", type=" + type +
                        ", userName='" + userName + '\'' +
                        ", showName='" + showName + '\'' +
                        ", flag=" + flag +
                        ", replyList=" + replyList +
                        '}';
            }
        }
    }
}
