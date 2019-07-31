package cn.jxau.servlet;

import cn.jxau.pojo.PageInfo;
import cn.jxau.service.*;
import cn.jxau.service.Impl.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import sun.jvm.hotspot.debugger.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PageServlet",urlPatterns = {"/pageClasses","/pageStaffs","/pageLessons","/pageDepartments","/pagePosts","/pageStudents"})
public class PageServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        int pageNumber = 1;//默认显示第一页
        int pageSize = 5;//默认显示3条数据
        String pageNumberString = request.getParameter("pageNumber");
        String pageSzieString = request.getParameter("pageSize");
        if(pageNumberString != null){
            pageNumber = Integer.parseInt(pageNumberString);
        }
        if (pageSzieString != null){
            pageSize = Integer.parseInt(pageSzieString);
        }
        String requestURI = request.getRequestURI();
        PageInfo pageInfo = null;
        if (requestURI.contains("pageClasses")){
             pageInfo = pageClasses(pageNumber, pageSize);
             pageInfo.setPage("class_manage.jsp");
        }if (requestURI.contains("pageStaffs")){
             pageInfo = pageStaffs( pageNumber, pageSize);
            pageInfo.setPage("employer_manage.jsp");
        }if (requestURI.contains("pageLessons")){
             pageInfo = pageLessons( pageNumber, pageSize);
             pageInfo.setPage("course_manage.jsp");
        }if (requestURI.contains("pageDepartments")){
             pageInfo = pageDepartments( pageNumber, pageSize);
            pageInfo.setPage("dept_manage.jsp");
        }if (requestURI.contains("pagePosts")){
             pageInfo = pagePosts(pageNumber, pageSize);
             pageInfo.setPage("post_manage.jsp");
        }if (requestURI.contains("pageStudents")){
             pageInfo = pageStudents(pageNumber, pageSize);
             pageInfo.setPage("student_manage.jsp");
        }
        if(pageInfo.getList().size()>0){
            request.setAttribute("pageInfo",pageInfo);
            request.getRequestDispatcher(pageInfo.getPage()).forward(request, response);
        }else{
            request.setAttribute("msg","没有查询到需要的数据！");
            request.getRequestDispatcher(pageInfo.getPage()).forward(request, response);
        }
    }

    private PageInfo pageClasses(int pageNumber, int pageSize) {
        ClassService classService = new ClassServiceImpl();
        PageInfo pageInfo = classService.showPage(pageNumber, pageSize);
        return pageInfo;
    }
    private PageInfo pageStaffs( int pageNumber, int pageSize) {
        StaffService staffService = new StaffServiceImpl();
        PageInfo pageInfo = staffService.showPage(pageNumber, pageSize);
        return pageInfo;
    }
    private PageInfo pageLessons( int pageNumber, int pageSize) {
        LessonService lessonService = new LessonServiceImpl();
        lessonService.getAll();
        return null;
    }
    private PageInfo pageDepartments( int pageNumber, int pageSize) {
        DepartmentService departmentService = new DepartmentServiceImpl();
        PageInfo pageInfo = departmentService.showDepartment(pageNumber, pageSize);
        return pageInfo;
    }
    private PageInfo pagePosts(int pageNumber, int pageSize) {
        PostService postService = new PostServiceImpl();
        PageInfo pageInfo = postService.showPage(pageNumber, pageSize);
        return pageInfo;
    }
    private PageInfo pageStudents(int pageNumber, int pageSize) {
        StudentService studentService = new StudentServiceImpl();
        PageInfo pageInfo = studentService.showStudent(pageNumber, pageSize);
        return pageInfo;
    }

}
