package cn.jxau.service;

import cn.jxau.pojo.PageInfo;
import cn.jxau.pojo.Post;

import java.util.List;

public interface PostService {
    PageInfo showPage(int pageNumber, int Pagesize);

    Post getPost(int id);

    int updatePost(Post post);

    int addPost(Post post);

    int delPost(int id);

    List<Post> getPostByDepId(int depId);

    List<Post> getPosts();

    int isExistPost(int depId,String postName);
}
