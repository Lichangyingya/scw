package cn.jxau.dao;

import cn.jxau.pojo.CrmClass;
import cn.jxau.pojo.Lesson;
import cn.jxau.util.JdbcUtils;
import cn.jxau.vo.ClassVo;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class ClassDao implements IClassDao{
    QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());


    /**
     * 分页查询
     * @param pageStart
     * @param pageSize
     * @return
     */
    public List<ClassVo> selByPage(int pageStart, int pageSize){
        String sql = "select c.id,c.className,l.lessonName,c.beginTime,c.endTime,c.state,c.totalCount,c.goCount,c.leaveCount from crm_class c,crm_lessontype l  where c.lessonTypeID = l.id" +
                " limit ?,?";
        Object[]params={pageStart,pageSize};
        List<ClassVo> classVos = null;
        try {
            classVos = queryRunner.query(sql, new BeanListHandler<>(ClassVo.class), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classVos;
    }

    /**
     * 查询crm_class总记录数
     * @return
     */
    public long total(){
        String sql = "select count(*) from crm_class c,crm_lessontype l  where c.lessonTypeID = l.id";
        Object query = null;
        try {
            query = queryRunner.query(sql, new ScalarHandler());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        long total = Long.parseLong(query.toString());
        return total;

    }

    /**
     * 根据主键删除
     * @param id
     * @return
     */
    public int delById(int id){
        String sql = "delete from crm_class where id =?";
        Object param = id;
        int del = 0;
        try {
            del = queryRunner.update(sql, param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  del;
    }

    /**
     * 添加CrmClass
     * @param crmClass
     * @return
     */
    public int insert(CrmClass crmClass){
        /*CrmClass crmClass = new CrmClass();
        crmClass.setClassName(request.getParameter("className"));
        crmClass.setLessonTypeID(Integer.parseInt(request.getParameter("course")));
        crmClass.setBeginTime(request.getParameter("startTime"));
        crmClass.setEndTime(request.getParameter("endTime"));
        crmClass.setRemark(request.getParameter("mark"));
        * */
        String sql = "insert into crm_class(className,lessonTypeID,beginTime,endTime,remark) values(?,?,?,?,?)";
        Object[] params = {crmClass.getClassName(),crmClass.getLessonTypeID(), crmClass.getBeginTime(),crmClass.getEndTime(),crmClass.getRemark()};
        int ins = 0;
        try {
            ins = queryRunner.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ins;
    }

    /**
     * 根据主键查询CrmClass
     * @param id
     * @return
     */
    @Override
    public CrmClass selClass(int id) {
        String sql = "select * from crm_class where id = ?";
        Object param = id;
        CrmClass crmClass = null;
        try {
            crmClass = queryRunner.query(sql, new BeanHandler<>(CrmClass.class), param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return crmClass;
    }

    /**
     * 根据主键查询crm_class
     * @param id
     * @return
     */
    @Override
    public ClassVo selById(int id){
        String sql = "select c.id,c.className,l.lessonName,c.beginTime,c.endTime,c.state,c.totalCount,c.goCount,c.leaveCount,c.remark" +
                " from crm_class c,crm_lessontype l  where c.lessonTypeID = l.id and c.id = ?";
        Object param = id;
        ClassVo classVo= null;
        try {
            classVo = queryRunner.query(sql, new BeanHandler<>(ClassVo.class), param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classVo;

    }

    /**
     * 根据主键更新
     * @param crmClass
     * @return
     */
    public int updateById(CrmClass crmClass){
        String sql = "update crm_class set className = ?,lessonTypeID= ?,beginTime= ?,endTime= ?,remark= ? where id =?";
        Object[] params = {crmClass.getClassName(),crmClass.getLessonTypeID(), crmClass.getBeginTime(),crmClass.getEndTime(),crmClass.getRemark(),crmClass.getId()};
        int update = 0;
        try {
            update = queryRunner.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }

    /**
     * 通过班级名称查询是否存在该名称
     * @param className
     * @return
     */
    public int selByClassName(String className){
        String sql = "select count(*) from crm_class where className =?";
        Object param = className;
        Object query = null;
        try {
            query = queryRunner.query(sql, new ScalarHandler(), param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(query.toString());

    }

    /**
     * 高级查询
     * @param status
     * @param beginTime
     * @param endTime
     * @param pageStart
     * @param pageSize
     * @return
     */
    public List<ClassVo> selByCondition(String status,String beginTime,String endTime,int pageStart,int pageSize){
        String sql = "select c.id,c.className,l.lessonName,c.beginTime,c.endTime,c.state,c.totalCount,c.goCount,c.leaveCount " +
                "from crm_class c,crm_lessontype l  where c.lessonTypeID = l.id";
        if(status !=null && !status.equals("")){
            sql+=" and c.state = '"+status+"'";
        }
        if(beginTime !=null && !beginTime.equals("")){

            sql+=" and c.beginTime ='"+beginTime+"'";
        }
        if(endTime !=null && !endTime.equals("")){
            //sql+=" and d.id ="+depId;
            sql+=" and c.endTime = '"+endTime+"'";

        }
        sql +=" limit ?,?";
        System.out.println(sql);
        Object[] params = {pageStart,pageSize};
        List<ClassVo> classVos = null;
        try {
            classVos = queryRunner.query(sql, new BeanListHandler<>(ClassVo.class),params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classVos;


    }

    /**
     * 高级查询得到的总条数
     * @param status
     * @param beginTime
     * @param endTime
     * @return
     */
    public long totalByCondition(String status,String beginTime,String endTime){
        String sql = "select count(*) " +
                "from crm_class c,crm_lessontype l  where c.lessonTypeID = l.id";
        if(status !=null && !status.equals("")){
            sql+=" and c.state = '"+status+"'";
        }
        if(beginTime !=null && !beginTime.equals("")){
            sql+=" and c.beginTime ='"+beginTime+"'";
        }
        if(endTime !=null && !endTime.equals("")){
            //sql+=" and d.id ="+depId;
            sql+=" and c.endTime = '"+endTime+"'";

        }
        Object query = null;
        try {
            query = queryRunner.query(sql, new ScalarHandler());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        long total = Long.parseLong(query.toString());
        return total;
    }

    /**
     * 查询班级状态
     * @return
     */
    public List<CrmClass> selAllStatus(){
        String sql = "select  state from crm_class group by state";
        List<CrmClass> crmClasses = null;
        try {
            crmClasses = queryRunner.query(sql, new BeanListHandler<>(CrmClass.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return crmClasses;
    }
    public int updateFile(String uploadFileName ,String uploadPath,int id){
        String sql = "update crm_class set uploadFileName = ?,uploadPath = ? where id = ?";
        Object[] params={uploadFileName,uploadPath,id};
        int update = 0;
        try {
            update = queryRunner.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }
    public List<CrmClass> getClasses(){
        String sql = "select * from crm_class";
        List<CrmClass> crmClasses = null;
        try {
            crmClasses = queryRunner.query(sql, new BeanListHandler<>(CrmClass.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return crmClasses;
    }

}
