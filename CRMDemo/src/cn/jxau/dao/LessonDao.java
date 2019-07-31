package cn.jxau.dao;

import cn.jxau.pojo.Lesson;
import cn.jxau.util.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class LessonDao {
    QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());

    /**
     * 得到所有课程
     * @return
     */
    public List<Lesson> getAll(){
        String  sql = "select * from crm_lessonType";
        List<Lesson> lessons = null;
        try {
            lessons = queryRunner.query(sql, new BeanListHandler<>(Lesson.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lessons;
    }

    /**
     * 分页查询
     * @param pageStart
     * @param pageNumber
     * @return
     */
    public List<Lesson> selByPage(int pageStart,int pageNumber){
        String sql = "select * from crm_lessonType limit ?,?";
        Object [] params={pageStart,pageNumber};
        List<Lesson> lessons = null;
        try {
            lessons = queryRunner.query(sql, new BeanListHandler<>(Lesson.class),params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lessons;

    }

    /**
     * 添加课程
     * @param lesson
     * @return
     */
    public int insLesson(Lesson lesson){
        String sql = "insert into crm_lessonType(lessonName,lessonCost,totalTime,remark) values(?,?,?,?)";
        Object[] params = {lesson.getLessonName(),lesson.getLessonCost(), lesson.getTotalTime(),lesson.getRemark()};
        int update = 0;
        try {
            update = queryRunner.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }

    /**
     * 通过主键删除
     * @param id
     * @return
     */
    public int delById(int id) {
        String sql = "delete from crm_lessonType where id = ?";
        Object param = id;
        int update = 0;
        try {
            update = queryRunner.update(sql, param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }

    /**
     * 通过主键更新
     * @param lesson
     * @return
     */
    public int updateById(Lesson lesson){
        String sql = "update crm_lessonType set lessonName = ?,lessonCost = ?,totalTime = ?,remark = ? where id = ?";
        Object[] params = {lesson.getLessonName(),lesson.getLessonCost(), lesson.getTotalTime(),lesson.getRemark(), lesson.getId()};
        int update = 0;
        try {
            update = queryRunner.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;

    }

    /**
     * 用过主键查询
     * @param id
     * @return
     */
    public Lesson selById(int id){
        String sql = "select * from crm_lessonType where id = ?";
        Object param = id;
        Lesson lesson = null;
        try {
            lesson = queryRunner.query(sql, new BeanHandler<>(Lesson.class), param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lesson;
    }
    public long selTotal(){
        String sql = "select count(*) from crm_lessonType";
        Object query = null;
        try {
            query = queryRunner.query(sql, new ScalarHandler());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Long.parseLong(query.toString());
    }

}
