package cn.jxau.vo;

import cn.jxau.pojo.Staff;

public class StaffVo extends Staff {
    private String postName;
    private String depName;
    private int depId;

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    @Override
    public String toString() {
        return "StaffVo{" +
                "postName='" + postName + '\'' +
                ", depName='" + depName + '\'' +
                '}';
    }
}
