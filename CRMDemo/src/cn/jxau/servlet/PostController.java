package cn.jxau.servlet;

import cn.jxau.pojo.Department;
import cn.jxau.pojo.PageInfo;
import cn.jxau.pojo.Post;
import cn.jxau.service.DepartmentService;
import cn.jxau.service.Impl.DepartmentServiceImpl;
import cn.jxau.service.Impl.PostServiceImpl;
import cn.jxau.service.PostService;
import cn.jxau.util.RandomUtil;
import com.alibaba.fastjson.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "postController",urlPatterns = {"/showPost","/addPost","/updPost","/delPost","/selPost","/showDeps","/ajaxGetByDepId","/getPosts","/isExistPost"})

public class PostController extends HttpServlet {
    private PostService postService = new PostServiceImpl();
    private DepartmentService departmentService = new DepartmentServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        String requestURI = request.getRequestURI();
        if (requestURI.contains("showPost")){
           showPost(request,response);
        }
        if (requestURI.contains("addPost")){
            addPost(request,response);
        }
        if (requestURI.contains("updPost")){
            updPost(request,response);
        }
        if (requestURI.contains("delPost")){
            delPost(request,response);
        }
        if (requestURI.contains("selPost")){
            selPost(request,response);
        }
        if (requestURI.contains("showDeps")){
            showDeps(request,response);
        }
        if (requestURI.contains("ajaxGetByDepId")){
            ajaxGetByDepId(request,response);
        }
        if (requestURI.contains("getPosts")){
            getPosts(request,response);
        }
        if (requestURI.contains("isExistPost")){
            isExistPost(request,response);
        }
    }

    private void isExistPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int id = Integer.parseInt(request.getParameter("depId"));
        String postName = request.getParameter("postName");
        int existPost = postService.isExistPost(id, postName);
        response.getWriter().print(existPost);
    }

    private void getPosts(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Post> posts = postService.getPosts();
        JSONArray jsonArray = new JSONArray();
        for (Post post:posts) {
            jsonArray.add(post);
        }
        String s = jsonArray.toString();
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/javascript");
        response.getWriter().print(s);
    }

    private void ajaxGetByDepId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int depId = Integer.parseInt(request.getParameter("depId"));
        List<Post> posts = postService.getPostByDepId(depId);
        JSONArray jsonArray = new JSONArray();
        for (Post post:posts) {
            jsonArray.add(post);
        }
        String s = jsonArray.toString();
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/javascript");
        response.getWriter().print(s);
    }

    private void showDeps(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Department> departments = departmentService.getAll();
        request.setAttribute("departments",departments);
        System.out.println(departments);
        request.getRequestDispatcher("add_post.jsp").forward(request,response);
    }

    private void addPost(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("add");
        Post post = new Post();
        int id= RandomUtil.getInt();
        post.setPostId(id);
        post.setPostName(request.getParameter("postName"));
        post.setDepId(Integer.parseInt(request.getParameter("depId")));
        int i = postService.addPost(post);
        if (i == 1){
            showPost(request,response);
        }else{
            request.setAttribute("msg","添加失败！");
            showPost(request,response);
        }
    }

    private void delPost(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        int del = postService.delPost(id);
        if(del == 1){
            showPost(request,response);
        }else{
            request.setAttribute("msg","删除失败！");
            showPost(request,response);
        }
    }

    private void selPost(HttpServletRequest request, HttpServletResponse response) {
        Post post = postService.getPost(Integer.parseInt(request.getParameter("id")));
        List<Department> departments = departmentService.getAll();
        try {
            request.setAttribute("post",post);
            request.setAttribute("departments",departments);
            request.getRequestDispatcher("do_post.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updPost(HttpServletRequest request, HttpServletResponse response) {
        Post post = new Post();
        post.setPostId(Integer.parseInt(request.getParameter("postId")));
        post.setDepId(Integer.parseInt(request.getParameter("depId")));
        post.setPostName(request.getParameter("postName"));
        int i = postService.updatePost(post);
        if(i == 1){
            showPost(request,response);
        }else{
            request.setAttribute("msg","更新失败！");
            showPost(request,response);
        }

    }

    private void showPost(HttpServletRequest request, HttpServletResponse response) {
        int pageNumber = 1;//默认显示第一页
        int pageSize = 4;//默认显示6条数据
        String pageNumberStr = request.getParameter("pageNumber");
        String pageSzieStr = request.getParameter("pageSize");
        if(pageNumberStr != null){
            pageNumber = Integer.parseInt(pageNumberStr);
        }
        if (pageSzieStr != null){
            pageSize = Integer.parseInt(pageSzieStr);
        }
        PageInfo pageInfo = postService.showPage(pageNumber, pageSize);
        try {
            request.setAttribute("pageInfo",pageInfo);
            request.getRequestDispatcher("post_manage.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
