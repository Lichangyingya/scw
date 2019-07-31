package cn.jxau.service.Impl;

import cn.jxau.dao.DeptDao;
import cn.jxau.dao.IDepartmentDao;
import cn.jxau.dao.PostDao;
import cn.jxau.pojo.Department;
import cn.jxau.pojo.PageInfo;
import cn.jxau.pojo.Post;
import cn.jxau.service.PostService;
import cn.jxau.vo.PostVo;
import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.List;

public class PostServiceImpl implements PostService {
    private PostDao postDao = new PostDao();
    private IDepartmentDao iDepartmentDao = new DeptDao();

    @Override
    public int isExistPost(int depId, String postName) {
        return postDao.selByDepIdAndPostName(depId,postName);
    }

    @Override
    public List<Post> getPosts() {
        return postDao.getAll();
    }

    @Override
    public List<Post> getPostByDepId(int depId) {
        return postDao.selByDepId(depId);
    }

    /**
     * 分页显示
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo showPage(int pageNumber, int pageSize) {
        int pageStart = (pageNumber-1)*pageSize;
        List<Post> posts = postDao.selByPage(pageStart, pageSize);
        List<PostVo> postVos = new ArrayList<>();
        for (Post post:posts) {
            Department department = iDepartmentDao.selById(post.getDepId());
            PostVo postVo = new PostVo();
            postVo.setPostId(post.getPostId());
            postVo.setDepId(post.getDepId());
            postVo.setPostName(post.getPostName());
            postVo.setDepartment(department);
            postVos.add(postVo);
        }
        long total = postDao.selTotal();
        long totalPage=total%pageSize==0?total/pageSize:total/pageSize+1;
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNumber(pageNumber);
        pageInfo.setPageStart(pageStart);
        pageInfo.setPageSize(pageSize);
        pageInfo.setList(postVos);
        pageInfo.setTotal(total);
        pageInfo.setTotalPage(totalPage);
        return pageInfo;
    }

    /**
     * 根据主键查询单个post 对象
     * @param id
     * @return
     */
    @Override
    public Post getPost(int id) {
        Post post = postDao.selById(id);
        return post;
    }

    /**
     * 修改单行数据
     * @param post
     * @return
     */
    @Override
    public int updatePost(Post post) {
        return postDao.updById(post);
    }

    /**
     * 添加一行数据
     * @param post
     * @return
     */
    @Override
    public int addPost(Post post) {
        return postDao.ins(post);
    }

    /**
     * 删除一行数据
     * @param id
     * @return
     */
    @Override
    public int delPost(int id) {
        return postDao.delById(id);
    }
}
