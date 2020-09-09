package com.itheima.web.servlet;

import com.itheima.domain.PageBean;
import com.itheima.domain.Route;
import com.itheima.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/RouteServlet")
public class RouteServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取前台传的值
        String method = request.getParameter("method");
        if ("findAll".equals(method)){
            this.findAll(request,response);
        }else if ("findByPage".equals(method)){
            this.findByPage(request,response);

        }
    }


    protected void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 调用service
        RouteServiceImpl routeService = new RouteServiceImpl();
        List<Route> all = routeService.findAll();

        // 转发视图
        request.setAttribute("list",all);
        request.getRequestDispatcher("/route_list.jsp").forward(request,response);
    }

    protected void findByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1 接收请求参数
        Integer pageNum = Integer.valueOf(request.getParameter("pageNum"));
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));

        // 2 调用service

        RouteServiceImpl routeService = new RouteServiceImpl();
        PageBean<Route> pb = routeService.findByPage(pageNum, pageSize);

        // 3 转发实体
        request.setAttribute("pb",pb);
        request.getRequestDispatcher("route_list.jsp").forward(request,response);
    }
}