package cn.jxau.servlet;

import cn.jxau.service.Impl.StudentServiceImpl;
import cn.jxau.service.StudentService;
import cn.jxau.vo.StudentVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addStudent")
public class AddStudent extends HttpServlet {
    StudentService studentService = new StudentServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        StudentVo studentVo = new StudentVo();
        studentVo.setStuname(request.getParameter("userName"));
        studentVo.setStucode(request.getParameter("code"));
        studentVo.setStusex(request.getParameter("sex"));
        studentVo.setClassid(Integer.parseInt(request.getParameter("class")));
        studentVo.setBeginTimeStr(request.getParameter("PostTime"));
        studentVo.setJobTimeStr(request.getParameter("endTime"));
        int insert = studentService.insert(studentVo);
        if (insert == 1){
            response.sendRedirect("ShowStudent");
        }else{
            request.setAttribute("mas","添加学生失败");
            request.getRequestDispatcher("add_student.jsp").forward(request, response);
        }

    }
}
