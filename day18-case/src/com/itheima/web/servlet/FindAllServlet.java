package com.itheima.web.servlet;

import com.itheima.domain.Route;
import com.itheima.service.impl.RouteServiceImpl;

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
        // 调用service
        RouteServiceImpl routeService = new RouteServiceImpl();
        List<Route> all = routeService.findAll();

        // 转发视图
        request.setAttribute("list",all);
        request.getRequestDispatcher("/route_list.jsp").forward(request,response);


    }
}