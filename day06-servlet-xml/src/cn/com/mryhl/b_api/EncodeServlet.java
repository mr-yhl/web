package cn.com.mryhl.b_api;

import javax.servlet.*;
import java.io.IOException;
public class EncodeServlet implements Servlet {
    // 声明变量
    private ServletConfig servletConfig;
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.servletConfig= servletConfig;
    }
    // 提供获取servletConfig公共方法
    @Override
    public ServletConfig getServletConfig() {
        return servletConfig;
    }
    // 对外提供服务器
    @Override
    public void service(ServletRequest servletRequest, ServletResponse
            servletResponse) throws ServletException, IOException {
// 对中文进行编码
        String encode = getServletConfig().getInitParameter("encode");
// 向浏览器输出结果
        servletResponse.getWriter().write(encode);
    }
    @Override
    public String getServletInfo() {
        return null;
    }
    @Override
    public void destroy() {
    }
}