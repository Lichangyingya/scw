package cn.jxau.dao;

import cn.jxau.pojo.Department;
import cn.jxau.pojo.Student;
import cn.jxau.util.JdbcUtils;
import cn.jxau.vo.StudentVo;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class StuDao implements IStudentDao {
    QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());

    /**
     * 对crm_student表实现分页查询
     * @param pageStart
     * @param pageSize
     * @return
     */
    @Override
    public List<Student> studentPage(int pageStart, int pageSize) {
        String sql ="select * from crm_student limit "+pageStart+","+pageSize;
        List<Student> students = null;
        try {
            students = queryRunner.query(sql, new BeanListHandler<>(Student.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    /**
     * 获取记录总条数为分页作准备
     * @return
     */
    @Override
    public long total() {
        long total = 0;
        String sql ="select count(*) from crm_student";
        try {
            Object o = queryRunner.query(sql, new ScalarHandler());
            String string;
            total = Long.parseLong(o.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    /**
     * 由于数据库中的时间类型为 datetime 类型，因此将时间转换为字符串存往数据库中
     * @param studentVo
     * @return 返回值为1 说明修改成功 否则修改失败
     */
    @Override
    public int undateById(StudentVo studentVo) {
        // '" + stustate + '\''
        String sql ="update crm_student set "+
                "stuname='" +studentVo.getStuname()+ '\''+
                ",stusex='" +studentVo.getStusex() +'\''+
                ",stuCode='"+studentVo.getStucode()+'\''+
                ",begintime ='"+studentVo.getBeginTimeStr()+'\''+
                ",remark ='"+studentVo.getRemark()+'\''+
                ",classid = "+studentVo.getClassid()+
                ",jobTime='" +studentVo.getJobTimeStr()+'\''+
                " where id="+studentVo.getId();
        int update = 0;
        try {
            update = queryRunner.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;


    }

    @Override
    public StudentVo selectById(int id) {
        String sql = "select * from crm_student where id= "+id;
        StudentVo student = null;
        try {
            student = queryRunner.query(sql, new BeanHandler<>(StudentVo.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public int insert(StudentVo studentVo) {
        String sql = "insert into crm_student(stuName,stuCode,stuSex,classid,beginTime,jobTime,remark) values(?,?,?,?,?,?,?)";
        Object[] parames={studentVo.getStuname(),studentVo.getStucode(),studentVo.getStusex(),
                studentVo.getClassid(),studentVo.getBeginTimeStr(),studentVo.getJobTimeStr(),studentVo.getRemark()};
        int update = 0;
        try {
            update = queryRunner.update(sql, parames);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }

    @Override
    public int deleteById(int id) {
        String sql = "delete from crm_student where id = ?";
        Object param = id;
        int update = 0;
        try {
            update = queryRunner.update(sql, param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }

    @Override
    public int updateStatus(String status, int id) {
        String sql = "update crm_student set stustate  = ? where id = ?";
        Object []params = {status,id};
        int update = 0;
        try {
            update = queryRunner.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }

    @Override
    public long totaladvancedStudentQuery(String className,String status, String stuName) {
        String sql = "select count(*) " +
                "from crm_class c,crm_student s  where s.classid = c.id  ";
        if (status != null && !status.equals("")) {
            sql += "and stuState = '"+status+'\'';
        }
        if (className != null && !className.equals("")) {
            sql += "and className = '"+className+'\'';
        }
        if (stuName != null && !stuName.equals("")) {
            sql +=" and stuName like '%"+stuName+"%'";
        }
        Object query = null;
        System.out.println(sql);
        try {
            query = queryRunner.query(sql, new ScalarHandler());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Integer.parseInt(query.toString());
    }

    @Override
    public List<StudentVo> advancedStudentQuery(String className, String status,String stuName, int pageStart, int pageSize) {
        String sql = "select s.id ,s.stuName,s.stuState,s.stuSex,c.className,s.beginTime,s.classid,c.className,s.stuState " +
                "from crm_class c,crm_student s  where s.classid = c.id  ";
        if (status != null && !status.equals("")) {
            sql += "and stuState = '"+status+'\'';
        }
        if (className != null && !className.equals("")) {
            sql += "and className = '"+className+'\'';
        }
        if (stuName != null && !stuName.equals("")) {
            sql +=" and stuName like '%"+stuName+"%'";
        }

        sql += " limit ?,?";
        Object [] param = {pageStart,pageSize};
        System.out.println(sql);
        List<StudentVo> studentVos = null;
        try {
            studentVos = queryRunner. query(sql, new BeanListHandler<>(StudentVo.class),param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentVos;
    }

    @Override
    public List<StudentVo> selByCondition(String className, String stuName,int pageStart,int pageSize) {
        String sql = "select s.id ,s.stuName,s.stuState,s.stuSex,c.className,s.beginTime,s.classid,c.className,s.stuState " +
                "from crm_class c,crm_student s  where s.classid = c.id  ";

        if (className != null && !className.equals("")) {
            sql += "and className = '"+className+'\'';
        }
        if (stuName != null && !stuName.equals("")) {
            sql +=" and stuName like '%"+stuName+"%'";
        }
        sql += " limit ?,?";
        Object[] params= {pageStart,pageSize};
        List<StudentVo> studentVos = null;
        try {
            studentVos = queryRunner.query(sql, new BeanListHandler<>(StudentVo.class),params);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentVos;
    }

    @Override
    public List<Student> queryAllStatus() {
        String sql = "select distinct stuState from crm_student";
        List<Student> students = null;
        try {
            students = queryRunner.query(sql, new BeanListHandler<>(Student.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public long totalByCondition(String className, String stuName){
        String sql = "select count(*) " +
                "from crm_class c,crm_student s  where s.classid = c.id  ";
        if (className != null && !className.equals("")) {
            sql += "and className = '"+className+'\'';
        }
        if (stuName != null && !stuName.equals("")) {
            sql +=" and stuName like '%"+stuName+"%'";
        }
        Object query = null;
        try {
            query = queryRunner.query(sql, new ScalarHandler());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Integer.parseInt(query.toString());


    }
}
