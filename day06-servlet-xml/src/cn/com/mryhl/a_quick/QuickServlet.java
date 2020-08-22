package cn.com.mryhl.a_quick;

import javax.servlet.*;
import java.io.IOException;

public class QuickServlet implements Servlet {
    /**
    * servlet创建时,执行init初始化化方法
    */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }
    /**
     *提供对外访问服务的方法
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("QuickServlet执行了");
    }
    /**
     * 用于描述当前servlet的功能(信息)
     */
    @Override
    public String getServletInfo() {
        return null;
    }
    /**
     * servlet销毁时
     */
    @Override
    public void destroy() {

    }
}
