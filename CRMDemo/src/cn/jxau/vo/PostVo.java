package cn.jxau.vo;

import cn.jxau.pojo.Department;
import cn.jxau.pojo.Post;

public class PostVo extends Post {
    private Department department;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "PostVo{" +
                "postId=" + super.getPostId() +
                ", postName='" + super.getPostName() + '\'' +
                "department=" + department +
                '}';
    }
}
