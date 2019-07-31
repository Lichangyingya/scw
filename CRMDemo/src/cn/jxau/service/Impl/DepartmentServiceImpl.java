package cn.jxau.service.Impl;

import cn.jxau.dao.DepartmentDao;
import cn.jxau.dao.DeptDao;
import cn.jxau.dao.IDepartmentDao;
import cn.jxau.pojo.Department;
import cn.jxau.pojo.PageInfo;
import cn.jxau.service.DepartmentService;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {
    DepartmentDao departmentDao = new DepartmentDao();
    IDepartmentDao iDepartmentDao = new DeptDao();

    /**
     * 根据主键查询部门信息 用于修改部门 信息显示
     * @param id
     * @return
     */
    @Override
    public Department selById(int id) {
        return iDepartmentDao.selById(id);
    }

    /**
     * 修改部门
     * @param department
     * @return
     */
    @Override
    public int updateDept(Department department) {
        return iDepartmentDao.updateById(department);
    }

    /**
     * 通过主键删除部门信息
     * @param id
     * @return
     */
    @Override
    public int delDept(int id) {

        return iDepartmentDao.delDeptById(id);
    }

    /**
     * 添加部门
     * @param department
     * @return
     */
    @Override
    public int addDept(Department department) {

        return iDepartmentDao.ins(department);
    }

    /**
     * 得到所有部门
     * @return
     */
    @Override
    public List<Department> getAll() {
        return iDepartmentDao.getAll();
    }

    /**
     * 分页显示数据并且将分页信息分装到pageInfo中
     * @param pageNumber
     * @param pageSize
     * @return pageInfo
     */
    @Override
    public PageInfo showDepartment(int pageNumber, int pageSize) {
        int pageStart = (pageNumber-1)*pageSize;
        List<Department> departments = iDepartmentDao.showDepartment(pageStart, pageSize);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageStart(pageStart);
        pageInfo.setPageNumber(pageNumber);
        pageInfo.setPageSize(pageSize);
        pageInfo.setList(departments);
        long total = iDepartmentDao.selectTotal();
        long totalPage=total%pageSize==0?total/pageSize:total/pageSize+1;
        pageInfo.setTotalPage(totalPage);
        pageInfo.setTotal(total);
        return pageInfo;
    }

    @Override
    public int isExistDepName(String depName) {
        return iDepartmentDao.selByDepName(depName);
    }
}
