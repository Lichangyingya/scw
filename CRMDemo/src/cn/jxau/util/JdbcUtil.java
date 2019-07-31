package cn.jxau.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class JdbcUtil {

    private static String url = "jdbc:mysql://129.28.180.185:3306/CRM?useSSL=true";
    private static String username = "root";
    private static String userpassword = "LCYlcy110212?";
   

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);// 抛出异常，可视
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, userpassword);
    }

    public static void free(ResultSet rs, Statement st, Connection con) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        // TODO 自动生成的 catch 块
                        e.printStackTrace();
                    }
                }
            }
        }

    }
    public static ResultSet getQueryData(String sql){
        Connection conn = null;
        Statement stm = null;
        try {
            conn = getConnection();
            stm = conn.createStatement();
            return stm.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}