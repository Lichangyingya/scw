package cn.jxau.service;

import cn.jxau.pojo.Department;
import cn.jxau.pojo.PageInfo;

import java.util.List;

public interface DepartmentService {
   PageInfo showDepartment(int pageNumber, int pageSize);

   int addDept(Department department);

   Department selById(int id);

   int updateDept(Department department);

   int delDept(int id);

   List<Department> getAll();

   int isExistDepName(String depName);
}
