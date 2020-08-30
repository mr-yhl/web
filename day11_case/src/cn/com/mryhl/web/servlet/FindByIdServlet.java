package cn.com.mryhl.web.servlet;

import cn.com.mryhl.domain.User;
import cn.com.mryhl.service.UserServic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/FindByIdServlet")
public class FindByIdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.接收请求参数
        String id = request.getParameter("id");
        // 2.调用UserService查询
        UserServic userServic = new UserServic();
        User user = userServic.findById(id);
        // 3.转发视图
        request.setAttribute("user", user);
        request.getRequestDispatcher("/update.jsp").forward(request, response);
    }
}