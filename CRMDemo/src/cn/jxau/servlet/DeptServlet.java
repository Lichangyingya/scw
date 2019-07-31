package cn.jxau.servlet;

import cn.jxau.dao.DepartmentDao;
import cn.jxau.pojo.Department;
import cn.jxau.pojo.PageInfo;
import cn.jxau.service.DepartmentService;
import cn.jxau.service.Impl.DepartmentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/dept")
public class DeptServlet extends HttpServlet {
    DepartmentService departmentService = new DepartmentServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        PageInfo pageInfo = departmentService.showDepartment(pageNumber, pageSize);
        request.setAttribute("pageInfo",pageInfo);
        request.getRequestDispatcher("dept_manage.jsp").forward(request, response);
    }
}
