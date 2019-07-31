package cn.jxau.servlet;

import cn.jxau.pojo.Lesson;
import cn.jxau.pojo.PageInfo;
import cn.jxau.service.Impl.LessonServiceImpl;
import cn.jxau.service.LessonService;
import com.alibaba.fastjson.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "LessonController",urlPatterns = {"/AjaxGetJson","/testMap","/showLessons","/addLesson","/deleteLesson","/updateLesson","/getLesson"})
public class LessonController extends HttpServlet {
    private LessonService lessonService = new LessonServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String requestURI = request.getRequestURI();
        if (requestURI.contains("AjaxGetJson")){
            AjaxGetJson(request,response);
        }
        if (requestURI.contains("testMap")){
            testMap(request,response);
        }
        if (requestURI.contains("showLessons")){
            showLessons(request,response);
        }
        if (requestURI.contains("addLesson")){
            addLesson(request,response);
        }
        if (requestURI.contains("deleteLesson")){
            deleteLesson(request,response);
        }
        if (requestURI.contains("updateLesson")){
            updateLesson(request,response);
        }
        if (requestURI.contains("getLesson")){
            getLesson(request,response);
        }

    }

    private void getLesson(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Lesson lesson = lessonService.getLesson(id);
        if (lesson != null) {
            request.setAttribute("lesson",lesson);
            request.getRequestDispatcher("do_course.jsp").forward(request, response);
        } else {
            request.setAttribute("msg","页面加载失败，请重试！");
            showLessons(request,response);
        }

    }

    private void updateLesson(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String lessonName = request.getParameter("lessonName");
        Double lessonCost =Double.parseDouble(request.getParameter("lessonCost")) ;
        short totalTime = Short.parseShort(request.getParameter("totalTime"));
        String remark = request.getParameter("remark");
        Lesson lesson = new Lesson(id,lessonName,lessonCost,totalTime,remark);
        int i = lessonService.updateLesson(lesson);
        if (i == 1) {
            request.setAttribute("msg","更新成功");
            showLessons(request, response);
        }else {
            request.setAttribute("msg","更新失败，请重试！");
            showLessons(request, response);
        }

    }

    private void deleteLesson(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int i = lessonService.deleteLesson(id);
        if (i == 1) {
            request.setAttribute("msg","删除成功");
            showLessons(request, response);
        }else {
            request.setAttribute("msg","删除失败，请重试！");
            showLessons(request, response);
        }

    }

    private void addLesson(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lessonName = request.getParameter("lessonName");
        Double lessonCost =Double.parseDouble(request.getParameter("lessonCost")) ;
        short totalTime = Short.parseShort(request.getParameter("totalTime"));
        String remark = request.getParameter("remark");
        Lesson lesson = new Lesson(lessonName,lessonCost,totalTime,remark);
        int i = lessonService.addLesson(lesson);
        if (i == 1) {
            request.setAttribute("msg","添加成功");
            showLessons(request, response);
        }else {
            request.setAttribute("msg","添加失败，请重试！");
            showLessons(request, response);
        }

    }

    private void showLessons(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNumber = 1;
        int pageSize = 4;
        if(request.getParameter("pageNumber")!=null){
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        }
        PageInfo pageInfo = lessonService.showPage(pageNumber, pageSize);
        if(pageInfo.getTotal()>0){
            request.setAttribute("pageInfo",pageInfo);
            request.getRequestDispatcher("course_manage.jsp").forward(request, response);
        }else {
            request.setAttribute("msg","内容加载失败，请重试！");
            request.getRequestDispatcher("course_manage.jsp").forward(request, response);
        }
    }

    private void testMap(HttpServletRequest request, HttpServletResponse response) {

    }

    private void AjaxGetJson(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Lesson> lessons = lessonService.getAll();
        JSONArray jsonArray = new JSONArray();
        for (Lesson lesson:lessons) {
            jsonArray.add(lesson);
        }
        String s = jsonArray.toString();
        System.out.println(s);
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/javascript");
        response.getWriter().print(s);
    }
}
