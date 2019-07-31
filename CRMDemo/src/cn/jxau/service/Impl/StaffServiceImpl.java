package cn.jxau.service.Impl;

import cn.jxau.dao.DeptDao;
import cn.jxau.dao.IDepartmentDao;
import cn.jxau.dao.PostDao;
import cn.jxau.dao.StaffDao;
import cn.jxau.pojo.Department;
import cn.jxau.pojo.PageInfo;
import cn.jxau.pojo.Post;
import cn.jxau.pojo.Staff;
import cn.jxau.service.StaffService;
import cn.jxau.vo.StaffVo;

import java.util.ArrayList;
import java.util.List;

public class StaffServiceImpl implements StaffService {
   StaffDao staffDao = new StaffDao();
   PostDao postDao = new PostDao();
   IDepartmentDao departmentDao = new DeptDao();

    @Override
    public int isExistLoginName(String loginName) {
        int i = staffDao.selByLoginName(loginName);

        return i;
    }

    /**
     * 验证用户名和密码是否在数据库存在
     * 存在返回true，否则返回false
     * @param userName
     * @param password
     * @return
     */
    @Override
    public boolean login(String userName, String password) {
        Staff staff = staffDao.selByUserName(userName);
        if(staff != null && staff.getLoginPwd().equals(password)){
            return true;
        }
        return false;
    }

    @Override
    public PageInfo selByCondition(String depId, String postId, String staffName,int pageNumber,int pageSize) {
        int pageStart = (pageNumber-1)*pageSize;
        List<StaffVo> staffVos = staffDao.getAllByCondition(depId, postId, staffName,pageStart,pageSize);
        long total = staffDao.queryTotal(depId,postId,staffName,pageStart,pageSize);
        long totalPage=total%pageSize==0?total/pageSize:total/pageSize+1;
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageStart(pageStart);
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNumber(pageNumber);
        pageInfo.setTotal(total);
        pageInfo.setTotalPage(totalPage);
        pageInfo.setList(staffVos);
        return pageInfo;
    }

    @Override
    public Staff selById(int staffId) {
        StaffVo staffVo = new StaffVo();
        Staff staff = staffDao.selById(staffId);
        Post post = postDao.selById(staff.getPostId());
        staffVo.setPostId(staff.getPostId());
        staffVo.setDepId(post.getDepId());
        staffVo.setLoginName(staff.getLoginName());
        staffVo.setLoginPwd(staff.getLoginPwd());
        staffVo.setGender(staff.getGender());
        staffVo.setStaffName(staff.getStaffName());
        staffVo.setOnDutyDate(staff.getOnDutyDate());
        return staffVo;
    }

    @Override
    public int delStaff(int staffId) {
        return staffDao.delByStaffId(staffId);
    }

    @Override
    public int updateStaff(Staff staff) {
        return staffDao.updateById(staff);
    }

    @Override
    public int addStaff(Staff staff) {
        return staffDao.insStaff(staff);
    }

    @Override
    public PageInfo showPage(int pageNumber, int pageSize) {
        int pageStart = (pageNumber-1)*pageSize;
        List<Staff> staffList = staffDao.selByPage(pageStart, pageSize);
        List<StaffVo> staffVos = new ArrayList<>();
        for (Staff staff:staffList) {
            Post post = postDao.selById(staff.getPostId());
            Department department = departmentDao.selById(post.getDepId());
            StaffVo staffVo = new StaffVo();
            staffVo.setStaffId(staff.getStaffId());
            staffVo.setStaffCode(staff.getStaffCode());
            staffVo.setStaffName(staff.getStaffName());
            staffVo.setLoginName(staff.getLoginName());
            staffVo.setLoginPwd(staff.getLoginPwd());
            staffVo.setGender(staff.getGender());
            staffVo.setBirthday(staff.getBirthday());
            staffVo.setOnDutyDate(staff.getOnDutyDate());
            staffVo.setPostId(staff.getPostId());
            staffVo.setState(staff.getState());
            staffVo.setPostName(post.getPostName());
            staffVo.setDepName(department.getDepName());
            staffVos.add(staffVo);
        }
        long total = staffDao.selTotal();
        long totalPage=total%pageSize==0?total/pageSize:total/pageSize+1;
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageStart(pageStart);
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNumber(pageNumber);
        pageInfo.setTotal(total);
        pageInfo.setTotalPage(totalPage);
        pageInfo.setList(staffVos);
        return pageInfo;
    }
}
