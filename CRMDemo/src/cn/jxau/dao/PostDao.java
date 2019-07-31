package cn.jxau.dao;

import cn.jxau.pojo.Post;
import cn.jxau.util.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class PostDao {
    QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
    public List<Post> selByPage(int pageStart, int pageSize){

        String sql ="select postId,postName,depId from crm_post limit "+pageStart+","+pageSize;
        List<Post> posts = null;
        try {
            posts = queryRunner.query(sql, new BeanListHandler<>(Post.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }
    public Post selById(int id){
        String sql = "select * from crm_post where postId = ?";
        Object param = id;
        Post post = null;
        try {
            post = queryRunner.query(sql, new BeanHandler<>(Post.class), param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;

    }
    public int ins(Post post){
        String sql = "insert into crm_post(postId,postName,depId) values(?,?,?)";
        Object[] params = {post.getPostId(),post.getPostName(),post.getDepId()};
        int ins = 0;
        try {
            ins = queryRunner.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ins;

    }
    public int updById(Post post){
        String sql = "update crm_post set postName = ? where postId = ?";
        Object[] params = {post.getPostName(),post.getPostId()};
        int update = 0;
        try {
            update = queryRunner.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  update;
    }
    public int delById(int id){
        String sql = "delete from crm_post where postId = ?";
        Object param = id;
        int del = 0;
        try {
            del = queryRunner.update(sql, param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return del;
    }
    public long selTotal(){
        String sql = "select count(*) from crm_post";
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
     * 根据部门ID 查询职位
     * @param depId
     * @return
     */
    public List<Post> selByDepId(int depId){
        String sql ="select * from crm_post where depID  =  ?";
        Object param = depId;
        List<Post> posts = null;
        try {
            posts = queryRunner.query(sql, new BeanListHandler<>(Post.class), param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }
    public List<Post> getAll(){
        String sql ="select postId,postName,depId from crm_post";
        List<Post> posts = null;
        try {
            posts = queryRunner.query(sql, new BeanListHandler<>(Post.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }
    public int selByDepIdAndPostName(int depId,String postName){
        String sql = "select count(*) from crm_post where depId=? and postName = ?";
        Object[] params = {depId,postName};
        Object query = null;
        try {
            query = queryRunner.query(sql, new ScalarHandler(), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(query.toString());
    }
}
