package cn.jxau.dao;

import cn.jxau.pojo.CrmClass;
import cn.jxau.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OldClassDao {
    public CrmClass selById(int id){
        CrmClass crmClass = null;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            String sql = "select className,remark,state from crm_class where id = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1,id);
            rs = pstm.executeQuery();
            while (rs.next()){
                crmClass = new CrmClass();
                crmClass.setClassName(rs.getString(1));
                crmClass.setRemark(rs.getString(2));
                crmClass.setState(rs.getString(3));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        JdbcUtil.free(rs,pstm,conn);
        return crmClass;
    }

}
