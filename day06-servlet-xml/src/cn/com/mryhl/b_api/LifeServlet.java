package cn.com.mryhl.b_api;

import javax.servlet.*;
import java.io.IOException;

public class LifeServlet implements Servlet {
    /**
     * 创建,在创建时执行此方法
     */

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("LifeServlet创建了");
    }

        @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     * 每次运行此方法
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("LifeServlet运行");
    }

    @Override
    public String getServletInfo() {
        return null;
    }
    /**
    * 销毁执行此方法
    */
    @Override
    public void destroy() {
        System.out.println("LifeServlet销毁");

    }
}
