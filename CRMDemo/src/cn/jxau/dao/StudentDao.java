package cn.jxau.dao;

import cn.jxau.pojo.Student;
import cn.jxau.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao  {
    public  List<Student> findAll(){
        List<Student> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            String sql ="select * from crm_student";
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()){
                Student stu = new Student();
                stu.setId(rs.getInt(1));
                stu.setStuname(rs.getString(2));
                stu.setStucode(rs.getString(3));
                stu.setStusex(rs.getString(4));
                stu.setReferid(rs.getInt(5));
                stu.setClassid(rs.getInt(6));
                stu.setBegintime(rs.getDate(7));
                stu.setJobtime(rs.getDate(8));
                stu.setStustate(rs.getString(9));
                stu.setChecklevel(rs.getString(10));
                stu.setRemark(rs.getString(11));
                list.add(stu);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.free(rs,pstm,conn);
        }
        return list;
    }
    public  List<Student> studentPage(int pageStart, int pageSize){
        List<Student> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            String sql ="select * from crm_student limit ?,?";
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1,pageStart);
            pstm.setInt(2,pageSize);
            rs = pstm.executeQuery();
            if (rs!= null) {
                while (rs.next()){
                    Student stu = new Student();
                    stu.setId(rs.getInt(1));
                    stu.setStuname(rs.getString(2));
                    stu.setStucode(rs.getString(3));
                    stu.setStusex(rs.getString(4));
                    stu.setReferid(rs.getInt(5));
                    stu.setClassid(rs.getInt(6));
                    stu.setBegintime(rs.getDate(7));
                    stu.setJobtime(rs.getDate(8));
                    stu.setStustate(rs.getString(9));
                    stu.setChecklevel(rs.getString(10));
                    stu.setRemark(rs.getString(11));
                    list.add(stu);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public long total(){
        int total = 0;
        Connection conn =null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            String sql ="select count(*)from crm_student";
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs!= null) {
                while (rs.next()){
                    String columnName;
                    total = rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

}
