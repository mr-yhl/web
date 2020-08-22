package cn.com.mryhl.a_api;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/GlobalServlet")
public class GlobalServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取ServletContext
        ServletContext servletContext = getServletContext();

        // 获取全局配置参数
        String encode = servletContext.getInitParameter("encode");

        System.out.println("获取的字符集：" + encode);
    }
}