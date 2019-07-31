package cn.jxau.service;

import cn.jxau.pojo.PageInfo;
import cn.jxau.pojo.Student;
import cn.jxau.vo.StudentVo;

import java.util.List;

public interface StudentService {
    List<Student> showAll();
    List<Student> show(int pageNumber,int pageSize);
    PageInfo showStudent(int pageNumber,int pageSize);
    boolean UpdateById(StudentVo studentVo);
    StudentVo selectById(int id);
    int insert(StudentVo studentVo);
    int  deleteStudent(int id);
    PageInfo selByCondition(String className,String stuName,int pageNumber,int pageSize);
    List<Student> queryStuStatus();

    PageInfo advancedStudentQuery(String className, String stuStatus, String stuName, int pageNumber, int pageSize);

    int updStatus(String status, String checkboxs);
}
