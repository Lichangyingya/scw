package cn.jxau.pojo;

import java.util.Date;

public class CrmClass {
    private int id;
    private String className;
    private String beginTime;
    private String endTime;
    private String state;
    private int totalCount;
    private int goCount;
    private int leaveCount;
    private int lessonTypeID;
    private int teacherid;
    private int userid;
    private String uploadFileName;
    private String uploadPath;
    private String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getGoCount() {
        return goCount;
    }

    public void setGoCount(int goCount) {
        this.goCount = goCount;
    }

    public int getLeaveCount() {
        return leaveCount;
    }

    public void setLeaveCount(int leaveCount) {
        this.leaveCount = leaveCount;
    }

    public int getLessonTypeID() {
        return lessonTypeID;
    }

    public void setLessonTypeID(int lessonTypeID) {
        this.lessonTypeID = lessonTypeID;
    }

    public int getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(int teacherid) {
        this.teacherid = teacherid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "CrmClass{" +
                "id=" + id +
                ", className='" + className + '\'' +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", state='" + state + '\'' +
                ", totalCount=" + totalCount +
                ", goCount=" + goCount +
                ", leaveCount=" + leaveCount +
                ", lessonTypeID=" + lessonTypeID +
                ", teacherid=" + teacherid +
                ", userid=" + userid +
                ", uploadFileName='" + uploadFileName + '\'' +
                ", uploadPath='" + uploadPath + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
