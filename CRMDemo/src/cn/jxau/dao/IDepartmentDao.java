package cn.jxau.dao;

import cn.jxau.pojo.Department;

import java.util.List;

public interface IDepartmentDao {
    List<Department> showDepartment(int pageStart, int pageSize);

    long selectTotal();

    int ins(Department department);

    Department selById(int id);

    int updateById(Department department);

    int delDeptById(int id);

    List<Department> getAll();

    int selByDepName(String depName);

}
