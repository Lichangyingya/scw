package cn.jxau.pojo;

import javax.xml.crypto.Data;
import java.util.Date;

public class Staff {
    private int StaffId;
    private String staffCode;
    private String loginName;
    private String loginPwd;
    private String staffName;
    private String gender;
    private String email;
    private Date birthday;
    private String birthdayStr;
    private String onDutyDateStr;
    private Date onDutyDate;
    private int postId;
    private int state;


    public Staff() {
    }

    public int getStaffId() {
        return StaffId;
    }

    public void setStaffId(int staffId) {
        StaffId = staffId;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getBirthdayStr() {
        return birthdayStr;
    }

    public void setBirthdayStr(String birthdayStr) {
        this.birthdayStr = birthdayStr;
    }

    public String getOnDutyDateStr() {
        return onDutyDateStr;
    }

    public void setOnDutyDateStr(String onDutyDateStr) {

        this.onDutyDateStr = onDutyDateStr;
    }

    public Date getOnDutyDate() {

        return onDutyDate;
    }

    public void setOnDutyDate(Date onDutyDate) {
        this.onDutyDate = onDutyDate;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "StaffId=" + StaffId +
                ", staffCode='" + staffCode + '\'' +
                ", loginName='" + loginName + '\'' +
                ", loginPwd='" + loginPwd + '\'' +
                ", staffName='" + staffName + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", birthdayStr='" + birthdayStr + '\'' +
                ", onDutyDateStr='" + onDutyDateStr + '\'' +
                ", onDutyDate=" + onDutyDate +
                ", postId=" + postId +
                ", state=" + state +
                '}';
    }
}
