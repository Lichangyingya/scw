package cn.jxau.dao;

import cn.jxau.pojo.Post;
import cn.jxau.pojo.Staff;
import cn.jxau.util.JdbcUtils;
import cn.jxau.vo.StaffVo;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class StaffDao {
    QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());

    /**
     * 分页查询
     * @param pageStart
     * @param pageSize
     * @return
     */
    public List<Staff> selByPage(int pageStart,int pageSize){
        String sql ="select * from crm_staff limit "+pageStart+","+pageSize;
        List<Staff> staffList = null;
        try {
            staffList = queryRunner.query(sql, new BeanListHandler<>(Staff.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffList;
    }

    /**
     * 查询总记录数
     * @return
     */
    public long selTotal(){
        String sql = "select count(*) from crm_staff";
        Object o = null;
        try {
            o = queryRunner.query(sql, new ScalarHandler());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        long total = Long.parseLong(o.toString());
        return total;
    }

    /**
     * 根据主键更新
     * @param staff
     * @return
     */
    public int updateById(Staff staff){
        String sql = "update crm_staff set loginName = ?,loginPwd = ?,StaffName = ?,gender = ?,onDutyDate = ?,postId = ? where staffId = ?";
        Object[] params= {staff.getLoginName(),staff.getLoginPwd(),staff.getStaffName(),staff.getGender(),staff.getOnDutyDateStr(),staff.getPostId(),staff.getStaffId()};
        int update = 0;
        try {
            update = queryRunner.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;

    }

    /**
     * 根据主键查询
     * @param staffId
     * @return
     */
    public Staff selById(int staffId){
        String sql = "select * from crm_staff where staffId = ?";
        Object param = staffId;
        int sel = 0;
        Staff staff = null;
        try {
            staff = queryRunner.query(sql, new BeanHandler<>(Staff.class), param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staff;
    }

    /**
     * 根据条件高级查询
     * @param depId
     * @param postId
     * @param staffName
     * @return
     */
    public List<StaffVo>getAllByCondition (String depId,String postId,String staffName,int pageStart,int pageSize){
        String sql = "select s.staffid,s.staffName,s.postId,s.onDutyDate,s.gender,p.postName,d.depName " +
                "from crm_post p,crm_department d,crm_staff s where s.postID = p.postId and p.depId =d.Id";
        if (depId!=null && !depId.equals("")) {

            sql+=" and d.id ="+depId;
        }
        if (postId!=null && !postId.equals("")) {

            sql+=" and p.postId ="+postId;
        }
        if(staffName!=null && !staffName.equals("")){
            sql+=" and  staffName like '%"+staffName+"%'";
        }
        sql +=" limit ?,?";
        Object[] params = {pageStart,pageSize};


        List<StaffVo> staffList = null;
        try {
            staffList = queryRunner.query(sql, new BeanListHandler<>(StaffVo.class),params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffList;
    }

    /**
     * 根据主键删除
     * @param satffId
     * @return
     */
    public int delByStaffId(int satffId){
        String sql = "delete from crm_staff where staffId = ?";
        Object param = satffId;
        int del = 0;
        try {
            del = queryRunner.update(sql, param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return del;
    }

    /**
     * 增加员工
     * @param staff
     * @return
     */
    public int insStaff(Staff staff){
        String sql = "insert into crm_staff(loginName,loginPwd,staffName,gender,onDutyDate,postId) values(?,?,?,?,?,?)";
        Object[] params= {staff.getLoginName(),staff.getLoginPwd(),staff.getStaffName(),staff.getGender(),staff.getOnDutyDateStr(),staff.getPostId()};
        int ins = 0;
        try {
            ins = queryRunner.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ins;
    }

    /**
     * 查询由高级查询得到的总条数
     * @return
     */
    public long queryTotal(String depId,String postId,String staffName,int pageStart,int pageSize){
        String sql = "select count(*) " +
                "from crm_post p,crm_department d,crm_staff s where s.postID = p.postId and p.depId =d.Id";
        if (depId!=null && !depId.equals("")) {

            sql+=" and d.id ="+depId;
        }
        if (postId!=null && !postId.equals("")) {

            sql+=" and p.postId ="+postId;
        }
        if(staffName!=null && !staffName.equals("")){
            sql+=" and  staffName like '%"+staffName+"%'";
        }
        Object query = null;
        try {
            query = queryRunner.query(sql, new ScalarHandler());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String string;
        long total = Long.parseLong(query.toString());
        return total;
    }
    public int selByLoginName(String loginName){
        String sql = "select count(*) from crm_staff where loginName = ?";
        Object param = loginName;
        Object query = null;
        try {
            query = queryRunner.query(sql, new ScalarHandler(),param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(query.toString());
    }

    public Staff selByUserName(String userName) {
        String sql = "select * from crm_staff where loginName = ?";
        Object param = userName;
        Staff staff = null;
        try {
            staff = queryRunner.query(sql, new BeanHandler<>(Staff.class), param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staff;

    }
}
