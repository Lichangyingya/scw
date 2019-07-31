package cn.jxau.servlet;


import cn.jxau.dao.PostDao;
import cn.jxau.pojo.Post;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;
import java.io.IOException;
import java.util.List;

@WebServlet("/test")
public class TestServlet extends HttpServlet {
    private PostDao postDao  = new PostDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String str;
        int depId = Integer.parseInt(request.getParameter("depId"));
        List<Post> posts = postDao.selByDepId(depId);
        JSONArray jsonArray = new JSONArray();
        for (Post post:posts) {
            jsonArray.add(post);
        }
        String s = jsonArray.toString();
        System.out.println(s);
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/javascript");
        response.getWriter().print(s);


    }
}
