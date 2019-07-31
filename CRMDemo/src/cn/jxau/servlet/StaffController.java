package cn.jxau.servlet;

import cn.jxau.pojo.PageInfo;
import cn.jxau.pojo.Staff;
import cn.jxau.service.Impl.StaffServiceImpl;
import cn.jxau.service.StaffService;
import cn.jxau.vo.StaffVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "StaffController",urlPatterns = {"/showStaff","/updStaff","/addStaff","/delStaff","/getStaff","/queryStaff","/isExistLoginName","/staffLogin"})
public class StaffController extends HttpServlet {
    private StaffService staffService = new StaffServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String requestURI = request.getRequestURI();
        if (requestURI.contains("showStaff")){
            showStaff(request,response);
        }
        if (requestURI.contains("updStaff")){
            updStaff(request,response);
        }
        if (requestURI.contains("addStaff")){
            addStaff(request,response);
        }
        if (requestURI.contains("delStaff")){
            delStaff(request,response);
        }
        if (requestURI.contains("getStaff")){
            getStaff(request,response);
        }
        if (requestURI.contains("queryStaff")){
            queryStaff(request,response);
        }
        if (requestURI.contains("isExistLoginName")){
            isExistLoginName(request,response);
        }
        if (requestURI.contains("staffLogin")){
            staffLogin(request,response);
        }

    }

    private void staffLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        boolean login = staffService.login(userName, password);
        if(login){
            request.getSession().setAttribute("loginUser",userName);
            Cookie cookie = new Cookie("loginUser", userName+"-"+password);
            cookie.setMaxAge(60*60*24*30);
            cookie.setPath("/");
            response.addCookie(cookie);
            response.sendRedirect("index.jsp");
        }else{
            request.setAttribute("msg","用户名或密码错误");
            request.getRequestDispatcher("login.jsp").forward(request, response);

        }

    }

    private void isExistLoginName(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String loginName = request.getParameter("loginName");
        int existLoginName = staffService.isExistLoginName(loginName);
        response.getWriter().println(existLoginName);
    }

    private void queryStaff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNumber = 1;//默认显示第一页
        int pageSize = 5;//默认显示3条数据
        String pageNumberString = request.getParameter("pageNumber");
        String pageSzieString = request.getParameter("pageSize");
        String depId = request.getParameter("depId");
        String postId = request.getParameter("postId");
        String staffName = request.getParameter("staffName");
        request.getSession().setAttribute("depId",depId);
        request.getSession().setAttribute("postId",postId);
        request.getSession().setAttribute("staffName",staffName);
        if(pageNumberString != null){
            pageNumber = Integer.parseInt(pageNumberString);
        }
        if (pageSzieString != null){
            pageSize = Integer.parseInt(pageSzieString);
        }
        PageInfo pageInfo = staffService.selByCondition(depId,postId,staffName,pageNumber,pageSize);
        if(pageInfo.getList().size()>0){
            request.setAttribute("pageInfo",pageInfo);
            request.getRequestDispatcher("queryStaff.jsp").forward(request, response);
        }else{
            request.setAttribute("msg","没有符合条件的数据！");
            request.getRequestDispatcher("queryStaff.jsp").forward(request, response);
        }


    }
    private void getStaff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int staffId = Integer.parseInt(request.getParameter("staffId"));
        Staff staff = staffService.selById(staffId);
        Date onDutyDate = staff.getOnDutyDate();
        System.out.println(onDutyDate);
        String[] s = onDutyDate.toString().split(" ");
        staff.setOnDutyDateStr(s[0]);
        System.out.println(staff.getOnDutyDateStr());
        request.setAttribute("staff",staff);
        request.getRequestDispatcher("do_employee.jsp").forward(request, response);

    }

    private void delStaff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int staffId = Integer.parseInt(request.getParameter("staffId"));
        int i = staffService.delStaff(staffId);
        if (i == 1) {
            showStaff(request, response);
        }else{
            request.setAttribute("msg","删除失败！");
            showStaff(request, response);
        }

    }

    private void addStaff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Staff staff = new Staff();
        staff.setLoginName(request.getParameter("loginName"));
        staff.setLoginPwd(request.getParameter("pwd"));
        staff.setStaffName(request.getParameter("userName"));
        staff.setGender(request.getParameter("sex"));
        staff.setPostId(Integer.parseInt(request.getParameter("post")));
        staff.setOnDutyDateStr(request.getParameter("PostTime"));
        int i = staffService.addStaff(staff);
        if (i == 1) {
            showStaff(request, response);
        }else{
            request.setAttribute("msg","添加失败！");
            showStaff(request, response);
        }
    }

    private void updStaff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Staff staff = new Staff();

        staff.setLoginName(request.getParameter("loginName"));
        staff.setLoginPwd(request.getParameter("pwd"));
        staff.setStaffName(request.getParameter("userName"));
        staff.setGender(request.getParameter("sex"));
        staff.setPostId(Integer.parseInt(request.getParameter("post")));
        staff.setOnDutyDateStr(request.getParameter("PostTime"));
        int i = staffService.updateStaff(staff);
        if (i == 1) {
            showStaff(request, response);
        }else{
            request.setAttribute("msg","更新失败！");
            showStaff(request, response);
        }


    }
    private void showStaff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNumber = 1;//默认显示第一页
        int pageSize = 5;//默认显示5条数据
        String pageNumberString = request.getParameter("pageNumber");
        String pageSzieString = request.getParameter("pageSize");
        if(pageNumberString != null){
            pageNumber = Integer.parseInt(pageNumberString);
        }
        if (pageSzieString != null){
            pageSize = Integer.parseInt(pageSzieString);
        }
        PageInfo pageInfo = staffService.showPage(pageNumber, pageSize);
        request.setAttribute("pageInfo",pageInfo);
        request.getRequestDispatcher("employer_manage.jsp").forward(request, response);
    }
}
