package cn.com.mryhl.c_inherit;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class ServletDemo01 extends GenericServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("我重写init方法...");
    }
    // 对外提供服务的方法
    @Override
    public void service(ServletRequest servletRequest, ServletResponse
            servletResponse) throws ServletException, IOException {
        System.out.println(getServletConfig());
        System.out.println("ServletDemo1 extends GenericServlet");
        servletResponse.getWriter().write("ServletDemo1 extends GenericServlet");
    }
    @Override
    public void destroy() {
        System.out.println("我重写destroy方法...");
    }
}
