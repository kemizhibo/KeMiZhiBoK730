package com.kemizhibo.kemizhibo.other.preparing_package_detail.bean;

import java.io.Serializable;

/**
 * Created by asus on 2018/8/6.
 */

public class MyPreviewBean implements Serializable{
    String host = "BCEDOC"; // 百度云传回的host
    String docId = "doc-gkjraanw4f89uu5"; // 百度云传回的docId
    String docType = "doc"; // 百度云传回的文档类型 doc/ppt/ppts等
    String token = "TOKEN"; // 百度云传回的token
    String thisDocDir = ""; // 指定为空串""表示在线浏览
    int totalPage = 3; // 总页数，必须准确填写 否则在离线浏览时会有问题
    String docTitle = "百度云文档服务";
    int startPage = 1; // 起始浏览页，最小值为1，请不要填入小于1的值

    public MyPreviewBean(String host, String docId, String docType, String token, String thisDocDir, int totalPage, String docTitle, int startPage) {
        this.host = host;
        this.docId = docId;
        this.docType = docType;
        this.token = token;
        this.thisDocDir = thisDocDir;
        this.totalPage = totalPage;
        this.docTitle = docTitle;
        this.startPage = startPage;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getThisDocDir() {
        return thisDocDir;
    }

    public void setThisDocDir(String thisDocDir) {
        this.thisDocDir = thisDocDir;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public String getDocTitle() {
        return docTitle;
    }

    public void setDocTitle(String docTitle) {
        this.docTitle = docTitle;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }
}
