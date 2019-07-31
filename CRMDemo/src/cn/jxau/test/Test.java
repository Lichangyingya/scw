package cn.jxau.test;

import cn.jxau.dao.*;
import cn.jxau.pojo.*;
import cn.jxau.service.Impl.PostServiceImpl;
import cn.jxau.service.Impl.StaffServiceImpl;
import cn.jxau.service.PostService;
import cn.jxau.service.StaffService;
import cn.jxau.util.JdbcUtil;
import cn.jxau.util.JdbcUtils;
import cn.jxau.util.RandomUtil;
import cn.jxau.vo.StaffVo;
import cn.jxau.vo.StudentVo;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Test {
    public static void main(String[] args) throws SQLException {
//        StudentDao studentDao = new StudentDao();
//        List<Student> list = studentDao.studentPage(0, 5);
//        System.out.println(list);
//        DepartmentDao departmentDao = new DepartmentDao();
//        List<Department> departments = departmentDao.showDepartment(1, 4);
//        System.out.println(departments);
//        DeptDao deptDao = new DeptDao();
//        StuDao stuDao = new StuDao();
//        List<Student> students = stuDao.studentPage(0, 5);
//        long total1 = stuDao.total();
//        System.out.println(students);
//        System.out.println(total1);
//        List<Department> departments = deptDao.showDepartment(0,5);
//        long total = deptDao.selectTotal();
//        System.out.println(total);
//        System.out.println(departments);
//
//        IClassDao iClassDao = new ClassDao();
//        CrmClass crmClass = iClassDao.selByID(3);
//        System.out.println("-->"+crmClass);
//        IStudentDao iStudentDao = new StuDao();
//        StudentVo studentVo = new StudentVo();
//
//        studentVo.setStuname("王刚");
//        studentVo.setStusex("男");
//        studentVo.setStucode("3251497");
//        studentVo.setBeginTimeStr("2019-07-19 00:00:00");
//        studentVo.setJobTimeStr("2019-010-01 00:00:00");
//        studentVo.setClassid(4);
//        studentVo.setRemark("add");
//        int insert = iStudentDao.insert(studentVo);
//        System.out.println(insert);
//        IDepartmentDao iDepartmentDao = new DeptDao();
//        Department department = new Department();
//        department.setId(1014);
//        department.setDepName("风控部");
//        department.setIsCancel(0);
//        iDepartmentDao.updateById(department);

//        PostDao postDao = new PostDao();
//        Post post = new Post();
//        post.setPostId(1022);
//        post.setPostName("哈哈哈");
//        post.setDepId(1001);
//        List<Post> posts = postDao.selByPage(0, 5);
//        int i = postDao.delById(1022);
//        System.out.println("-->"+i);

//        PostDao postDao = new PostDao();
//        List<Post> posts = postDao.selByDepId(1001);
//        System.out.println(posts);
//        StaffDao staffDao = new StaffDao();
//        Staff staff = new Staff();
////        staff.setLoginName("kkll");
////        staff.setLoginPwd("fssff");
////        staff.setGender("男");
////        staff.setPostId(1001);
////        staff.setOnDutyDateStr("2018-03-05 00:00:00");
////        staff.setStaffName("好看");
////        int i = staffDao.insStaff(staff);
////        System.out.println(i);
//        int i = staffDao.delByStaffId(18);
//        Staff staff = staffDao.selById(17);
//        long i = staffDao.queryTotal("", "", "王", 0, 6);
//        System.out.println("-->"+i);
//        System.out.println(staff);
//        DataSource dataSource = JdbcUtils.getDataSource();
//        QueryRunner queryRunner = new QueryRunner(dataSource);
//        String sql = "select * from crm_class";
//        List<CrmClass> crmClasses = queryRunner.query(sql, new BeanListHandler<>(CrmClass.class));
//        System.out.println(crmClasses);
//        Connection connection = JdbcUtil.getConnection();
//        System.out.println(connection);
        StaffDao staffDao = new StaffDao();
        Staff staff = staffDao.selByUserName("wahaha");
        System.out.println(staff);


    }
}
