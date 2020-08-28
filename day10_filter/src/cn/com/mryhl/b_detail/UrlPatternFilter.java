package cn.com.mryhl.b_detail;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

// 目录匹配
// @WebFilter("/user/*")
// 后缀名匹配
// @WebFilter("*.html")
// 拦截所有
// @WebFilter("/*")
public class UrlPatternFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("UrlPatternFilter拦截执行了.....");
        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
