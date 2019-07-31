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

@WebServlet("/updateStudent")
public class UpdateStudentServlet extends HttpServlet {
    StudentService studentService = new StudentServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        request.setCharacterEncoding("utf-8");
        StudentVo studentVo = new StudentVo();
        int id = Integer.parseInt(request.getParameter("sid"));
        String userName = request.getParameter("userName");
        String code = request.getParameter("code");
        String sex = request.getParameter("sex");
        int classId = Integer.parseInt(request.getParameter("class"));
        String postTime = request.getParameter("PostTime");
        String endTime = request.getParameter("endTime");
        String mark = request.getParameter("mark");
        studentVo.setId(id);
        studentVo.setStusex(sex);
        studentVo.setStuname(userName);
        studentVo.setRemark(mark);
        studentVo.setClassid(classId);
        studentVo.setBeginTimeStr(postTime);
        studentVo.setJobTimeStr(endTime);
        studentVo.setStucode(code);

        boolean b = studentService.UpdateById(studentVo);
        if(b){
            response.sendRedirect("ShowStudent");
        }else{
            request.setAttribute("msg","更新失败！");
            request.getRequestDispatcher("do_student.jsp").forward(request, response);
        }

    }
}
