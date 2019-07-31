package cn.jxau.filter;

import cn.jxau.service.Impl.StaffServiceImpl;
import cn.jxau.service.StaffService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter",urlPatterns ={"/*"})
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        Cookie[] cookies = request.getCookies();
        String value="";
        if (cookies != null) {
            for (Cookie cookie:cookies) {
                if (cookie.getName().equals("loginUser")) {
                    value = cookie.getValue();
                    break;
                }
            }
        }
        if (value != null && !value.equals("")) {
            String[] split = value.split("-");
            StaffService staffService = new StaffServiceImpl();
            boolean login = staffService.login(split[0],split[1]);
            if (login){
                request.getSession().setAttribute("loginUser",split[0]);
            }
        }
        chain.doFilter(req, resp);


    }

    public void init(FilterConfig config) throws ServletException {

    }

}
