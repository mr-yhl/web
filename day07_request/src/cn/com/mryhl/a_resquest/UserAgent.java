package cn.com.mryhl.a_resquest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UserAgent")
public class UserAgent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        // 1.获取user-agent头信息
        String userAgent = request.getHeader("User-Agent");
        // 2.判断厂商
        if (userAgent.contains("Chrome")) {
            response.getWriter().write("这是谷歌浏览器");
        } else if (userAgent.contains("Firefox")) {
            response.getWriter().write("这是火狐浏览器");
        } else {
            response.getWriter().write("其他浏览器");
        }
    }
}