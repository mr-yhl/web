package cn.com.mryhl.a_quick;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(urlPatterns = "/UserServlet")
public class UserServlet implements Servlet {// 实现此接口，才能被外界访问

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    // 对外提供服务的方法
    /**
        servletRequest：请求 （获取）
        servletResponse：响应（返回）
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        // System.out.println("hello,servlet");
        // request用于接收请求
        String username = servletRequest.getParameter("username");
        String password = servletRequest.getParameter("password");

        // response用于返回响应
        servletResponse.getWriter().print(username + "   " + password);

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}