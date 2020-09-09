package com.itheima.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class EncodingFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        if (request.getMethod().equalsIgnoreCase("post")) {
            req.setCharacterEncoding("utf-8");
            
        }
        // 放行
        chain.doFilter(req, resp);
    }

    public void destroy() {

    }

}
