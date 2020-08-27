package cn.com.mryhl.tj40827.web;

import cn.com.mryhl.tj40827.domain.User;
import cn.com.mryhl.tj40827.service.UserService;

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
        // 1. 接收请求(没有)

        // 2.调用service查询
        UserService userService = new UserService();
        // 创建对象集合
        List<User> list = userService.findAll();

        // 3.转发视图
        request.setAttribute("list",list);
        request.getRequestDispatcher("/list.jsp").forward(request,response);



    }
}