package cn.jxau.service.Impl;

import cn.jxau.dao.*;
import cn.jxau.pojo.PageInfo;
import cn.jxau.pojo.Student;
import cn.jxau.service.StudentService;
import cn.jxau.vo.StudentVo;

import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements StudentService {

    IClassDao iClassDao = new ClassDao();
    IStudentDao iStudentDao = new StuDao();
    StudentDao studentDao = new StudentDao();
    @Override
    public List<Student> showAll() {

        List<Student> list = new ArrayList<>();
        List<Student> students = studentDao.findAll();
        for (Student stu:students) {
            stu.setCrmClass(iClassDao.selClass(stu.getClassid()));
            list.add(stu);
        }
        return list;
    }

    @Override
    public List<Student> show(int pageNumber, int pageSize) {
        List<Student> list = new ArrayList<>();
        int pageStart = (pageNumber-1)*pageSize;
        List<Student> students = iStudentDao.studentPage(pageStart,pageSize);
        for (Student stu:students) {
            stu.setCrmClass(iClassDao.selClass(stu.getClassid()));
            list.add(stu);
        }
        return list;
    }

    /**
     * 将分页信息封装在PageInfo类中
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo showStudent(int pageNumber, int pageSize) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNumber(pageNumber);
        pageInfo.setPageSize(pageSize);
        List<Student> list = new ArrayList<>();
        int pageStart = (pageNumber-1)*pageSize;
        List<Student> students = iStudentDao.studentPage(pageStart,pageSize);
        for (Student stu:students) {
            stu.setCrmClass(iClassDao.selClass(stu.getClassid()));
            list.add(stu);
        }
        long total = iStudentDao.total();
        long totalPage=total%pageSize==0?total/pageSize:total/pageSize+1;
        pageInfo.setTotalPage(totalPage);
        pageInfo.setTotal(total);
        pageInfo.setList(list);
        return pageInfo;
    }

    @Override
    public boolean UpdateById(StudentVo studentVo) {
        int i = iStudentDao.undateById(studentVo);
        if (i == 1){
            return true;
        }
        return false;
    }

    @Override
    public int updStatus(String status, String checkboxs) {
        String[] strings = checkboxs.split(",");
        int index = 0;
        for (String s:strings){
           index += iStudentDao.updateStatus(status, Integer.parseInt(s));
        }
        return index;
    }

    @Override
    public PageInfo advancedStudentQuery(String className, String stuStatus, String stuName,int pageNumber, int pageSize) {
        int pageStart = (pageNumber-1)*pageSize;
        List<StudentVo> studentVos = iStudentDao.advancedStudentQuery(className,stuStatus ,stuName ,pageStart, pageSize);
        long total = iStudentDao.totaladvancedStudentQuery(className,stuStatus,stuName);
        long totalPage = total%pageSize == 0? total/pageSize:total/pageSize+1;
        PageInfo pageInfo = new PageInfo(pageSize,pageNumber,totalPage,total,studentVos,pageStart);
        return pageInfo;
    }

    @Override
    public StudentVo selectById(int id) {
        StudentVo studentVo = iStudentDao.selectById(id);
        studentVo.setCrmClass(iClassDao.selClass(studentVo.getClassid()));
        return studentVo;
    }

    @Override
    public int deleteStudent(int id) {
        return iStudentDao.deleteById(id);
    }

    @Override
    public List<Student> queryStuStatus() {

        return iStudentDao.queryAllStatus();
    }

    @Override
    public PageInfo selByCondition(String className, String stuName, int pageNumber, int pageSize) {
        int pageStart = (pageNumber-1)*pageSize;
        List<StudentVo> studentVos = iStudentDao.selByCondition(className, stuName, pageStart, pageSize);
        long total = iStudentDao.totalByCondition(className, stuName);
        long totalPage = total%pageSize == 0? total/pageSize:total/pageSize+1;
        PageInfo pageInfo = new PageInfo(pageSize,pageNumber,totalPage,total,studentVos,pageStart);
        return pageInfo;
    }


    @Override
    public int insert(StudentVo studentVo) {
        return iStudentDao.insert(studentVo);
    }


}
