package com.itheima.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/index.jsp")
public class LoginFilter implements Filter {
    /**
     *
     */
    @Override
    public void init(FilterConfig config) throws ServletException {

    }

    /**
     *
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {

        // 向下转型
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("user");
        if (user==null){
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }

        // 放行
        filterChain.doFilter(request, response);
    }

    /**
     *
     */
    @Override
    public void destroy() {
    }

}
