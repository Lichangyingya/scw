package cn.jxau.vo;

import cn.jxau.pojo.CrmClass;
import cn.jxau.pojo.Student;

public class StudentVo extends Student {
    private CrmClass crmClass;
    private String beginTimeStr;
    private String jobTimeStr;
    private String endTimeStr;
    private String className;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getBeginTimeStr() {
        return beginTimeStr;
    }

    public void setBeginTimeStr(String beginTimeStr) {
        this.beginTimeStr = beginTimeStr;
    }

    public String getJobTimeStr() {
        return jobTimeStr;
    }

    public void setJobTimeStr(String jobTimeStr) {
        this.jobTimeStr = jobTimeStr;
    }

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }

    @Override
    public CrmClass getCrmClass() {
        return crmClass;
    }

    @Override
    public void setCrmClass(CrmClass crmClass) {
        this.crmClass = crmClass;
    }

    @Override
    public String toString() {
        return "StudentVo{" +
                "crmClass=" + crmClass +
                ", beginTimeStr='" + beginTimeStr + '\'' +
                ", jobTimeStr='" + jobTimeStr + '\'' +
                ", endTimeStr='" + endTimeStr + '\'' +
                '}'+super.toString();
    }
}
