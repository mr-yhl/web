package cn.com.mryhl.d_chain;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/show.jsp")
public class FilterB implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {

        // 向下转型
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        System.out.println("FilterB拦截执行了...");
        // 放行
        filterChain.doFilter(request, response);
        System.out.println("FilterB增强了...");
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
