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

@WebServlet("/showUpdate")
public class ShowUpdate extends HttpServlet {
    StudentService studentService = new StudentServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("sid"));
        System.out.println(id);
        StudentVo studentVo = studentService.selectById(id);
        System.out.println(studentVo);
        request.setAttribute("studentVo",studentVo);
        request.getRequestDispatcher("do_student.jsp").forward(request, response);

    }
}
