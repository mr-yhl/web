package cn.com.mryhl.b_detail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/OverrideURL")
public class OverrideURL extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        // 1.获取session
        HttpSession session = request.getSession();
        // 2.存数据
        session.setAttribute("product", "我使用了url重写技术，浏览器禁用了cookie，我也不怕哈哈哈");

        // 3.重写url（将jsessionid，写入到地址栏）
        String url = "/day08_session_war_exploded/GetSession";

        url = response.encodeURL(url);

        response.getWriter().write("<a href='"+ url +"'>使用了url重写技术</a>");
    }
}