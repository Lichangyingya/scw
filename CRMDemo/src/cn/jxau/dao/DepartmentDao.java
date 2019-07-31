package cn.jxau.dao;

import cn.jxau.pojo.Department;
import cn.jxau.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDao {
    public List<Department> findAll(){
        List<Department> list = null;
        String sql ="select id,depName,isCancel from crm_department";
        ResultSet rs = JdbcUtil.getQueryData(sql);
        try {
            if (rs != null) {
                list = new ArrayList<>();
                while (rs.next()){
                    Department department = new Department();
                    department.setId(rs.getInt("id"));
                    department.setDepName(rs.getString("depName"));
                    department.setIsCancel(rs.getInt("isCancel"));
                    list.add(department);
                }
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Department> showDepartment(int pageStart, int pageSize){
        List<Department> list = null;
        Connection conn = null;
        PreparedStatement pstm  =null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            String sql ="select id,depName,isCancel from crm_department limit ?,?";
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1,pageStart);
            pstm.setInt(2,pageSize);
             rs = pstm.executeQuery();
            if (rs != null) {
                list = new ArrayList<>();
                while (rs.next()) {
                    Department department = new Department();
                    department.setId(rs.getInt("id"));
                    department.setDepName(rs.getString("depName"));
                    department.setIsCancel(rs.getInt("isCancel"));
                    list.add(department);
                }
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        JdbcUtil.free(rs,pstm,conn);

         return list;
    }


    public long selectTotal() {
        int total = 0;
        Connection conn =null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            String sql ="select count(*) from crm_department";
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs!= null) {
                while (rs.next()){
                    total = rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }
}

