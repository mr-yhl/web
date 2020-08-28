package cn.com.mryhl.a_quick;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

// @WebFilter(filterName = "QuickFilter",urlPatterns ="/quick.jsp" )
// @WebFilter(urlPatterns ="/quick.jsp" )
// @WebFilter(value ="/quick.jsp" )
@WebFilter("/quick.jsp")
public class QuickFilter implements Filter {
    @Override
    public void destroy() {
    }

    /**
     * 实现拦截的方法...
     *
     * @param req：请求
     * @param resp：响应
     * @param chain：过滤器链（放行）
     */

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        System.out.println("filter执行了....");
        // 放行
        chain.doFilter(req, resp);
        System.out.println("对响应结果增强...");
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
