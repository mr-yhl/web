package com.itheima.web;

import com.itheima.domain.User;
import com.itheima.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/FindAllServlet")
public class FindAllServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.接收请求（略）

        // 2.调用service
        UserService userService = new UserService();
        List<User> list =  userService.findAll();

        // 3.存储到request域
        request.setAttribute("list", list);
       response.sendRedirect(request.getContextPath()+"/list.jsp");
    }

}