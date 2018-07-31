package com.kemizhibo.kemizhibo.yhr.bean.personcenterbean;

/**
 * Author: 闫浩然
 * Date: on 2018/7/24.
 * Describe:获取用户信息
 */

public class GetUserBean {


    /**
     * flag : true
     * code : 0
     * message : 成功
     * content : {"userId":27782,"mobile":"17600993868","email":"17600993868@163.com","password":"","userName":"yanhaoran001","showName":"","sex":0,"age":0,"createTime":null,"isavalible":1,"picImg":"http://192.168.1.101:8080/static/inxweb/img/defaultUser.png","bannerUrl":null,"msgNum":0,"sysMsgNum":0,"lastSystemTime":null,"loginAccount":null,"registerFrom":"register","invitationCode":null,"loginTimeStamp":0,"courseName":null,"displayName":"yanhaoran001","userProfileList":null,"forTeacherStatus":null,"mobileNotNull":null,"emailNotNull":null,"realName":"2222","idCardNo":"140521199303169753","province":0,"city":0,"area":0,"school":"","subject":"2222","paDogToken":"","userType":1,"parentId":0,"roleId":0,"enterpriseId":0,"period":"","grade":"2222","address":"2222","lastLoginIp":"127.0.0.1","lastLoginTime":"2018-07-26 19:18:55","firstLoginFlag":0,"schoolSystem":null,"lectureTruthPicImg":"/static/inxweb/img/defaultUser.png","lectureTruthShowName":"","lectureTruthSchoolName":"","lectureTruthProfile":"","lectureTruthPeriod":"","lectureTruthGrade":"","lectureTruthSubject":"","lectureTruthSysUserId":0,"lectureTruthIsAudit":0,"lectureTruthAuditTime":null,"lectureTruthAuditRemark":null,"testUserType":null}
     * page : null
     */

    private boolean flag;
    private int code;
    private String message;
    private ContentBean content;
    private Object page;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

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

    public Object getPage() {
        return page;
    }

    public void setPage(Object page) {
        this.page = page;
    }

    public static class ContentBean {
        /**
         * userId : 27782
         * mobile : 17600993868
         * email : 17600993868@163.com
         * password :
         * userName : yanhaoran001
         * showName :
         * sex : 0
         * age : 0
         * createTime : null
         * isavalible : 1
         * picImg : http://192.168.1.101:8080/static/inxweb/img/defaultUser.png
         * bannerUrl : null
         * msgNum : 0
         * sysMsgNum : 0
         * lastSystemTime : null
         * loginAccount : null
         * registerFrom : register
         * invitationCode : null
         * loginTimeStamp : 0
         * courseName : null
         * displayName : yanhaoran001
         * userProfileList : null
         * forTeacherStatus : null
         * mobileNotNull : null
         * emailNotNull : null
         * realName : 2222
         * idCardNo : 140521199303169753
         * province : 0
         * city : 0
         * area : 0
         * school :
         * subject : 2222
         * paDogToken :
         * userType : 1
         * parentId : 0
         * roleId : 0
         * enterpriseId : 0
         * period :
         * grade : 2222
         * address : 2222
         * lastLoginIp : 127.0.0.1
         * lastLoginTime : 2018-07-26 19:18:55
         * firstLoginFlag : 0
         * schoolSystem : null
         * lectureTruthPicImg : /static/inxweb/img/defaultUser.png
         * lectureTruthShowName :
         * lectureTruthSchoolName :
         * lectureTruthProfile :
         * lectureTruthPeriod :
         * lectureTruthGrade :
         * lectureTruthSubject :
         * lectureTruthSysUserId : 0
         * lectureTruthIsAudit : 0
         * lectureTruthAuditTime : null
         * lectureTruthAuditRemark : null
         * testUserType : null
         */

        private int userId;
        private String mobile;
        private String email;
        private String password;
        private String userName;
        private String showName;
        private int sex;
        private int age;
        private Object createTime;
        private int isavalible;
        private String picImg;
        private Object bannerUrl;
        private int msgNum;
        private int sysMsgNum;
        private Object lastSystemTime;
        private Object loginAccount;
        private String registerFrom;
        private Object invitationCode;
        private int loginTimeStamp;
        private Object courseName;
        private String displayName;
        private Object userProfileList;
        private Object forTeacherStatus;
        private Object mobileNotNull;
        private Object emailNotNull;
        private String realName;
        private String idCardNo;
        private int province;
        private int city;
        private int area;
        private String school;
        private String subject;
        private String paDogToken;
        private int userType;
        private int parentId;
        private int roleId;
        private int enterpriseId;
        private String period;
        private String grade;
        private String address;
        private String lastLoginIp;
        private String lastLoginTime;
        private int firstLoginFlag;
        private Object schoolSystem;
        private String lectureTruthPicImg;
        private String lectureTruthShowName;
        private String lectureTruthSchoolName;
        private String lectureTruthProfile;
        private String lectureTruthPeriod;
        private String lectureTruthGrade;
        private String lectureTruthSubject;
        private int lectureTruthSysUserId;
        private int lectureTruthIsAudit;
        private Object lectureTruthAuditTime;
        private Object lectureTruthAuditRemark;
        private Object testUserType;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
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

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public int getIsavalible() {
            return isavalible;
        }

        public void setIsavalible(int isavalible) {
            this.isavalible = isavalible;
        }

        public String getPicImg() {
            return picImg;
        }

        public void setPicImg(String picImg) {
            this.picImg = picImg;
        }

        public Object getBannerUrl() {
            return bannerUrl;
        }

        public void setBannerUrl(Object bannerUrl) {
            this.bannerUrl = bannerUrl;
        }

        public int getMsgNum() {
            return msgNum;
        }

        public void setMsgNum(int msgNum) {
            this.msgNum = msgNum;
        }

        public int getSysMsgNum() {
            return sysMsgNum;
        }

        public void setSysMsgNum(int sysMsgNum) {
            this.sysMsgNum = sysMsgNum;
        }

        public Object getLastSystemTime() {
            return lastSystemTime;
        }

        public void setLastSystemTime(Object lastSystemTime) {
            this.lastSystemTime = lastSystemTime;
        }

        public Object getLoginAccount() {
            return loginAccount;
        }

        public void setLoginAccount(Object loginAccount) {
            this.loginAccount = loginAccount;
        }

        public String getRegisterFrom() {
            return registerFrom;
        }

        public void setRegisterFrom(String registerFrom) {
            this.registerFrom = registerFrom;
        }

        public Object getInvitationCode() {
            return invitationCode;
        }

        public void setInvitationCode(Object invitationCode) {
            this.invitationCode = invitationCode;
        }

        public int getLoginTimeStamp() {
            return loginTimeStamp;
        }

        public void setLoginTimeStamp(int loginTimeStamp) {
            this.loginTimeStamp = loginTimeStamp;
        }

        public Object getCourseName() {
            return courseName;
        }

        public void setCourseName(Object courseName) {
            this.courseName = courseName;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public Object getUserProfileList() {
            return userProfileList;
        }

        public void setUserProfileList(Object userProfileList) {
            this.userProfileList = userProfileList;
        }

        public Object getForTeacherStatus() {
            return forTeacherStatus;
        }

        public void setForTeacherStatus(Object forTeacherStatus) {
            this.forTeacherStatus = forTeacherStatus;
        }

        public Object getMobileNotNull() {
            return mobileNotNull;
        }

        public void setMobileNotNull(Object mobileNotNull) {
            this.mobileNotNull = mobileNotNull;
        }

        public Object getEmailNotNull() {
            return emailNotNull;
        }

        public void setEmailNotNull(Object emailNotNull) {
            this.emailNotNull = emailNotNull;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getIdCardNo() {
            return idCardNo;
        }

        public void setIdCardNo(String idCardNo) {
            this.idCardNo = idCardNo;
        }

        public int getProvince() {
            return province;
        }

        public void setProvince(int province) {
            this.province = province;
        }

        public int getCity() {
            return city;
        }

        public void setCity(int city) {
            this.city = city;
        }

        public int getArea() {
            return area;
        }

        public void setArea(int area) {
            this.area = area;
        }

        public String getSchool() {
            return school;
        }

        public void setSchool(String school) {
            this.school = school;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getPaDogToken() {
            return paDogToken;
        }

        public void setPaDogToken(String paDogToken) {
            this.paDogToken = paDogToken;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public int getRoleId() {
            return roleId;
        }

        public void setRoleId(int roleId) {
            this.roleId = roleId;
        }

        public int getEnterpriseId() {
            return enterpriseId;
        }

        public void setEnterpriseId(int enterpriseId) {
            this.enterpriseId = enterpriseId;
        }

        public String getPeriod() {
            return period;
        }

        public void setPeriod(String period) {
            this.period = period;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLastLoginIp() {
            return lastLoginIp;
        }

        public void setLastLoginIp(String lastLoginIp) {
            this.lastLoginIp = lastLoginIp;
        }

        public String getLastLoginTime() {
            return lastLoginTime;
        }

        public void setLastLoginTime(String lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
        }

        public int getFirstLoginFlag() {
            return firstLoginFlag;
        }

        public void setFirstLoginFlag(int firstLoginFlag) {
            this.firstLoginFlag = firstLoginFlag;
        }

        public Object getSchoolSystem() {
            return schoolSystem;
        }

        public void setSchoolSystem(Object schoolSystem) {
            this.schoolSystem = schoolSystem;
        }

        public String getLectureTruthPicImg() {
            return lectureTruthPicImg;
        }

        public void setLectureTruthPicImg(String lectureTruthPicImg) {
            this.lectureTruthPicImg = lectureTruthPicImg;
        }

        public String getLectureTruthShowName() {
            return lectureTruthShowName;
        }

        public void setLectureTruthShowName(String lectureTruthShowName) {
            this.lectureTruthShowName = lectureTruthShowName;
        }

        public String getLectureTruthSchoolName() {
            return lectureTruthSchoolName;
        }

        public void setLectureTruthSchoolName(String lectureTruthSchoolName) {
            this.lectureTruthSchoolName = lectureTruthSchoolName;
        }

        public String getLectureTruthProfile() {
            return lectureTruthProfile;
        }

        public void setLectureTruthProfile(String lectureTruthProfile) {
            this.lectureTruthProfile = lectureTruthProfile;
        }

        public String getLectureTruthPeriod() {
            return lectureTruthPeriod;
        }

        public void setLectureTruthPeriod(String lectureTruthPeriod) {
            this.lectureTruthPeriod = lectureTruthPeriod;
        }

        public String getLectureTruthGrade() {
            return lectureTruthGrade;
        }

        public void setLectureTruthGrade(String lectureTruthGrade) {
            this.lectureTruthGrade = lectureTruthGrade;
        }

        public String getLectureTruthSubject() {
            return lectureTruthSubject;
        }

        public void setLectureTruthSubject(String lectureTruthSubject) {
            this.lectureTruthSubject = lectureTruthSubject;
        }

        public int getLectureTruthSysUserId() {
            return lectureTruthSysUserId;
        }

        public void setLectureTruthSysUserId(int lectureTruthSysUserId) {
            this.lectureTruthSysUserId = lectureTruthSysUserId;
        }

        public int getLectureTruthIsAudit() {
            return lectureTruthIsAudit;
        }

        public void setLectureTruthIsAudit(int lectureTruthIsAudit) {
            this.lectureTruthIsAudit = lectureTruthIsAudit;
        }

        public Object getLectureTruthAuditTime() {
            return lectureTruthAuditTime;
        }

        public void setLectureTruthAuditTime(Object lectureTruthAuditTime) {
            this.lectureTruthAuditTime = lectureTruthAuditTime;
        }

        public Object getLectureTruthAuditRemark() {
            return lectureTruthAuditRemark;
        }

        public void setLectureTruthAuditRemark(Object lectureTruthAuditRemark) {
            this.lectureTruthAuditRemark = lectureTruthAuditRemark;
        }

        public Object getTestUserType() {
            return testUserType;
        }

        public void setTestUserType(Object testUserType) {
            this.testUserType = testUserType;
        }

        @Override
        public String toString() {
            return "ContentBean{" +
                    "userId=" + userId +
                    ", mobile='" + mobile + '\'' +
                    ", email='" + email + '\'' +
                    ", password='" + password + '\'' +
                    ", userName='" + userName + '\'' +
                    ", showName='" + showName + '\'' +
                    ", sex=" + sex +
                    ", age=" + age +
                    ", createTime=" + createTime +
                    ", isavalible=" + isavalible +
                    ", picImg='" + picImg + '\'' +
                    ", bannerUrl=" + bannerUrl +
                    ", msgNum=" + msgNum +
                    ", sysMsgNum=" + sysMsgNum +
                    ", lastSystemTime=" + lastSystemTime +
                    ", loginAccount=" + loginAccount +
                    ", registerFrom='" + registerFrom + '\'' +
                    ", invitationCode=" + invitationCode +
                    ", loginTimeStamp=" + loginTimeStamp +
                    ", courseName=" + courseName +
                    ", displayName='" + displayName + '\'' +
                    ", userProfileList=" + userProfileList +
                    ", forTeacherStatus=" + forTeacherStatus +
                    ", mobileNotNull=" + mobileNotNull +
                    ", emailNotNull=" + emailNotNull +
                    ", realName='" + realName + '\'' +
                    ", idCardNo='" + idCardNo + '\'' +
                    ", province=" + province +
                    ", city=" + city +
                    ", area=" + area +
                    ", school='" + school + '\'' +
                    ", subject='" + subject + '\'' +
                    ", paDogToken='" + paDogToken + '\'' +
                    ", userType=" + userType +
                    ", parentId=" + parentId +
                    ", roleId=" + roleId +
                    ", enterpriseId=" + enterpriseId +
                    ", period='" + period + '\'' +
                    ", grade='" + grade + '\'' +
                    ", address='" + address + '\'' +
                    ", lastLoginIp='" + lastLoginIp + '\'' +
                    ", lastLoginTime='" + lastLoginTime + '\'' +
                    ", firstLoginFlag=" + firstLoginFlag +
                    ", schoolSystem=" + schoolSystem +
                    ", lectureTruthPicImg='" + lectureTruthPicImg + '\'' +
                    ", lectureTruthShowName='" + lectureTruthShowName + '\'' +
                    ", lectureTruthSchoolName='" + lectureTruthSchoolName + '\'' +
                    ", lectureTruthProfile='" + lectureTruthProfile + '\'' +
                    ", lectureTruthPeriod='" + lectureTruthPeriod + '\'' +
                    ", lectureTruthGrade='" + lectureTruthGrade + '\'' +
                    ", lectureTruthSubject='" + lectureTruthSubject + '\'' +
                    ", lectureTruthSysUserId=" + lectureTruthSysUserId +
                    ", lectureTruthIsAudit=" + lectureTruthIsAudit +
                    ", lectureTruthAuditTime=" + lectureTruthAuditTime +
                    ", lectureTruthAuditRemark=" + lectureTruthAuditRemark +
                    ", testUserType=" + testUserType +
                    '}';
        }
    }




    @Override
    public String toString() {
        return "GetUserBean{" +
                "flag=" + flag +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", content=" + content +
                ", page=" + page +
                '}';
    }
}
