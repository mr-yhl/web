package cn.com.mryhl.b_detail;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
* 指定过滤器拦截方式
* */

// @WebFilter(value = "/BServlet", dispatcherTypes = {DispatcherType.FORWARD}) // 内部转发时，进行拦截
// @WebFilter(value = "/BServlet", dispatcherTypes = {DispatcherType.REQUEST,DispatcherType.FORWARD}) // 外部请求和内部转发时，都会拦截
public class ModelFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("ModelFilter拦截了...");
        // 向下转型
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 放行
        filterChain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
    }
}
