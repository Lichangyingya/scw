package cn.jxau.servlet;

import cn.jxau.pojo.PageInfo;
import cn.jxau.pojo.Student;
import cn.jxau.service.Impl.StudentServiceImpl;
import cn.jxau.service.StudentService;
import cn.jxau.vo.StudentVo;
import com.alibaba.fastjson.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "StudentController" ,urlPatterns = {"/selStudentByCondition","/ajaxStudentStatus","/studentMessage","/advancedStudentQuery","/changeStatus"})
public class StudentController extends HttpServlet {
    //changeStatus
    private StudentService studentService = new StudentServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String requestURI = request.getRequestURI();
        if(requestURI.contains("selStudentByCondition")){
            selStudentByCondition(request,response);
        }
        if(requestURI.contains("ajaxStudentStatus")){
            ajaxStudentStatus(request,response);
        }
        if(requestURI.contains("studentMessage")){
            studentMessage(request,response);
        }
        if(requestURI.contains("advancedStudentQuery")){
            advancedStudentQuery(request,response);
        }
        if(requestURI.contains("changeStatus")){
            updStatus(request,response);
        }


    }

    private void updStatus(HttpServletRequest request, HttpServletResponse response) {
        String status = request.getParameter("status");
        System.out.println(status);
        String checkboxs  = request.getParameter("checkboxs");
        System.out.println(checkboxs);
        int i = studentService.updStatus(status,checkboxs);
        try {
            System.out.println(i);
            response.getWriter().print(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void advancedStudentQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNumber = 1;
        int pageSize = 4;
        if(request.getParameter("pageNumber")!=null){
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        }
        String className = request.getParameter("className");
        String stuStatus = request.getParameter("stuState");
        String stuName = request.getParameter("stuName");
        request.getSession().setAttribute("className",className);
        request.getSession().setAttribute("stuState",stuStatus);
        request.getSession().setAttribute("stuName",stuName);
        PageInfo pageInfo = studentService.advancedStudentQuery(className,stuStatus, stuName, pageNumber, pageSize);
        if (pageInfo.getTotal()>0) {
            request.setAttribute("pageInfo",pageInfo);
            request.getRequestDispatcher("up_class_query.jsp").forward(request, response);
        }else {
            request.setAttribute("msg","没有查询到符合条件的数据");
            request.getRequestDispatcher("up_class_query.jsp").forward(request, response);
        }
    }

    private void studentMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        StudentVo studentVo = studentService.selectById(id);
        request.setAttribute("studentVo",studentVo);
        request.getRequestDispatcher("view.jsp").forward(request, response);
    }

    private void ajaxStudentStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Student> students = studentService.queryStuStatus();
        JSONArray jsonArray  = new JSONArray();
        for (Student student:students) {
            jsonArray.add(student);
        }
        String s = jsonArray.toString();
        response.setContentType("text/javascript");
        response.getWriter().print(s);
    }

    private void selStudentByCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNumber = 1;
        int pageSize = 4;
        if(request.getParameter("pageNumber")!=null){
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        }
        String className = request.getParameter("className");
        String stuName = request.getParameter("stuName");
        request.getSession().setAttribute("className",className);
        request.getSession().setAttribute("stuName",stuName);
        PageInfo pageInfo = studentService.selByCondition(className, stuName, pageNumber, pageSize);
        if (pageInfo.getTotal()>0){
            request.setAttribute("pageInfo",pageInfo);
            request.getRequestDispatcher("query_student.jsp").forward(request, response);
        }else {
            request.setAttribute("msg","没有查询到符合条件的数据");
            request.getRequestDispatcher("query_student.jsp").forward(request, response);
        }

    }

}
