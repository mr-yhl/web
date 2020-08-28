package cn.com.mryhl.b_detail;

import javax.servlet.*;
import java.io.IOException;

/**
 * 生命周期
 */
public class LifeFilter implements Filter {
    // 定义变量
    private FilterConfig filterConfig;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 创建时，执行
        /*
        FilterConfig：过滤器配置对象
        加载一些配置信息...
        */
        System.out.println("LifeFilter创建了");
        this.filterConfig = filterConfig;
    }
    /**
     * 执行拦截
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("LifeFilter拦截了...");
        // 获取xml传来的设置参数
        String encoding = filterConfig.getInitParameter("encoding");
        // 输出打印
        System.out.println(encoding);
        // 放行
        filterChain.doFilter(servletRequest,servletResponse);

    }

    @Override
    public void destroy() {
        System.out.println("销毁了...");

    }
}
