package cn.jxau.dao;

import cn.jxau.pojo.Department;
import cn.jxau.util.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class DeptDao implements IDepartmentDao {
    QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
    @Override
    public List<Department> showDepartment(int pageStart, int pageSize) {

        String sql ="select id,depName,isCancel from crm_department limit "+pageStart+","+pageSize;
        List<Department> departments = null;
        try {
            departments = queryRunner.query(sql, new BeanListHandler<>(Department.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }

    @Override
    public long selectTotal() {
        long total = 0;
        String sql ="select count(*) from crm_department";
        try {
            Object o = queryRunner.query(sql, new ScalarHandler());
            String string;
            total = Long.parseLong(o.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    @Override
    public Department selById(int id) {
        String sql = "select * from crm_department where id = ?";
        Object param = id;
        Department department = null;
        try {
            department = queryRunner.query(sql, new BeanHandler<>(Department.class), param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return department;
    }

    @Override
    public int ins(Department department) {
        String sql = "insert into crm_department(depName, isCancel) values(?,?)";
        Object[] params = {department.getDepName(),department.getIsCancel()};
        int ins = 0;
        try {
            ins = queryRunner.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ins;
    }

    @Override
    public int delDeptById(int id) {
        String sql = "delete from crm_department where id = ? ";
        Object param = id;
        int del = 0;
        try {
            del = queryRunner.update(sql, param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return del;
    }

    @Override
    public int selByDepName(String depName) {
        String sql = "select count(*) from crm_department where depName = ?";
        Object param = depName;
        Object query = null;
        try {
            query = queryRunner.query(sql, new ScalarHandler(), param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(query.toString());
    }

    @Override
    public List<Department> getAll() {
        String sql = "select * from crm_department";
        List<Department> departments = null;
        try {
            departments = queryRunner.query(sql, new BeanListHandler<>(Department.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }

    @Override
    public int updateById(Department department) {
        String sql = "update crm_department set depName = ? where id = ?";
        Object[] params = {department.getDepName(),department.getId()};
        int update = 0;
        try {
            update = queryRunner.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }
}
