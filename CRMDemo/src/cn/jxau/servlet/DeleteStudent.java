package cn.jxau.servlet;

import cn.jxau.dao.IStudentDao;
import cn.jxau.dao.StuDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteStudent")
public class DeleteStudent extends HttpServlet {
    IStudentDao iStudentDao =new StuDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("sid"));
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        System.out.println(pageNumber);
        int i = iStudentDao.deleteById(id);
        if (i == 1){
            response.sendRedirect("ShowStudent?pageNumber="+pageNumber);

        }else {
            request.setAttribute("msg","删除失败！");
            request.getRequestDispatcher("ShowStudent?pageNumber="+pageNumber).forward(request,response);
        }
    }
}
