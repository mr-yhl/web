package cn.com.mryhl.web.servlet;

import cn.com.mryhl.domain.User;
import cn.com.mryhl.service.UserServic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/FindAllServlet")
public class FindAllServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 调用UserService查询
        UserServic userServic = new UserServic();
        List<User> user = userServic.findAll();

        // 转发视图

        request.setAttribute("list",user);
        request.getRequestDispatcher("/list.jsp").forward(request,response);


    }
}