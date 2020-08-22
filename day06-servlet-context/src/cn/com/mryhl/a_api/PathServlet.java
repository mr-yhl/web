package cn.com.mryhl.a_api;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/PathServlet")
public class PathServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取ServletContext
        ServletContext servletContext = getServletContext();

        // 获取 8.jpg真实路径
        String picPath = servletContext.getRealPath("/download/8.jpg");
        System.out.println(picPath);
        // 获取 web.xml真实路径
        String webPath = servletContext.getRealPath("/WEB-INF/web.xml");
        System.out.println(webPath);
    }
}