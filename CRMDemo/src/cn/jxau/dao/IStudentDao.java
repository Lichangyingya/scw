package cn.jxau.dao;

import cn.jxau.pojo.Student;
import cn.jxau.vo.StudentVo;

import java.util.List;

public interface IStudentDao {
    List<Student> studentPage(int pageStart, int pageSize);

    long total();

    int undateById(StudentVo studentVo);

    StudentVo selectById(int id);

    int insert(StudentVo studentVo);

    int deleteById(int id);

    List<StudentVo>selByCondition(String className, String stuName,int pageStart,int pageSize);

    long totalByCondition(String className, String stuName);

    List<Student> queryAllStatus ();

    long totaladvancedStudentQuery(String className,String status, String stuName);

    List<StudentVo> advancedStudentQuery(String className,String status,String stuName, int pageStart, int pageSize);

    int updateStatus(String status, int id);
}