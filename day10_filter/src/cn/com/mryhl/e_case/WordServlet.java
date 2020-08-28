package cn.com.mryhl.e_case;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/WordServlet")
public class WordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 接收请求
        String word = request.getParameter("word");
        // 2. 响应弹幕
        // 设置编码
        response.setContentType("text/html;charset=utf-8");
        // 输出
        response.getWriter().write("你发送的弹幕是:"+word);
    }
}