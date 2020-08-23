package cn.com.mryhl.a_resquest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/QQVideo")
public class QQVideo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        // 1.获取请求来源
        String referer = request.getHeader("referer");
        // 2.判断
        if (referer != null && referer.startsWith("http://localhost:8080/day07_request_war_exploded")) {
                response.getWriter().write("<h1>林绿茶表白许山炮..</h1>");
    } else {
        response.getWriter().write("<h1>想看最新剧集吗，请来腾讯把~~~</h1>");
    }
    }
}