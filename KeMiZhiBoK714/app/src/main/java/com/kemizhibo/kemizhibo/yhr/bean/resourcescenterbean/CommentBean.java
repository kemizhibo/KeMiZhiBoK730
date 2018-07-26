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
     * content : {"page":1,"size":10,"total":4,"data":[{"commentId":5837261,"otherId":2222,"parentCommentId":0,"content":"反反复复","addtime":"2018-05-10 11:32:15","praiseCount":0,"replyCount":0,"replyList":[],"userId":2701,"userHead":"http://192.168.1.101:8080/images/upload/picImg/20180402/1522662157587.png","praiseHistory":0,"page":1,"size":10,"type":null,"userName":"liuxuechao001","showName":"研发部3","flag":0},{"commentId":5664860,"otherId":2222,"parentCommentId":0,"content":"嘎嘎嘎","addtime":"2018-05-10 11:31:47","praiseCount":0,"replyCount":0,"replyList":[],"userId":2701,"userHead":"http://192.168.1.101:8080/images/upload/picImg/20180402/1522662157587.png","praiseHistory":0,"page":1,"size":10,"type":null,"userName":"liuxuechao001","showName":"研发部3","flag":0},{"commentId":84059933,"otherId":2222,"parentCommentId":0,"content":"<img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/f9/hot_halfstar_thumb.png\" height=\"22\" width=\"22\" /><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/70/88_thumb.gif\" height=\"22\" width=\"22\" /><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/59/huba02_hubatan_thumb.png\" height=\"22\" width=\"22\" />","addtime":"2018-04-09 20:00:45","praiseCount":0,"replyCount":1,"replyList":[{"commentId":5510455,"otherId":2222,"parentCommentId":84059933,"content":"哈哈哈","addtime":"2018-05-10 11:31:41","praiseCount":0,"replyCount":0,"replyList":[],"userId":2701,"userHead":"http://192.168.1.101:8080/images/upload/picImg/20180402/1522662157587.png","praiseHistory":0,"page":1,"size":10,"type":null,"userName":"liuxuechao001","showName":"研发部3","flag":0}],"userId":3462,"userHead":"http://192.168.1.101:8080/images/upload/inxedu/20180522/1526973658158.png","praiseHistory":0,"page":1,"size":10,"type":null,"userName":null,"showName":"135****0853","flag":0},{"commentId":84070882,"otherId":2222,"parentCommentId":0,"content":"<img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/e9/sk_thumb.gif\" height=\"22\" width=\"22\" />","addtime":"2018-04-09 19:58:23","praiseCount":0,"replyCount":0,"replyList":[],"userId":3462,"userHead":"http://192.168.1.101:8080/images/upload/inxedu/20180522/1526973658158.png","praiseHistory":0,"page":1,"size":10,"type":null,"userName":null,"showName":"135****0853","flag":0}]}
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
         * total : 4
         * data : [{"commentId":5837261,"otherId":2222,"parentCommentId":0,"content":"反反复复","addtime":"2018-05-10 11:32:15","praiseCount":0,"replyCount":0,"replyList":[],"userId":2701,"userHead":"http://192.168.1.101:8080/images/upload/picImg/20180402/1522662157587.png","praiseHistory":0,"page":1,"size":10,"type":null,"userName":"liuxuechao001","showName":"研发部3","flag":0},{"commentId":5664860,"otherId":2222,"parentCommentId":0,"content":"嘎嘎嘎","addtime":"2018-05-10 11:31:47","praiseCount":0,"replyCount":0,"replyList":[],"userId":2701,"userHead":"http://192.168.1.101:8080/images/upload/picImg/20180402/1522662157587.png","praiseHistory":0,"page":1,"size":10,"type":null,"userName":"liuxuechao001","showName":"研发部3","flag":0},{"commentId":84059933,"otherId":2222,"parentCommentId":0,"content":"<img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/f9/hot_halfstar_thumb.png\" height=\"22\" width=\"22\" /><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/70/88_thumb.gif\" height=\"22\" width=\"22\" /><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/59/huba02_hubatan_thumb.png\" height=\"22\" width=\"22\" />","addtime":"2018-04-09 20:00:45","praiseCount":0,"replyCount":1,"replyList":[{"commentId":5510455,"otherId":2222,"parentCommentId":84059933,"content":"哈哈哈","addtime":"2018-05-10 11:31:41","praiseCount":0,"replyCount":0,"replyList":[],"userId":2701,"userHead":"http://192.168.1.101:8080/images/upload/picImg/20180402/1522662157587.png","praiseHistory":0,"page":1,"size":10,"type":null,"userName":"liuxuechao001","showName":"研发部3","flag":0}],"userId":3462,"userHead":"http://192.168.1.101:8080/images/upload/inxedu/20180522/1526973658158.png","praiseHistory":0,"page":1,"size":10,"type":null,"userName":null,"showName":"135****0853","flag":0},{"commentId":84070882,"otherId":2222,"parentCommentId":0,"content":"<img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/e9/sk_thumb.gif\" height=\"22\" width=\"22\" />","addtime":"2018-04-09 19:58:23","praiseCount":0,"replyCount":0,"replyList":[],"userId":3462,"userHead":"http://192.168.1.101:8080/images/upload/inxedu/20180522/1526973658158.png","praiseHistory":0,"page":1,"size":10,"type":null,"userName":null,"showName":"135****0853","flag":0}]
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
             * commentId : 5837261
             * otherId : 2222
             * parentCommentId : 0
             * content : 反反复复
             * addtime : 2018-05-10 11:32:15
             * praiseCount : 0
             * replyCount : 0
             * replyList : []
             * userId : 2701
             * userHead : http://192.168.1.101:8080/images/upload/picImg/20180402/1522662157587.png
             * praiseHistory : 0
             * page : 1
             * size : 10
             * type : null
             * userName : liuxuechao001
             * showName : 研发部3
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
        }
    }
}
