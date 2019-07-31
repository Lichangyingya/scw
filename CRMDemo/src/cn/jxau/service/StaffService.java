package cn.jxau.service;

import cn.jxau.pojo.PageInfo;
import cn.jxau.pojo.Staff;

public interface StaffService {
    PageInfo showPage(int pageNumber,int pageSize);

    PageInfo selByCondition(String depId,String postId,String staffName,int pageNumber,int pageSize);

    Staff selById(int staffId);

    int delStaff(int staffId);

    int updateStaff(Staff staff);

    int addStaff(Staff staff);

    int isExistLoginName(String loginName);

    boolean login(String userName, String password);
}
