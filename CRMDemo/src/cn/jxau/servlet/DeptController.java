package cn.jxau.servlet;

import cn.jxau.pojo.Department;
import cn.jxau.pojo.PageInfo;
import cn.jxau.pojo.Post;
import cn.jxau.service.DepartmentService;
import cn.jxau.service.Impl.DepartmentServiceImpl;
import com.alibaba.fastjson.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "/deptController",urlPatterns = {"/addDept","/delDept","/selDept","/updateDept","/insDept","/pageDept","/ajaxGetAll","/isExistDepName"})
public class DeptController extends HttpServlet {
    private DepartmentService departmentService = new DepartmentServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String requestURI = request.getRequestURI();
        if (requestURI.contains("addDept")){
            add(request,response);
        }
        if (requestURI.contains("delDept")){
            delById(request,response);
        }
        if (requestURI.contains("updateDept")){
            updDept(request,response);
        }
        if (requestURI.contains("selDept")){
            selDept(request,response);
        }
        if (requestURI.contains("pageDept")){
            showDept(request, response);
        }
        if (requestURI.contains("ajaxGetAll")){
            ajaxGetAll(request, response);
        }
        if (requestURI.contains("isExistDepName")){
            isExistDepName(request, response);
        }




    }

    private void isExistDepName(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String depName = request.getParameter("depName");
        int existDepName = departmentService.isExistDepName(depName);
        response.getWriter().print(existDepName);
    }

    private void ajaxGetAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Department> departments = departmentService.getAll();
        JSONArray jsonArray = new JSONArray();
        for (Department department:departments) {
            jsonArray.add(department);
        }
        String s = jsonArray.toString();
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/javascript");
        response.getWriter().print(s);
    }

    private void delById(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        int i = departmentService.delDept(id);
        if(i == 1){
            showDept(request,response);
        }else{

            request.setAttribute("msg","删除失败！");
            showDept(request,response);
        }
    }

    private void updDept(HttpServletRequest request, HttpServletResponse response) {
        Department department = new Department();
        department.setId(Integer.parseInt(request.getParameter("id")));
        department.setDepName(request.getParameter("depName"));
        department.setIsCancel(0);
        int i = departmentService.updateDept(department);
        if (i == 1){
            showDept(request,response);
        }else{
            request.setAttribute("msg","更新失败！");
            showDept(request,response);
        }

    }

    private void showDept(HttpServletRequest request, HttpServletResponse response) {
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
        try {
            request.setAttribute("pageInfo",pageInfo);
            request.getRequestDispatcher("dept_manage.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void add(HttpServletRequest request,HttpServletResponse response){
        Department department = new Department();
        department.setDepName(request.getParameter("depName"));
        department.setIsCancel(0);
        int i = departmentService.addDept(department);
        if (i == 1){
            showDept(request, response);
        }else{
            try {
                request.setAttribute("msg","添加部门失败！");
                request.getRequestDispatcher("add_dept.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void selDept(HttpServletRequest request,HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        Department department = departmentService.selById(id);
        try {
            request.setAttribute("department",department);
            request.getRequestDispatcher("do_dept.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
