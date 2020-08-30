package cn.com.mryhl.web.servlet;

import cn.com.mryhl.service.UserServic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeletServlet")
public class DeletServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 接收参数
        String id = request.getParameter("id");
        // 调用UserService的功能
        UserServic userServic = new UserServic();
        userServic.delete(id);
        // 重定向
        response.sendRedirect(request.getContextPath()+"/FindAllServlet");
    }
}