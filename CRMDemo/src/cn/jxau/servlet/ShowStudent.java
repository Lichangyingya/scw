package cn.jxau.servlet;

import cn.jxau.dao.StudentDao;
import cn.jxau.pojo.PageInfo;
import cn.jxau.pojo.Student;
import cn.jxau.service.Impl.StudentServiceImpl;
import cn.jxau.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name="showStudent",urlPatterns = {"/showStudent","/upgrade"})
public class ShowStudent extends HttpServlet {
    StudentService studentService = new StudentServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String requestURI = request.getRequestURI();
        if (requestURI.contains("upgrade")){
            showStudent(request,response);
            request.getRequestDispatcher("up_class.jsp").forward(request, response);
        }
        if (requestURI.contains("showStudent")){
            showStudent(request,response);
            request.getRequestDispatcher("student_manage.jsp").forward(request, response);
        }


    }

    private void showStudent(HttpServletRequest request, HttpServletResponse response) {
        int pageNumber = 1;//默认显示第一页
        int pageSize = 6;//默认显示6条数据
        String pageNumberStr = request.getParameter("pageNumber");
        String pageSzieStr = request.getParameter("pageSize");
        if(pageNumberStr != null){
            pageNumber = Integer.parseInt(pageNumberStr);
        }
        if (pageSzieStr != null){
            pageSize = Integer.parseInt(pageSzieStr);
        }

        PageInfo pageInfo = studentService.showStudent(pageNumber, pageSize);
        request.setAttribute("pageInfo",pageInfo);
    }
}
